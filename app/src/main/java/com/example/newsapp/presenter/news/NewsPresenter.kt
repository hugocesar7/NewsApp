package com.example.newsapp.presenter.news

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome

class NewsPresenter(val view: ViewHome.View,
                    private val dataSource: NewsDataSource
) : NewsHome.Presenter {
    override fun requestAll() {
//        this.dataSource.getEverything(this)
    }

    override fun onSuccess(newResponse: NewsResponse) {
//        this.view.showArticles(news)
    }

    override fun onError(message: String) {
        this.view.showMessage(message)
    }

    override fun onCompleted() {
        this.view.hideProgressBar()
    }
}