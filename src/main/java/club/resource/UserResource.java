package club.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import club.DAO.User;
import club.DAO.User.ApprovedState;
import club.EJB.interfaces.LocalUser;

@Stateless
@Path("/users")
public class UserResource extends BasicResource{
	
	@EJB
	LocalUser userEJB;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user){
		user.setApprovedState(ApprovedState.DENIED);
		user.setAdmin(false);
		user.setBirthday(new java.sql.Date(0));
		user.setHMACPassword(user.getPassword());
		User user2 = userEJB.create(user);
		
		return Response.accepted(user2).build();
	}
	
}
