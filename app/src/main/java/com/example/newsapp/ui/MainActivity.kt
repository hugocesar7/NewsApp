package com.example.newsapp.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.MainAdapter
import com.example.newsapp.model.Article
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome
import com.example.newsapp.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycler()
    }

    private fun configRecycler() {
        with(findViewById<RecyclerView>(R.id.rvNews)) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.rvMainProgressBar).visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.rvMainProgressBar).visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())

    }
}