package com.example.githubrestapi.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrestapi.R
import com.example.githubrestapi.RepositoryItem
import com.example.githubrestapi.activity.MainActivity
import com.squareup.picasso.Picasso
import com.example.githubrestapi.activity.RepositoryDetailActivity


class GetAdapter(
    private val list: ArrayList<RepositoryItem>
): RecyclerView.Adapter<GetAdapter.GetViewHolder>() {

    inner class GetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(response: RepositoryItem){
            val repoName = itemView.findViewById<TextView>(R.id.repoName)
            val image = itemView.findViewById<ImageView>(R.id.image)
            val author = itemView.findViewById<TextView>(R.id.author)
            val watchersCount = itemView.findViewById<TextView>(R.id.watchersCount)
            val forksCount = itemView.findViewById<TextView>(R.id.forksCount)
            val issuesCount = itemView.findViewById<TextView>(R.id.issuesCount)
            val detailViewArea = itemView.findViewById<ConstraintLayout>(R.id.detailViewArea)

            // Set layout
            repoName.text = response.name ?: "-"
            if (URLUtil.isValidUrl(response.owner?.avatarUrl)){
                Picasso.get().load(response.owner?.avatarUrl).into(image);
            }
            author.text = response.owner?.login ?: "-"
            watchersCount.text = WATCHERS_LABEL.plus(response.watchersCount?:0.toString())
            forksCount.text = FORKS_LABEL.plus(response.forksCount?:0.toString())
            issuesCount.text = ISSUES_LABEL.plus(response.openIssuesCount?:0.toString())

            // ImageView click event
            val htmlUrl = response.owner?.htmlUrl
            if (URLUtil.isValidUrl(htmlUrl)){
                image.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(htmlUrl)
                    )
                    itemView.context.startActivity(intent)
                }
            }

            // Set Event to go to detail page
            detailViewArea.setOnClickListener {
                val intent = Intent(itemView.context, RepositoryDetailActivity::class.java)
                intent.putExtra(MainActivity.Companion.Extras.THUMBNAIL_URL, response.owner?.avatarUrl)
                intent.putExtra(MainActivity.Companion.Extras.AUTHOR_NAME, response.owner?.login)
                intent.putExtra(MainActivity.Companion.Extras.LANGUAGE, response.language)
                intent.putExtra(MainActivity.Companion.Extras.CREATED_AT, response.createdAt)
                intent.putExtra(MainActivity.Companion.Extras.UPDATED_AT, response.updatedAt)
                intent.putExtra(MainActivity.Companion.Extras.REPO_URL, response.htmlUrl)
                intent.putExtra(MainActivity.Companion.Extras.USER_URL, response.owner?.htmlUrl)

                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_view, parent, false)
        return GetViewHolder(view)
    }

    override fun onBindViewHolder(holder: GetViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    companion object {
        const val WATCHERS_LABEL = "Watchers: "
        const val FORKS_LABEL = "Forks: "
        const val ISSUES_LABEL = "Issues: "
    }
}