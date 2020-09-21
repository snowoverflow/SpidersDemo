package com.yangjl.spiders

data class LinkTypeData(
    val linkHref: String,
    val linkText: String,
    val summary: String? = null,
    val content: String? = null
) {
}