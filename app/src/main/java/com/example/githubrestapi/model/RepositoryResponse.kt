package com.example.githubrestapi

import com.google.gson.annotations.SerializedName

data class RepositoryResponse (
    @SerializedName("total_count")
    val id: Int?,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean?,
    @SerializedName("items")
    val items: ArrayList<RepositoryItem>?
)

data class RepositoryItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("watchers_count")
    val watchersCount: Int?,
    @SerializedName("forks_count")
    val forksCount: Int?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int?,
    @SerializedName("visibility")
    val visibility: String?,
    @SerializedName("default_branch")
    val defaultBranch: String?
)

data class Owner(
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("organizations_url")
    val organizationsurl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("site_admin")
    val siteAdmin: String?
)