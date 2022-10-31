package com.azimut4946777.shoptask

import android.app.Application
import com.azimut4946777.shoptask.data.TaskRoomDatabase

class ShopTaskApplication:Application() {
    val database: TaskRoomDatabase by lazy {
        TaskRoomDatabase.getDatabase(this)
    }
}