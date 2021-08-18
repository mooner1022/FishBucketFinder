package dev.mooner.pages

import dev.mooner.PAGE_TITLE_KR
import dev.mooner.templates.PageTemplate

fun PageTemplate.searchingPage() {
    headTitle { + PAGE_TITLE_KR }
    pageSubTitle { + "검색 중..." }
    pageTitle { + "대화 내역을 검색 중입니다. 잠시 기다려주세요." }
    content {

    }
}