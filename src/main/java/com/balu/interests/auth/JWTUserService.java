package com.balu.interests.auth;

import java.util.Date;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balu.interests.db.entity.User;
import com.balu.interests.db.repo.RoleRepository;
import com.balu.interests.db.repo.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class JWTUserService {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static final String SECRET = "balajisecretkey";
	public static final long EXPIRATION_TIME = 864_000_000;


	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/auth/register";


	@PostMapping(value="/iregister")
	@Transactional
	public void register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}


	@CrossOrigin
	@PostMapping(value="/ilogin")
	@Transactional(readOnly = true)
	public void interestLogin(@RequestBody User login,HttpServletResponse res) throws ServletException {

		String jwtToken = null;

		if (login.getUsername() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String email = login.getUsername();
		String password = login.getPassword();

		User user = userRepository.findByUsername(email);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String encodedPassword = user.getPassword();

		if (!bCryptPasswordEncoder.matches(password, encodedPassword)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = this.createJWT(user.getId(),email, "balu");
		user.setAuthToken(jwtToken);

		userRepository.save(user);


		res.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);
	}


	private String createJWT(Long id,String email, String issuer) {


		String token = Jwts.builder().setId(id.toString())
				.setSubject(email).claim("roles", "user")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		return token;
	}

	private void parseJWT(String token) {

		//This line will throw an exception if it is not a signed JWS (as expected)


		//String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			/*String claims = Jwts.parser()
					.setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();*/
			
			Claims claims =Jwts.parser()
					.setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
			
			
			System.out.println(claims.getId());
			System.out.println(claims.getSubject());
			System.out.println(claims.get("roles").toString());

			/* if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
            SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res)
        }

		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
			 */
		}
	}
	
	/*public static void main(String[] args) {
		String jwet = JWTUserService.createJWT(123456L,"sdfafdsfwhat", "balu");
		System.out.println(jwet);
		parseJWT(jwet);
	}*/

}
