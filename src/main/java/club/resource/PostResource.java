package club.resource;

import static javax.ws.rs.core.Response.Status.*;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import club.DAO.Post;
import club.EJB.interfaces.LocalPost;

@Path("/posts")
public class PostResource {
	
	@EJB
	LocalPost postEJB;
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPosts(){
		List<Post> posts = postEJB.getAll();
		return Response.status(Status.ACCEPTED)
				.entity(posts)
				.links(getSelfLink())
				.build();
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id){
		Post post = postEJB.getById(id);
		return Response.status(ACCEPTED)
				.entity(post)
				.links(getSelfLink())
				.build();
	}
	
	private URI getAbsolutePathURIFromContext() {
		return uriInfo.getAbsolutePath();
	}
	
	private Link getSelfLink() {
		URI uri = getAbsolutePathURIFromContext();
		return Link.fromUri(uri).rel("self").build();
	}
	
	
	
}
