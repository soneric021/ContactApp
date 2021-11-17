package com.ericson.contactapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.ericson.contactapp.data.models.Contact
import com.ericson.contactapp.data.models.ContactDatabase
import com.ericson.contactapp.data.models.Result
import java.lang.Exception

class ContactRepository(context: Context) {
    private val db = ContactDatabase.getDatabase(context)

    fun getAll():LiveData<List<Contact>> = db.contactDao().getAll()

    fun get(id:Long):LiveData<Contact> = db.contactDao().get(id)

    suspend fun save(contact: Contact) : Result<Boolean>{
        return try {
            db.contactDao().save(contact)
            Result.Success(true)
        }catch (ex:Exception){
            Log.e("Creando contacto", ex.message ?: "", ex)
            Result.Error(ex)
        }
    }

    suspend fun update(contact: Contact): Result<Boolean>{
        return try {
            db.contactDao().update(contact)
            Result.Success(true)
        }catch (ex:Exception){
            Log.e("Actualizar contacto", ex.message ?: "", ex)
            Result.Error(ex)
        }
    }
}