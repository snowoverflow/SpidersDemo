package com.yangjl.spiders

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun extract(rule: Rule): List<LinkTypeData> {
    val datas = mutableListOf<LinkTypeData>()
    var data: LinkTypeData? = null
    val url = rule.url
    val values = rule.values
    val resultTagName = rule.resultTagName
    val type = rule.type
    val requestType = rule.requestMethod
    val conn = Jsoup.connect(url)
    rule.params.forEach {
        conn.data(it, values[rule.params.indexOf(it)])
    }
    val doc: Document = when (requestType) {
        Rule.GET -> conn.timeout(100000).get()
        else -> conn.timeout(10000).post()
    }
    var results: Elements = Elements()
    when (type) {
        Rule.CLASS -> results = doc.getElementsByClass(resultTagName)
        Rule.ID -> {
            results.add(doc.getElementById(resultTagName))
        }
        Rule.SELECTION -> results = doc.select(resultTagName)
        else -> {
            if (resultTagName.isEmpty()) {
                results = doc.getElementsByTag("body")
            }
        }
    }
    results.forEach { element ->
        val links = element.getElementsByTag("a")
        links.forEach {
            val linkHref = it.attr("href")
            val linkText = it.text()
            data = LinkTypeData(linkHref, linkText)
            datas.add(data!!)
        }
    }

    return datas
}