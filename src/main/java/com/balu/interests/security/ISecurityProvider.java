package com.balu.interests.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class ISecurityProvider extends AbstractUserDetailsAuthenticationProvider {

	
	public static final String SECRET = "balajisecretkey";
	public static final long EXPIRATION_TIME = 864_000_000;


	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/auth/register";


	@Override
	@Transactional(readOnly = true)
	protected AuthenticatedUser retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		JwtAuthenticationToken jwtToken = (JwtAuthenticationToken)authentication;

		return this.parseJwtToken(jwtToken.getToken());
	}

	@Override
	public boolean supports(Class<? extends Object> type) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(type);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	private AuthenticatedUser parseJwtToken(String token) {

		Claims claims =Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(claims.get("roles").toString()));


		/*for (Role role : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		 */
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

		return new AuthenticatedUser(Long.valueOf(claims.getId()), claims.getSubject(), token, grantedAuthorities);


	}

}
