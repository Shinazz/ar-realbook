package com.shinaz.newapp.room.util

import android.location.Location
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shinaz.newapp.model.ar.LocationData

@ProvidedTypeConverter
class LocationTypeConverter {
    @TypeConverter
    fun fromString(value: String): LocationData? {
        val type = object : TypeToken<LocationData?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromLocation(location: LocationData?): String {
        val type = object : TypeToken<LocationData?>() {}.type
        val gson = Gson()
        return gson.toJson(location, type)
    }
}