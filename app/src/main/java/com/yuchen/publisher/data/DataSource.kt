package com.yuchen.publisher.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*

object DataSource {
    private val db = Firebase.firestore
    private val articles = db.collection("articles")

    const val AUTHOR_EMAIL = "eliseo.cyc@gmail.com"
    const val AUTHOR_ID = "eliseo"
    const val AUTHOR_NAME = "YuChen"

    val listArticle = MutableLiveData<List<Article>>()
    val addArticleSuccess = MutableLiveData<Boolean>()

    fun addData(title : String,tag : String ,content : String) {
        Log.d("chenyjzn", "post")

        val document = articles.document()

        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to AUTHOR_EMAIL,
                "id" to AUTHOR_ID,
                "name" to AUTHOR_NAME),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to document.id,
            "tag" to tag
        )
        document.set(data)
            .addOnSuccessListener {
                Log.d("chenyjzn", "post added success")
                addArticleSuccess.value = true
                getAllArticles()
            }
            .addOnFailureListener { exception ->
                Log.w("chenyjzn", "post error getting documents.", exception)
                addArticleSuccess.value = false
            }
    }

    fun getAllArticles(){
        var list: List<Article> = listOf()
        articles.get().addOnSuccessListener { result ->
            for (document in result) {
                val article = document.toObject<Article>()
                list = list + listOf(article)
            }
            listArticle.value = list
            Log.d("chenyjzn", "Get articles from firebase success")
        }.addOnFailureListener { exception ->
            Log.w("chenyjzn", "Error getting documents.", exception)
        }
    }
}