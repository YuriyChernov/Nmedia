package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                fun convertNumbers(count: Int): String {
                    return when {
                        count < 1000 -> count.toString()
                        count < 10000 && count % 1000 == 0 -> count.toString()[0] + "К"
                        count < 10000 -> count.toString()[0] + "." + count.toString()[1] + "К"
                        count < 1000000 -> count.toString()[0] + "" + count.toString()[1] + "К"
                        count < 10000000 -> count.toString()[0] + "." + count.toString()[1] + "M"
                        else -> count.toString()[0] + "" + count.toString()[1] + "M"
                    }
                }
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.text = convertNumbers(post.likes)
                likesButton.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
                share.text = convertNumbers(post.share)
                views.text = convertNumbers(post.views)
            }
        }
        binding.likesButton.setOnClickListener {
            viewModel.like()
        }

        binding.shareButton.setOnClickListener {
            viewModel.share()
        }

        binding.viewsButton.setOnClickListener {
            viewModel.views()
        }
    }
}