package com.pavlesamardzic.assignmentnews.data

data class Post(
        val id: Int,
        val userId: Int,
        val title: String,
        val body: String,
        var userEmail: String) {}