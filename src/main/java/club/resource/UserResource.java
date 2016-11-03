package club.resource;

import static javax.ws.rs.core.Response.Status.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import club.DAO.user.User;
import club.DAO.user.User.ApprovedState;
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
		user.setId(null); // id can not be specified, or else this will, in entity manager, be interpreted as an update
		user.setApprovedState(ApprovedState.DENIED);
		user.setAdmin(false);
		user.setBirthday(new java.sql.Date(0));
		user.setHMACPassword(user.getPassword());
		
		// TODO: make this work.. need to add newly created id to url
		List<Link> links = Arrays.asList(super.getSelfLink());
		RESTLinkable<User> createdUser = new RESTLinkable<User>(userEJB.save(user), links);
		
		return Response.status(Status.CREATED)
				.entity(createdUser)
				.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user, @PathParam("id") int id ){
		if(user.getId() == 0){
			HashMap<String, String> errorMsg = new HashMap<>();
			errorMsg.put("Error", "Json must have id");
			return Response.status(Status.PRECONDITION_FAILED).entity(errorMsg).build();
		}
		user.setBirthday(new java.sql.Date(0));
		user.setHMACPassword(user.getPassword());
		User updatedUser = userEJB.update(user);
		
		return Response.status(OK)
				.entity(new RESTLinkable<User>(updatedUser, super.getSelfLink()))
				.build();
	}
	
	@Path("/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("user_id") int id){
		User user = userEJB.getById(id);

		return Response.status(OK)
				.entity(new RESTLinkable<User>(user, super.getSelfLink()))
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){
		List<User> users = userEJB.getAll();
		Link getByIdLink = new Link(super.getAbsolutePathURIFromContext() + "/id", "Get user by id");
		Link selfLink = super.getSelfLink();
		
		return Response.status(users.size() > 0 ? OK : NO_CONTENT)
				.entity(new RESTLinkable<List<User>>(users, Arrays.asList(selfLink, getByIdLink)))
				.build();		
	}
	
}
