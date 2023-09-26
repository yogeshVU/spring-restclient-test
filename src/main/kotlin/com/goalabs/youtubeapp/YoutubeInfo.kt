package com.goalabs.youtubeapp

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.stereotype.Component



@Component
class YoutubeInfo(private val youtube: Youtube,
    private val props: YoutubeProperties,
    @RegisteredOAuth2AuthorizedClient("google") googleClient: OAuth2AuthorizedClient?
) {

    companion object{
        private val log = LoggerFactory.getLogger(YoutubeInfo::class.java)
    }
    // fixed delay of 30 seconds
    @Scheduled(fixedDelay = 10000)
    fun info(): String {
        log.info("Getting info for video id: $props.id")
        return try {
            youtube.details(props.id)
        } catch (e: Exception) {
            log.error("Error getting info for video id: $props.id")
            "Error getting info for video id: $props.id"
        }

    }
}