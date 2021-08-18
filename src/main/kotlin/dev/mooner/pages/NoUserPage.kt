package dev.mooner.pages

import dev.mooner.PAGE_TITLE_KR
import dev.mooner.templates.PageTemplate
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.span
import javax.management.Query.div

fun PageTemplate.noUserPage() {
    headTitle { +PAGE_TITLE_KR }
    pageSubTitle { +"오류" }
    pageTitle { +"유저를 찾을 수 없습니다." }
    content {
        div {
            classes = setOf("box", "alt")
            div {
                classes = setOf("row", "50%", "uniform")
                div(classes = "4u")
                div {
                    classes = setOf("4u")
                    span {
                        classes = setOf("image", "fit")
                        +"""
                            이 페이지는 [AlphaDo 봇과 함께하는 낚시게임!] 방의 2021/05/24 ~ 2021/08/18 사이에 전송된 양동이만 찾을 수 있습니다.
                            이름이나 방, 기간을 확인하고 다시 시도해주세요.
                        """.trimIndent()
                        img {
                            src = "/fishbucket/static/404-5.gif"
                        }
                    }
                }
                div(classes = "4u$")
            }
        }
    }
}