package dev.mooner.pages

import dev.mooner.templates.PageTemplate
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.span
import javax.management.Query.div

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
                            src = "/fishbucket/static/$randomImg"
                        }
                    }
                }
                div(classes = "4u$")
            }
        }
    }
}