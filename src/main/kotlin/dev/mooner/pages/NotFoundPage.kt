package dev.mooner.pages

import dev.mooner.PAGE_TITLE
import dev.mooner.PAGE_TITLE_KR
import dev.mooner.templates.PageTemplate
import kotlinx.html.*

fun PageTemplate.notFoundPage() {
    headTitle { + "404: Not Found" }
    pageSubTitle { + "잠깐! 어딜 보시는 건가요!" }
    pageTitle { + "이곳에는 아무것도 없습니다. 어떻게 들어온거죠?" }
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
                        img {
                            val randomImg = "404-${(1..6).random()}.gif"
                            src = "/static/$randomImg"
                        }
                    }
                }
                div(classes = "4u$")
            }
        }
    }
}