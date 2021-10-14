package com.example.githubrestapi

import com.google.gson.annotations.SerializedName

data class fPostResponse (
    @SerializedName("id")
    val id: Int,

    val repoName: String?,
    val authorName: String?,
    val imageThumbnail: String?,
    val watcherNumber: Int?,
    val ForkNumber: Int?,
    val issueNumber: Int?
)

//Repository name
//● Name of the author
//● Thumbnail image of the author
//● Number of watchers
//● Number of forks
//● Number of issues