package com.rubdev.moviesnow.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubdev.moviesnow.Constants.Companion.DATABASE_NAME

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var instance: MoviesDatabase? = null
        private val LOCK = Any()
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                DATABASE_NAME,
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
}


