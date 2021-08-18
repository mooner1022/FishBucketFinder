package dev.mooner.templates

import dev.mooner.PAGE_TITLE
import io.ktor.html.*
import kotlinx.html.*

class PageTemplate: Template<HTML> {
    val content = Placeholder<FlowContent>()
    val headTitle = Placeholder<FlowContent>()
    val pageSubTitle = Placeholder<FlowContent>()
    val pageTitle = Placeholder<FlowContent>()
    override fun HTML.apply() {
        head {
            title(content = "내 양동이 찾기")
            meta { charset = "utf-8" }
            meta {
                name = "viewport"
                content = "width=device-width, initial-scale=1"
            }
            link {
                rel = "stylesheet"
                href = "static/main.css"
            }
        }
        body {
            section {
                id = "One"
                classes = setOf("wrapper", "style3")
                div {
                    classes = setOf("inner")
                    header {
                        classes = setOf("align-center")
                        p {
                            + PAGE_TITLE
                        }
                        h2 {
                            insert(headTitle)
                        }
                    }
                }
            }
            section {
                id = "Two"
                classes = setOf("wrapper", "style2")
                div {
                    classes = setOf("inner")
                    div {
                        classes = setOf("box")
                        div {
                            classes = setOf("content")
                            header {
                                classes = setOf("align-center")
                                p {
                                    insert(pageSubTitle)
                                }
                                h2 {
                                    insert(pageTitle)
                                }
                            }
                            insert(content)
                        }
                    }
                }
            }
            footer {
                id = "footer"
                div {
                    classes = setOf("copyright")
                    + "2021 © Mooner 무너. All rights reserved."
                }
            }
        }
    }
}