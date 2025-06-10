package webvet.v1.infraestructure.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import webvet.v1.infraestructure.security.filters.JwtTokenValidatorFilter;
import webvet.v1.infraestructure.services.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfig {
    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;
    private final UserDetailsService userDetailsService;


    public securityConfig(JwtTokenValidatorFilter jwtTokenValidatorFilter, UserDetailsService userDetailsService) {
        this.jwtTokenValidatorFilter = jwtTokenValidatorFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers( "/api/v1/authentication/**",
                    // Rutas Swagger UI
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    // Rutas API Docs
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/v3/api-docs/swagger-config",
                    // Recursos Swagger
                    "/swagger-resources/**",
                    "/swagger-resources",
                    "/webjars/**",
                    // Configuraciones
                    "/configuration/ui",
                    "/configuration/security").permitAll();
           auth.requestMatchers("/error").permitAll();

        });

        httpSecurity.addFilterBefore(jwtTokenValidatorFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authenticationProvider(authenticationProvider());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
