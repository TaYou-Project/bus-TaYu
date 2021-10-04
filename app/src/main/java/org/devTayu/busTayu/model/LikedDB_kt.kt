package org.devTayu.busTayu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likedTable")
data class LikedDB_kt(
        var busNumber: String,
        var stationNumber: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}