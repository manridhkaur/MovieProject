package com.mandrid.omdb

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

object DbUtils {

    private var DB_INSTANCE: AppDatabase?= null

    fun getDb(context: Context): AppDatabase {
        if(DB_INSTANCE == null) {
            DB_INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "fav_movie_database"
            ).allowMainThreadQueries()
                .build()
        }
        return DB_INSTANCE!!
    }

    fun getMovieList(context: Context): List<MovieModel> {
        return getDb(context).movieDao().getAll()
    }

    fun getMovieListLiveData(context: Context): LiveData<List<MovieModel>> {
        return getDb(context).movieDao().getAllLiveData()
    }

    fun updateMovie(context: Context, movieModel: MovieModel) {
        getDb(context).movieDao().addFavorite(movieModel)
    }

    fun favMovie(context: Context, movieModel: MovieModel) {
        getDb(context).movieDao().addFavorite(movieModel)
    }

}