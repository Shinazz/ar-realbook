package com.shinaz.newapp.model.ar

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shinaz.newapp.room.util.LocationTypeConverter

@Entity(tableName = "shinaz_table")
data class UserData(
    @PrimaryKey @ColumnInfo(name = "userId")
    val userId: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "desc")
    val desc: String?,
    @field:TypeConverters(LocationTypeConverter::class)
    @ColumnInfo(name = "location")
    val location: LocationData?,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserData

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }

}
