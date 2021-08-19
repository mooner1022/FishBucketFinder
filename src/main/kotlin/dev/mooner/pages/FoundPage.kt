package dev.mooner.pages

import dev.mooner.PAGE_TITLE_KR
import dev.mooner.templates.PageTemplate
import kotlinx.html.*

fun PageTemplate.foundPage(result: Pair<String, String>) {
    headTitle { +PAGE_TITLE_KR }
    pageSubTitle { + "검색 완료" }
    pageTitle { + "닉네임 [${result.first}] 양동이 검색 결과" }
    content {
        div {
            classes = setOf("box", "alt")
            div {
                classes = setOf("row", "50%", "uniform")
                div(classes = "2u")
                div {
                    classes = setOf("8u")
                    ul {
                        classes = setOf("actions")
                        li {
                            unsafe { +"""
                                <a class="button special" data-clipboard-target="#bucket-result" style="background-color:#635D5C">
                                    클립보드에 복사
                                </a>
                                """.trimIndent()
                            }
                        }
                    }
                    pre {
                        code {
                            id = "bucket-result"
                            style = "height: 600px"
                            +result.second
                        }
                    }
                }
                div(classes = "2u$")
            }
            script {
                type = ScriptType.textJavaScript
                src = "/fishbucket/static/clipboard.js"
            }
            unsafe {
                +"""
                    <script>
                        new ClipboardJS('.button');
                    </script>
                """.trimIndent()
            }
        }
    }
}