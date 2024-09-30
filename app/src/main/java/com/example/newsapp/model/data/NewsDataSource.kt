package com.example.newsapp.model.data

import com.example.newsapp.network.RetrofitInstance
import com.example.newsapp.presenter.news.NewsHome
import com.example.newsapp.presenter.search.SearchHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("us")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onCompleted()
            } else {
                callback.onError(response.message())
                callback.onCompleted()
            }
        }
    }

    fun searchNews(query: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(query)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onCompleted()
            } else {
                callback.onError(response.message())
                callback.onCompleted()
            }
        }
    }
}