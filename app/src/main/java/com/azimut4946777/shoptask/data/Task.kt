package com.azimut4946777.shoptask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val taskName: String,
    @ColumnInfo(name = "quantity")
    val taskQuantity: Int
)