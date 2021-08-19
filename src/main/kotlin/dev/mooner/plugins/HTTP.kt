package dev.mooner.plugins

import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureHTTP() {
    val maxAgeDay = 24 * 60 * 60
    install(CachingHeaders) {
        options { outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS ->
                    CachingOptions(CacheControl.MaxAge(maxAgeSeconds = maxAgeDay))
                ContentType.defaultForFileExtension("gif") ->
                    CachingOptions(CacheControl.MaxAge(maxAgeSeconds = maxAgeDay))
                else -> null
            }
        }
    }
    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        allowCredentials = true
    }
    install(HttpsRedirect) {
        sslPort = 8443
        permanentRedirect = true
    }
}
