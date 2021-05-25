package com.example.todolist.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.todolist.Model.TodoDao
import com.example.todolist.Model.TodoData

@Database(entities = arrayOf(TodoData::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null;
        fun getInstance(ctx: Context): AppDatabase {
            var t = _instance;
            if (t != null) {
                return t
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        ctx.applicationContext,
                        AppDatabase::class.java,
                        "userdata"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                _instance = instance;
                return instance;
            }
        }
    }
}