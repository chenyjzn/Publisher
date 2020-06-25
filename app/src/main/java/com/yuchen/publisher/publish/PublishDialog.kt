package com.yuchen.publisher.publish

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuchen.publisher.R
import com.yuchen.publisher.databinding.DialogPublishBinding


class PublishDialog : DialogFragment() {
    var isButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.LoginDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogPublishBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = PublishViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(PublishViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.button.setOnClickListener {
            if (!isButtonClicked) {
                isButtonClicked = true
                val title: String = binding.publishEditTextTitle.text.toString()
                val tag: String = binding.publishEditTextTag.text.toString()
                val content: String = binding.publishEditTextContent.text.toString()
                viewModel.addData(title, tag, content)
            }
        }

        binding.publishBackground.setOnClickListener {
            dismiss()
        }

        viewModel.addArticleSuccess.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) {
                        dismiss()
                    } else {
                        Toast.makeText(context, "Po 文失敗", Toast.LENGTH_SHORT).show()
                        Handler().postDelayed({ dismiss() }, Toast.LENGTH_SHORT.toLong())
                    }
                }
        })

        return binding.root
    }
}
