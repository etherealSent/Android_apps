package com.example.exercise202

import android.app.Application
import androidx.room.Room
import com.example.exercise202.api.DownloadService
import com.example.exercise202.repository.DogMapper
import com.example.exercise202.repository.DogRepository
import com.example.exercise202.repository.DogRepositoryImpl
import com.example.exercise202.storage.filesystem.FileToUriMapper
import com.example.exercise202.storage.filesystem.ProviderFileManager
import com.example.exercise202.storage.preference.DownloadPreferencesWrapper
import com.example.exercise202.storage.room.DogDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

class MainApplication : Application() {

    lateinit var dogRepository: DogRepository
    lateinit var preferencesWrapper: DownloadPreferencesWrapper

    override fun onCreate() {
        super.onCreate()

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val downloadService: DownloadService = retrofit.create(DownloadService::class.java)

        val database = Room.databaseBuilder(applicationContext, DogDatabase::class.java, "dogs-db")
            .build()

        val providerFileManager = ProviderFileManager(
            this,
            FileToUriMapper()
        )

        preferencesWrapper = DownloadPreferencesWrapper(
            getSharedPreferences("my_shared_preferences", MODE_PRIVATE)
        )

        dogRepository = DogRepositoryImpl(
            dogDao = database.dogDao(),
            executor = Executors.newSingleThreadExecutor(),
            downloadService = downloadService,
            dogMapper = DogMapper(),
            downloadPreferencesWrapper = preferencesWrapper,
            providerFileManager = providerFileManager
        )
    }
}