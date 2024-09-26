package com.example.newsapp.model.data

import com.example.newsapp.network.RetrofitInstance
import com.example.newsapp.presenter.news.NewsHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
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