package dev.mooner

import dev.mooner.plugins.configureRouting
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private val bucketRegex = "\\[\uD81A\uDD10 AlphaDo \uD81A\uDD10] \\[(.*)] (.*) 님의 양동이[\\s\\S\\r\\n]+─+┬+─([\\s\\S\\r\\n]+)─+┴+─".toRegex()
    private val testStr = """
            |[𖤐 AlphaDo 𖤐] [오전 1:14] 맛있는 망고같은 자몽 님의 양동이
            |───┬───────────────────
            |００１│🔒 [초월] 호수에 비친 은하수 (9825.4cm)
            |───┼───────────────────
            |００２│🔒 [초월] 호수에 비친 은하수 (6346.2cm)
            |───┼───────────────────
            |００２│🔒 [초월] 호수에 비친 은하수 (6346.2cm)
            |───┴───────────────────
        """.trimMargin("|")

    @KtorExperimentalLocationsAPI
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                println("matches= " + testStr.matches(bucketRegex))
                println("match: ${bucketRegex.findAll(testStr).toList().drop(1).joinToString("\n") { it.value } }")
                assertEquals(testStr.matches(bucketRegex), true)
            }
        }
    }
}