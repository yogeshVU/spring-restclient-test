package com.goalabs.youtubeapp

import com.nimbusds.oauth2.sdk.token.AccessToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class AuthController(
//    private val youtubeClient: RestClient,
    private val youtube: Youtube,
    private val props: YoutubeProperties,
) {

    @GetMapping("/me")
    fun me(
//        @RegisteredOAuth2AuthorizedClient("google") googleClient: OAuth2AuthorizedClient?
    ): String {
     val auth = SecurityContextHolder
         .getContext()
            .authentication as? OAuth2AuthenticationToken

        println("${auth?.principal} ")

//        println("${googleClient!!.accessToken.tokenValue} }")

//        val  tmp = youtube.details("Ogl3bN7oV0w",
//            googleClient!!.accessToken)
//
        val  tmp = youtube.details(props.id)

        return tmp


    }
}