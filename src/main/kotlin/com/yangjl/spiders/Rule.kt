package com.yangjl.spiders

data class Rule(
    var url: String,
    var params: List<String>,
    var values: List<String>,
    var resultTagName: String = "",//对返回的HTML，第一次过滤所用的标签，请先设置type
    var type: Int = ID,//设置resultTagName的类型，默认为ID
    var requestMethod: Int = GET //请求的类型，默认GET
) {
    init {
        require(url.isNotEmpty()) {
            "url can be empty"
        }
    }

    companion object {
        //请求方法
        const val GET = 0
        const val POST = 1

        //筛选的tag名称
        const val CLASS = 0
        const val ID = 1
        const val SELECTION = 2
    }
}