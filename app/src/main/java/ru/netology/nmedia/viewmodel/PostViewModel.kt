package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImplem

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImplem()

    val data = repository.get()

    fun like() = repository.likes()

    fun share() = repository.share()

    fun views() = repository.views()
}