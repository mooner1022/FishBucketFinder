package dev.mooner.pages

import dev.mooner.PAGE_TITLE_KR
import dev.mooner.bucketSearcher
import dev.mooner.templates.PageTemplate
import kotlinx.html.*
import kotlinx.html.dom.document
import org.w3c.dom.Document
import java.util.*
import kotlin.concurrent.schedule

fun PageTemplate.searchingPage(queryName: String) {
    headTitle { +PAGE_TITLE_KR }
    pageSubTitle { +"검색 중..." }
    pageTitle { +"[$queryName] 의 대화 내역을 검색 중입니다. 잠시 기다려주세요." }
    content {
        div {
            classes = setOf("box", "alt")
            div {
                classes = setOf("row", "50%", "uniform")
                div(classes = "2u")
                div {
                    classes = setOf("8u")
                    span {
                        classes = setOf("image", "fit")
                        img {
                            src = "/fishbucket/static/loading.gif"
                        }
                    }
                }
                div(classes = "2u$")
            }
        }
    }
}