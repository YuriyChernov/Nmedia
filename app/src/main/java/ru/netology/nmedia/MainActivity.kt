package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            likes = 999,
            share = 999,
            views = 999
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.text = post.likes.toString()
            share.text = post.share.toString()
            views.text = post.views.toString()

            if (post.likedByMe) {
                likesButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            likesButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes += if (post.likedByMe) 1 else -1
                likesButton.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
                likes.text = likesToK(post.likes)
            }

            shareButton.setOnClickListener {
                post.share++
                share.text = shareToK(post.share)
            }

            viewsButton.setOnClickListener {
                post.views++
                views.text = viewsToK(post.views)
            }
        }
    }

    private fun likesToK(likes: Int): String {
        return when {
            likes < 1000 -> "$likes"
            likes < 10000 && likes % 1000 == 0 -> likes.toString()[0] + "К"
            likes < 10000 -> likes.toString()[0] + "." + likes.toString()[1] + "К"
            likes < 1000000 -> likes.toString()[0] + "" + likes.toString()[1] + "К"
            likes < 10000000 -> likes.toString()[0] + "." + likes.toString()[1] + "M"
            else -> likes.toString()[0] + "" + likes.toString()[1] + "M"
        }
    }

    private fun shareToK(share: Int): String {
        return when {
            share < 1000 -> "$share"
            share < 10000 && share % 1000 == 0 -> share.toString()[0] + "К"
            share < 10000 -> share.toString()[0] + "." + share.toString()[1] + "К"
            share < 1000000 -> share.toString()[0] + "" + share.toString()[1] + "К"
            share < 10000000 -> share.toString()[0] + "." + share.toString()[1] + "M"
            else -> share.toString()[0] + "" + share.toString()[1] + "M"
        }
    }

    private fun viewsToK(views: Int): String {
        return when {
            views < 1000 -> "$views"
            views < 10000 && views % 1000 == 0 -> views.toString()[0] + "К"
            views < 10000 -> views.toString()[0] + "." + views.toString()[1] + "К"
            views < 1000000 -> views.toString()[0] + "" + views.toString()[1] + "К"
            views < 10000000 -> views.toString()[0] + "." + views.toString()[1] + "M"
            else -> views.toString()[0] + "" + views.toString()[1] + "M"
        }
    }
}

