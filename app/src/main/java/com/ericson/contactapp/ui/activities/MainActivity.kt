package com.ericson.contactapp.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.ericson.contactapp.R
import com.ericson.contactapp.databinding.ActivityMainBinding
import com.ericson.contactapp.ui.base.BaseActivity
import com.ericson.contactapp.ui.fragments.ContactFormFragment
import com.ericson.contactapp.ui.fragments.ContactFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}