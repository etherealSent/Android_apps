package com.example.exercise202

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JournalistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournalist(journalist: Journalist)
    @Update
    fun updateJournalist(journalist: Journalist)
    @Delete
    fun deleteJournalist(journalist: Journalist)
    @Query("SELECT * FROM journalist")
    fun loadAllJournalists(): List<Journalist>
    @Query("SELECT * FROM journalist INNER JOIN joined_article_journalist ON journalist.id=joined_article_journalist.journalist_id WHERE joined_article_journalist.article_id=:articleId")
        fun getAuthorsForArticle(articleId: Long): List<Journalist>
}