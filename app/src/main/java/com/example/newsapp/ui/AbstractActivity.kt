package com.example.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        onInject()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun onInject()
}