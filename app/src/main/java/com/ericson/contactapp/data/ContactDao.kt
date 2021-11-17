package com.ericson.contactapp.data.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("select * from contact_table")
    fun getAll():LiveData<List<Contact>>

    @Query("select * from contact_table where id = :id")
    fun get(id:Long):LiveData<Contact>

    @Insert
    suspend fun save(contact: Contact)

    @Update
    suspend fun update(contact: Contact)
}