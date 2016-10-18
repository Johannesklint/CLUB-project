package club.resource;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWTSigner;


@Stateless
public class LoginResource {
	
	final String issuer = "https://mydomain.com/";
	final String secret = "{{secret used for signing}}";

	final long iat = System.currentTimeMillis() / 1000L; // issued at claim 
	final long exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds

	final JWTSigner signer = new JWTSigner(secret);
	final HashMap<String, Object> claims = new HashMap<String, Object>();
	
	
	@POST
	@Path("/login/{user_name}{password}")
	public Response authenticateUser(
			@PathParam("user_name") String userName, 
			@PathParam("password") String password) {
		// https://github.com/auth0/java-jwt
		return null;
	}
}
