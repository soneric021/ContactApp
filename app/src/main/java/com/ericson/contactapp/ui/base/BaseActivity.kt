package com.ericson.contactapp.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {
    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
        setUp()
    }
    abstract fun setUp()
}