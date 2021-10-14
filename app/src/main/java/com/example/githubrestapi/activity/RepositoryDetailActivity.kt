package com.example.githubrestapi.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import com.example.githubrestapi.R
import com.squareup.picasso.Picasso

class RepositoryDetailActivity : AppCompatActivity() {

    var ownerLoginId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_detail)

        val intentExtras = intent.extras
        intentExtras?.let { extras ->
            ownerLoginId = extras.getString(MainActivity.Companion.Extras.AUTHOR_NAME) ?: ""

            val userImage = findViewById<ImageView>(R.id.userImage)
            val userName = findViewById<TextView>(R.id.userName)
            val userButton = findViewById<TextView>(R.id.userButton)
            val repoLanguage = findViewById<TextView>(R.id.repoLanguage)
            val repoCreatedDate = findViewById<TextView>(R.id.repoCreatedDate)
            val repoUpdatedAt = findViewById<TextView>(R.id.repoUpdatedAt)
            val repoButton = findViewById<TextView>(R.id.repoButton)
            val closeButton = findViewById<TextView>(R.id.closeButton)

            val thumbnailUrl = extras.getString(MainActivity.Companion.Extras.THUMBNAIL_URL)
            if (URLUtil.isValidUrl(thumbnailUrl)){
                Picasso.get().load(thumbnailUrl).into(userImage);
                userImage.setOnClickListener {
                    val defaultBrowser = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(thumbnailUrl)
                    )
                    applicationContext.startActivity(defaultBrowser)
                }
            }
            userName.text = extras.getString(MainActivity.Companion.Extras.AUTHOR_NAME) ?: ""
            userButton.setOnClickListener {
                callWebBrowser(extras.getString(MainActivity.Companion.Extras.USER_URL))
            }
            repoLanguage.text = extras.getString(MainActivity.Companion.Extras.LANGUAGE) ?: ""
            repoCreatedDate.text = extras.getString(MainActivity.Companion.Extras.CREATED_AT) ?: ""
            repoUpdatedAt.text = extras.getString(MainActivity.Companion.Extras.UPDATED_AT) ?: ""
            repoButton.setOnClickListener {
                callWebBrowser(extras.getString(MainActivity.Companion.Extras.REPO_URL))
            }
            closeButton.setOnClickListener {
              finish()
            }
        }
    }

    private fun callWebBrowser(url: String?){
        if (URLUtil.isValidUrl(url)) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            baseContext.startActivity(intent)
        }
    }
}