package com.example.newsapp.presenter.search

import com.example.newsapp.model.NewsResponse

interface SearchHome {

        interface Presenter {
            fun search(query: String)
            fun onSuccess(newsResponse: NewsResponse)
            fun onError(message: String)
            fun onCompleted()
        }
}