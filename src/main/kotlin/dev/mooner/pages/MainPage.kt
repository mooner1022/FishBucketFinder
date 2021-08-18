package dev.mooner.pages

import dev.mooner.PAGE_TITLE_KR
import dev.mooner.templates.PageTemplate
import kotlinx.html.*

fun PageTemplate.mainPage() {
    headTitle { + PAGE_TITLE_KR }
    pageSubTitle { + "2021/05/24 ~ 2021/08/18 사이에 전송된 양동이를 찾을 수 있습니다." }
    pageTitle { + "검색하실 닉네임을 입력해주세요" }
    content {
        div {
            classes = setOf("box", "alt")
            div {
                classes = setOf("row", "50%", "uniform")
                div {
                    classes = setOf("2u")
                    style = "height: 100px"
                }
                div {
                    classes = setOf("12u")
                    form {
                        method = FormMethod.get
                        action = "/fishbucket/search"
                        div {
                            classes = setOf("row", "uniform")

                            div {
                                id = "search"
                                classes = setOf("9u", "12u$(small)")
                                input {
                                    required = true
                                    type = InputType.text
                                    name = "query"
                                    id = "query"
                                    placeholder = "검색할 닉네임 입력..."
                                }
                            }
                            div {
                                id = "button"
                                classes = setOf("3u$", "12u$(small)")
                                input {
                                    classes = setOf("fit")
                                    type = InputType.submit
                                    value = "검색"
                                }
                            }
                        }
                    }
                }
                div {
                    classes = setOf("2u$")
                    style = "height: 150px"
                }
            }
        }
    }
}