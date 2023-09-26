package com.goalabs.youtubeapp

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.stereotype.Component

@Component
class Oauth2TokenProvider(
    private val service: OAuth2AuthorizedClientService,
    private val props: YoutubeProperties,
) : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution,
    ): ClientHttpResponse {

        val token = token()
        if( token != null ) {
            request.headers.add(HttpHeaders.AUTHORIZATION, "Bearer $token")
            return execution.execute(request, body)
        }
        throw Exception("No token found")
    }

    fun token() : String? {


        println("Calling token for ${props.principal}")
        val client: OAuth2AuthorizedClient? = service.loadAuthorizedClient<OAuth2AuthorizedClient>(
            "google",
            props.principal
        )
        println("Client access-token: ${client?.accessToken?.tokenValue}")
        return client?.accessToken?.tokenValue
    }
}