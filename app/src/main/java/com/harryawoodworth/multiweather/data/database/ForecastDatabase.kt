package com.harryawoodworth.multiweather.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harryawoodworth.multiweather.data.model.ForecastModel

/**
 * Defines the database configuration and serves as the main access point to cached data
 * @Database defines what type of entries there are
 */
@Database(entities = [ForecastModel::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {
    abstract val forecastDao: ForecastDao

    companion object {

        // For singleton
        @Volatile private var instance: ForecastDatabase? = null

        // Following the Singleton pattern, once accessed initialize an instance and return it
        fun getInstance(context: Context): ForecastDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ForecastDatabase {
            return Room.databaseBuilder(context, ForecastDatabase::class.java, "forecastdb").build()
        }
    }
}