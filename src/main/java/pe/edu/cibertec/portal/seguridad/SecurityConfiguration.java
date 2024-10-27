package pe.edu.cibertec.portal.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pe.edu.cibertec.portal.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	// @Bean
	// public AuthenticationManager
	// authenticationManager(AuthenticationConfiguration
	// authenticationConfiguration)
	// throws Exception {

	// return authenticationConfiguration.getAuthenticationManager();

	// }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;

	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity

				.csrf(AbstractHttpConfigurer::disable).formLogin(httpForm -> {

					httpForm.loginPage("/login").permitAll();
					// httpForm.defaultSuccessUrl("/", true);

				})

				.authorizeHttpRequests(registry -> {

					registry.requestMatchers("/login", "/registro", "/css/**", "/js/**").permitAll();
					registry.anyRequest().authenticated();

				})

				.build();

	}

	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {

	// auth.authenticationProvider(authenticationProvider());

	// }

}
