package com.example.exercise202.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercise202.api.Dog
import com.example.exercise202.api.DownloadService
import com.example.exercise202.storage.filesystem.ProviderFileManager
import com.example.exercise202.storage.preference.DownloadPreferencesWrapper
import com.example.exercise202.storage.room.DogDao
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class DogRepositoryImpl(
    private val dogDao: DogDao,
    private val downloadService: DownloadService,
    private val providerFileManager: ProviderFileManager,
    private val downloadPreferencesWrapper: DownloadPreferencesWrapper,
    private val dogMapper: DogMapper,
    private val executor: Executor
) : DogRepository {

    override fun loadDogList(): LiveData<Result<List<DogUi>>> {
        val result = MediatorLiveData<Result<List<DogUi>>>()
        result.postValue(Result.Loading())
        result.addSource(dogDao.loadAllDogs()) { dogEntities ->
            result.postValue(Result.Success(dogEntities.map { dogMapper.mapEntityToUiModel(it) }))
        }
        downloadService.getDogs(downloadPreferencesWrapper.getNumberOfResults())
            .enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                    if (response.isSuccessful) {
                        executor.execute {
                            response.body()?.let {
                                dogDao.deleteAll()
                                dogDao.insertDogs(dogMapper.mapServiceToEntity(it))
                            }
                        }
                    } else {
                        result.postValue(Result.Error())
                    }
                }

                override fun onFailure(call: Call<Dog>, t: Throwable) {
                    result.postValue(Result.Error())
                }

            })
        return result
    }


    override fun downloadFile(url: String): LiveData<Result<Unit>> {
        val result = MutableLiveData<Result<Unit>>()
        result.postValue(Result.Loading())

        downloadService.downloadFile(url)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    result.postValue(Result.Error())
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        executor.execute {
                            try {
                                response.body()?.let {
                                    val name = url.substring(url.lastIndexOf("/") + 1)
                                    providerFileManager.writeStream(
                                        name,
                                        response.body()!!.byteStream()
                                    )
                                    result.postValue(Result.Success(Unit))
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                result.postValue(Result.Error())
                            }

                        }
                    } else {
                        result.postValue(Result.Error())
                    }
                }

            })
        return result
    }
}