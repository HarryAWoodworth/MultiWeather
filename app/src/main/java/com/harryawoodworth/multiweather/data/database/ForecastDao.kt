package com.harryawoodworth.multiweather.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harryawoodworth.multiweather.data.model.ForecastModel

/**
 * Data Access object for querying into the SQLite db using Room
 */
@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecasts")
    fun getAll(): LiveData<List<ForecastModel>>

    @Query("SELECT * FROM forecasts WHERE endTime LIKE :dateString LIMIT 1")
    fun findByData(dateString: String): LiveData<ForecastModel>

    @Query("SELECT * FROM forecasts WHERE endTime >= :dateString")
    fun loadForecastsFromDateOnward(dateString: String): LiveData<List<ForecastModel>>

    // We define a conflict strategy in the case of insertions conflicts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(forecasts: MutableList<ForecastModel>)

    @Delete
    fun delete(forecast: ForecastModel)

    @Query("DELETE FROM forecasts WHERE endTime < (:dateString)")
    fun deleteAllBeforeDate(dateString: String)
}