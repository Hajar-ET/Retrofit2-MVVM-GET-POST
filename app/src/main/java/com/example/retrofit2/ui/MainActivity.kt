package com.example.retrofit2.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit2.R
import com.example.retrofit2.viewmodel.PostViewModel
import com.example.retrofit2.model.Post

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostViewModel

    private lateinit var btnFetch: Button
    private lateinit var btnSearch: Button
    private lateinit var etPostId: EditText
    private lateinit var tvPostDetails: TextView
    private lateinit var btnCreatePost: Button
    private lateinit var etTitle: EditText
    private lateinit var etBody: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFetch = findViewById(R.id.btnFetch)
        btnSearch = findViewById(R.id.btnSearch)
        etPostId = findViewById(R.id.etPostId)
        tvPostDetails = findViewById(R.id.tvResult)

        btnCreatePost = findViewById(R.id.btnCreatePost)
        etTitle = findViewById(R.id.etTitle)
        etBody = findViewById(R.id.etBody)

        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un ID valide."
            }
        }

        btnCreatePost.setOnClickListener {
            val title = etTitle.text.toString()
            val body = etBody.text.toString()

            Toast.makeText(this, "Titre: $title, Corps: $body", Toast.LENGTH_SHORT).show()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                val newPost = Post(userId = 1, title = title, body = body, id = 0)
                viewModel.createPost(newPost)
            } else {
                tvPostDetails.text = "Veuillez remplir tous les champs."
            }
        }

        viewModel.postCreationStatus.observe(this) { status ->
            tvPostDetails.text = status
        }

        viewModel.post.observe(this) { post ->
            tvPostDetails.text = post?.let {
                "ID: ${it.id}\nTitre: ${it.title}\nContenu: ${it.body}"
            } ?: "Aucun post trouvé."
        }
    }
}
