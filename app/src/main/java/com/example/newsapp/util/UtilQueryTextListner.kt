package com.example.newsapp.util

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import com.example.newsapp.util.Constants.Companion.SEARCH_NEWS_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class UtilQueryTextListner(
    lifecycle: Lifecycle,
    private val utilQueryTextListner: (String?) -> Unit
) : SearchView.OnQueryTextListener, LifecycleObserver,
    android.widget.SearchView.OnQueryTextListener {

    private val courotineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    init {
        lifecycle.addObserver(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = courotineScope.launch {
            newText?.let {
                delay(SEARCH_NEWS_DELAY)
                utilQueryTextListner(newText)
            }
        }

        return false
    }
}