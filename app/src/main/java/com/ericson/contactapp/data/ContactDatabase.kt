package com.ericson.contactapp.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao

    companion object{
        @Volatile private var instance: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, ContactDatabase::class.java, "contact_database")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}