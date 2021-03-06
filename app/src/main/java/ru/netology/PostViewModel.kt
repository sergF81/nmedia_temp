package ru.netology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

val empty = Post(
id  = 0,
author = "",
content = "",
published = "",
likesCount = 0,
likedByMe = false,
sharedCount = 0
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    val edited = MutableLiveData(empty)
    fun like(id: Int) = repository.like(id)

    fun share(id: Int) = repository.share(id)
    fun removeById(id: Int) = repository.removeById(id)
    fun save() {
       edited.value?.let {
           repository.save(it)
       }
        edited.value = empty
    }

    fun edit(post: Post){
        edited.value = post
    }

    fun changeContent(content: String){
        edited.value?.let {
            val newPost = content.trim()
            if (newPost != it.content){
                edited.value = edited.value?.copy(content = content)
            }
        }
    }
}
