package club.resource;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import club.DAO.Comment;
import club.EJB.interfaces.LocalComment;

@RequestScoped
@Path("/comments")
public class CommentResource extends BasicResource {
	
	// hur kommer man åt 1an i  /messages/1/comments/ 
	// vi kan komma åt den via @PathParam också
	
	
	@EJB
	LocalComment commentEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComments(@Context UriInfo uriInfo, @PathParam("post_id") int postId) {
		
		System.out.println("INSIDE COMMENT RESOURCE GEEET: " + uriInfo.getAbsolutePath().toString());
		System.out.println("post id is : " + postId);
		
		System.out.println("commentEJB = " + commentEJB);
		List<Comment> comments = commentEJB.getAllByPostId(postId);
		return Response.status(Status.ACCEPTED)
				.entity(comments)
				.links(super.getSelfLink())
				.build();
	}
	
	@GET
	@Path("/{commentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentById(
			@Context UriInfo uriInfo,
			@PathParam("idcommentId") int commentId, 
			@PathParam("messageId") int messageId) { // OBS : detta låser oss till att
				// .. bara använda resursen när vi får med oss messageId. 
		// dsv inte direkt via api/comments/1 
		
		
//		String uri = uriInfo.getBaseUriBuilder()
//				.path(MessageResources.class) // path fram till /resources/
//				.path("" + messageId)
//				.path(CommentResource.class)
//				// här skulle vi kunna lägga till ett id eller liknande
//				// funkar likadant för resurser path(AnotherResource.class)
//				
//				.build() 
//				.toString();
		
		
		return "nada";
	}
	
	
}
