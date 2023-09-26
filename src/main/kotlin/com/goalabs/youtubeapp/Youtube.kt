package com.goalabs.youtubeapp

import com.nimbusds.oauth2.sdk.token.AccessToken
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.net.http.HttpHeaders

@Service
class Youtube(private val client: RestClient) {


    fun details(id: String,
//                accessToken: OAuth2AccessToken
    ): String {
        val response = client.get().
            uri("/videos?id=$id&part=snippet")
//            .header(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer ${accessToken.tokenValue}")
                .retrieve()
            .body(String::class.java)
        return response?.toString() ?: "No response"
    }

}