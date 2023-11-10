package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias LikeListener = (Post) -> Unit
typealias ShareListener = (Post) -> Unit
typealias ViewListener = (Post) -> Unit
class PostsAdapter(private val listener: LikeListener, private val listener2: ShareListener, private val listener3: ViewListener) : ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, listener, listener2, listener3)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class PostViewHolder(
        private val binding: CardPostBinding,
        private val listener: LikeListener,
        private val listener2: ShareListener,
        private val listener3: ViewListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
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
                likesButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                likesButton.setOnClickListener {
                    listener(post)
                }
                share.text = convertNumbers(post.share)
                shareButton.setOnClickListener {
                    listener2(post)
                }
                views.text = convertNumbers(post.views)
                viewsButton.setOnClickListener {
                    listener3(post)
                }
            }
        }
    }

    object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
    }
}
