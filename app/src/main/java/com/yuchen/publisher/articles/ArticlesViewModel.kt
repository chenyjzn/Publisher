package com.yuchen.publisher.articles

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuchen.publisher.data.Article
import com.yuchen.publisher.data.DataSource

class ArticlesViewModel (application: Application) : AndroidViewModel(application) {

    private var _articlesList = DataSource.listArticle
    val articlesList: LiveData<List<Article>>
        get() = _articlesList

    init {
        DataSource.getAllArticles()
    }
}