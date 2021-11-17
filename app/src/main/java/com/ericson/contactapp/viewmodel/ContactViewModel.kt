package com.ericson.contactapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.ericson.contactapp.data.ContactRepository
import com.ericson.contactapp.data.models.Contact
import com.ericson.contactapp.data.models.ContactDatabase
import com.ericson.contactapp.data.models.Result
import kotlinx.coroutines.launch

class ContactViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ContactRepository(application.baseContext)
    private val _message = MutableLiveData<Result<Boolean>>()
    val message:LiveData<Result<Boolean>> = _message

    fun getAll():LiveData<List<Contact>>{
        return repository.getAll()
    }

    fun getById(id:Long):LiveData<Contact>{
        return repository.get(id)
    }

    fun save(contact: Contact){
        viewModelScope.launch {
            repository.save(contact).let {
                _message.value = it
            }
        }
    }

    fun update(contact: Contact){
        viewModelScope.launch {
            repository.update(contact).let {
                _message.value = it
            }
        }
    }
}