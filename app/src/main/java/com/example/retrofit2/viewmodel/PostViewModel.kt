package com.example.retrofit2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit2.model.Post
import com.example.retrofit2.repository.PostRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post

    private val _postCreationStatus = MutableLiveData<String>()
    val postCreationStatus: LiveData<String> = _postCreationStatus

    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = repository.getPosts() ?: emptyList()
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            _post.value = repository.getPostById(postId)
        }
    }

    fun createPost(newPost: Post) {
        viewModelScope.launch {
            try {
                val createdPost = repository.createPost(newPost)
                if (createdPost != null) {
                    _postCreationStatus.value = "Post créé avec succès: ${createdPost.title}"
                } else {
                    _postCreationStatus.value = "Erreur : La réponse de l'API est nulle."
                }
            } catch (e: Exception) {
                _postCreationStatus.value = "Erreur : ${e.message}"
            }
        }
    }


}
