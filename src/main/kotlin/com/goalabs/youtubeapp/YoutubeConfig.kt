package com.goalabs.youtubeapp

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestClient



@ConfigurationProperties(prefix = "youtube")
data class YoutubeProperties(
    val url:   String,
    val title:  String,
    val principal:  String,
    val id: String
)

@Configuration
@EnableConfigurationProperties(YoutubeProperties::class)
@EnableScheduling
class YoutubeConfig(private val props: YoutubeProperties) {
    @Bean
    fun client(builder: RestClient.Builder, interceptor: Oauth2TokenProvider): RestClient {
        return builder
            .requestInterceptor(interceptor)
            .baseUrl(props.url).
            build()
    }
}