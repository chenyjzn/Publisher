package com.yuchen.publisher.publish

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuchen.publisher.data.Article
import com.yuchen.publisher.data.DataSource

class PublishViewModel (application: Application) : AndroidViewModel(application) {

    private var _addArticleSuccess = MutableLiveData<Boolean>()
    val addArticleSuccess: LiveData<Boolean>
        get() = _addArticleSuccess

    init {
        DataSource.addArticleSuccess.value = null
        _addArticleSuccess = DataSource.addArticleSuccess
    }
    
    fun addData(title : String,tag : String ,content : String){
        DataSource.addData(title,tag,content)
    }
}