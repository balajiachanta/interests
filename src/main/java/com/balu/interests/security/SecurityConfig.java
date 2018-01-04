package com.balu.interests.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ISecurityProvider securityProvider;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("user").password("user").authorities("user");
		auth.authenticationProvider(securityProvider);
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManager());
		authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		return authenticationTokenFilter;
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterBefore(
				authenticationTokenFilterBean(), BasicAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/h2/**").permitAll();
		
		http.httpBasic().disable().and().authorizeRequests()
        .antMatchers("/api/**")
        .authenticated();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();

		
		//http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
//		 http
//         .httpBasic()                      // it indicate basic authentication is requires
//         .and()
//         .authorizeRequests() // /index will be accessible directly, no need of any authentication
//         .anyRequest().authenticated();
	}
*/	
	
	
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		// we don't need CSRF because our token is invulnerable
		.csrf().disable()
		//.antMatcher("/secure/test/**")
		
		//.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/h2/**").permitAll()

		// All urls must be authenticated (filter for token always fires (/**)
		//.authorizeRequests().anyRequest().authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic().disable()
		// Call our errorHandler if authentication/authorisation fails
		//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		//.and()
		// don't create session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //.and()
		// Custom JWT based security filter
	httpSecurity
		.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();
		httpSecurity.headers().frameOptions().disable();
	}
	
	
	
	
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/resources/**");
    }
	
}
