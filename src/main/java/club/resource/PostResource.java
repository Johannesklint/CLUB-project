package club.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import club.DAO.News;
import club.DAO.User;
import club.EJB.interfaces.LocalNews;
import club.EJB.interfaces.LocalPost;
import club.EJB.interfaces.LocalUser;

@Path("/posts")
public class PostResource {
	
	@EJB
	LocalPost postEJB;
	
	@EJB
	LocalUser userEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPosts(){
		return Response.accepted(postEJB.getAll()).build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id){
		return Response.accepted(postEJB.getById(id)).build();
	}
	
	
	
}
