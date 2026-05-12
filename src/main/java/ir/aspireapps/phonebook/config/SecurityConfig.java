package ir.aspireapps.phonebook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ir.aspireapps.phonebook.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Value("${security.rememberme.key}")
	private String rememberMeKey;

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    AuthenticationManagerBuilder builder =
	            http.getSharedObject(AuthenticationManagerBuilder.class);

	    builder
	        .userDetailsService(customUserDetailsService)
	        .passwordEncoder(passwordEncoder);

	    return builder.build();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/",
							"/register/**",
							"/login",
							"/css/**",
							"/js/**",
							"/img/**",
							"/bootstrap/**"
							).permitAll()
					.anyRequest().authenticated())
			
			.formLogin(form -> form
					.loginPage("/login")
					.loginProcessingUrl("/perform_login")
					.usernameParameter("email")
					.passwordParameter("password")
					.defaultSuccessUrl("/contacts", true)
					.failureUrl("/login?error=true")
					.permitAll())
			
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID", "remember-me")
					.permitAll())
			
			.rememberMe(r -> r
					.key(rememberMeKey)
					.tokenValiditySeconds(60 * 60 * 24 * 7)
					.userDetailsService(customUserDetailsService));
		
		return http.build();						
	}
}
