package dev.mooner.plugins

import dev.mooner.pages.mainPage
import dev.mooner.pages.notFoundPage
import dev.mooner.templates.PageTemplate
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.request.*

@KtorExperimentalLocationsAPI
fun Application.configureRouting() {
    install(Locations) {
    }

    install(StatusPages) {
        exception<NotFoundException> {
            call.respondHtmlTemplate(PageTemplate()) {
                notFoundPage()
            }
        }
        status(HttpStatusCode.NotFound) {
            call.respondHtmlTemplate(PageTemplate()) {
                notFoundPage()
            }
        }
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> {
            call.respond(HttpStatusCode.Forbidden)
        }
    }

    routing {
        route("/fishbucket") {
            static("static") {
                preCompressed {
                    resources("static/assets/css")
                    resources("static/assets/images")
                }
            }

            get {
                call.respondHtmlTemplate(PageTemplate()) {
                    mainPage()
                }
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
