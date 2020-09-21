package com.yangjl.spiders


fun main() {
    val params = mutableListOf<String>()
    params.add("k")
    val values = mutableListOf<String>()
    values.add("设计模式")
    val rule = Rule(
        url = "https://www.wanandroid.com/article/query",
        params = params,
        values = values,
        type = Rule.CLASS,
        resultTagName = "alink"
    )
    val datas = extract(rule)
    datas.forEach {
        println(it.linkHref)
        println(it.linkText)
        println("----------------------------------")
    }
}