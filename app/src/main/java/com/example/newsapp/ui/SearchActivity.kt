package com.example.newsapp.ui


import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.MainAdapter
import com.example.newsapp.model.Article
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome
import com.example.newsapp.presenter.search.SearchPresenter
import com.example.newsapp.util.UtilQueryTextListner

class SearchActivity : AbstractActivity(), ViewHome.View {

    private val searchAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: SearchPresenter

    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = SearchPresenter(this, dataSource)
        configRecycler()
        search()
    }

    private fun search() {
        findViewById<SearchView>(R.id.svSearchNews).setOnQueryTextListener(
            UtilQueryTextListner(
                this.lifecycle
            ) { newText ->
                newText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        findViewById<ProgressBar>(R.id.rvSearchProgressBar).visibility =
                            View.VISIBLE
                    }
                }
            }
        )
    }

    private fun configRecycler() {
        with(findViewById<RecyclerView>(R.id.rvSearchNews)) {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.rvSearchProgressBar).visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.rvSearchProgressBar).visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        searchAdapter.differ.submitList(articles.toList())
    }
}