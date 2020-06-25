package com.yuchen.publisher.articles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuchen.publisher.R
import com.yuchen.publisher.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArticlesBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ArticlesViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(ArticlesViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ArticlesAdapter()
        binding.publisherRecyclerView.adapter = adapter

        viewModel.articlesList.observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                val myList = list.sortedByDescending { it.createdTime}
                Log.d("chenyjzn","$myList")
                adapter.submitList(myList)
            }
        })
        return binding.root
    }
}
