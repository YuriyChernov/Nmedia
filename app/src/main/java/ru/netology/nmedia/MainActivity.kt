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
            views = 999,
        )

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
            share.text = convertNumbers(post.share)
            views.text = convertNumbers(post.views)

            if (post.likedByMe) {
                likesButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            likesButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes += if (post.likedByMe) 1 else -1
                likesButton.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
                likes.text = convertNumbers(post.likes)
            }

            shareButton.setOnClickListener {
                post.share++
                share.text = convertNumbers(post.share)
            }

            viewsButton.setOnClickListener {
                post.views++
                views.text = convertNumbers(post.views)
            }
        }
    }
}