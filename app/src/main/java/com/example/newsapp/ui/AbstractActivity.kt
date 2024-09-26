package com.example.newsapp.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = getLayout()
        setContentView(getLayout())
        onInject()
    }

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun onInject()
}