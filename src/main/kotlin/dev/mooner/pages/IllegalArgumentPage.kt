package dev.mooner.pages

import dev.mooner.templates.PageTemplate
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.span

fun PageTemplate.illegalArgumentPage() {
    headTitle { + "400: Bad Request" }
    pageSubTitle { + "흐으으으음..." }
    pageTitle { + "뭐라도 찾으시려는건진 모르겠지만 그런 요청을 보내진 마시죠." }
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