package com.ericson.contactapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "contact_table")
@Parcelize
data class Contact(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    var name:String,
    var email:String,
    var phoneNumber:String
) : Parcelable
