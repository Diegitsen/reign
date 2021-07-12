package com.diegitsen.reignchallenge.data.entity

data class Hit(
    val created_at: String,
    val story_title: String,
    val story_url: String,
    val author: String,
    val status: Boolean
) {
}
