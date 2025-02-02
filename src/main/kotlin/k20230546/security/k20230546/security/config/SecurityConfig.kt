package k20230546.security.k20230546.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig
{
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource
    {
        val configuration = CorsConfiguration()
        configuration.apply{
            allowedOrigins = listOf("http://localhost:3000")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE","OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain
    {
        return http
            .cors {it.configurationSource(corsConfigurationSource())}
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf {csrf -> csrf.disable()}
            .build()
    }
}