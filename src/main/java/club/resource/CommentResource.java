package club.resource;
import static javax.ws.rs.core.Response.Status.*;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import club.DAO.comment.Comment;
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
	public Response getComments(@PathParam("post_id") int postId) {
		
		System.out.println("INSIDE COMMENT RESOURCE GEEET: " + uriInfo.getAbsolutePath().toString());
		System.out.println("post id is : " + postId);
		
		System.out.println("commentEJB = " + commentEJB);
		List<Comment> comments = commentEJB.getAllByPostId(postId);
		return Response.status(OK)
				.entity(comments)
				.links(super.getSelfLink())
				.build();
	}
	
	@GET
	@Path("/{comment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentById(
			@PathParam("post_id") int postId,
			@PathParam("comment_id") int commentId) { // OBS : detta låser oss till att
				// .. bara använda resursen när vi får med oss messageId. 
		// dsv inte direkt via api/comments/1 
		
		
		List<Comment> postComments = commentEJB.getAllByPostId(postId);
		Optional<Comment> requestedComment = postComments.stream()
				.filter(c -> c.getId() == commentId)
				.findFirst();
		
		Status responseStatus = 
				requestedComment.isPresent() ? OK : NOT_FOUND;
		
		return Response.status(responseStatus)
				.entity(requestedComment.orElse(new Comment()))
				.links(super.getSelfLink())
				.build();
		
//		String uri = uriInfo.getBaseUriBuilder()
//				.path(MessageResources.class) // path fram till /resources/
//				.path("" + messageId)
//				.path(CommentResource.class)
//				// här skulle vi kunna lägga till ett id eller liknande
//				// funkar likadant för resurser path(AnotherResource.class)
//				
//				.build() 
//				.toString();
		
	}
	
	
}
