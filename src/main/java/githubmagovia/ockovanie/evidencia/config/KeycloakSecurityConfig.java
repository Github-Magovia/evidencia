package githubmagovia.ockovanie.evidencia.config;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@org.keycloak.adapters.springsecurity.KeycloakConfiguration
@ConditionalOnProperty(name = "security.config.use-keycloak", havingValue = "true")
class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider
                = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
                new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
                new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
                http.csrf().disable()
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/api/vaccinations*")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/**")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/**")
                    .hasRole("ADMIN")
                    .regexMatchers(HttpMethod.GET, "/api/people/\\d+")
                    .hasRole("ADMIN")
                    .regexMatchers(HttpMethod.GET, "/api/vaccines/\\d+")
                    .hasRole("ADMIN")
                    .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
    }
}
