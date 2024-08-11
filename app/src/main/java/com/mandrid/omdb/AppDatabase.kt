package com.mandrid.omdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [MovieModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAll(): List<MovieModel>

    @Query("SELECT * FROM movie_table")
    fun getAllLiveData(): LiveData<List<MovieModel>>


    @Query("DELETE FROM movie_table where movieID= :moviedID")
    fun delete(moviedID:Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( movies: List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(movies: MovieModel)

}
