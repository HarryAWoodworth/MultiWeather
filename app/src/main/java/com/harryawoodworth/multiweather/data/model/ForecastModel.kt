package com.harryawoodworth.multiweather.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Model for the data contained in the weather forecast from the API
 *
 * Also the Entity class for Room
 *
 * @SerializedName is used to determine the exact key in the response, and avoid obfuscation issues
 * https://stackoverflow.com/questions/28957285/what-is-the-basic-purpose-of-serializedname-annotation-in-android-using-gson
 */
@Entity(tableName = "forecasts")
data class ForecastModel(

    @PrimaryKey
    @SerializedName("endTime")
    val endTime: String = "",

    @ColumnInfo(name = "is_daytime")
    @SerializedName("isDaytime")
    val isDaytime: Boolean = true,

    @SerializedName("temperature")
    val temperature: Int = 0,

    @ColumnInfo(name = "wind_speed")
    @SerializedName("windSpeed")
    val windSpeed: String = "0 mph",

    @ColumnInfo(name = "short_forecast")
    @SerializedName("shortForecast")
    val shortForecast: String = "missing"

)

