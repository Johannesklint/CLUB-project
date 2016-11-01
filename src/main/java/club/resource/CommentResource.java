package club.resource;
import static javax.ws.rs.core.Response.Status.*;

import java.util.Arrays;
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
	
	@EJB
	LocalComment commentEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComments(@PathParam("post_id") int postId) {
		
		System.out.println("INSIDE COMMENT RESOURCE GEEET: " + uriInfo.getAbsolutePath().toString());
		System.out.println("post id is : " + postId);
		
		System.out.println("commentEJB = " + commentEJB);
		List<Comment> comments = commentEJB.getAllByPostId(postId);
		
		return Response.status(comments.size() > 0 ? OK : NO_CONTENT)
				.entity(new RESTLinkable<List<Comment>>(comments, Arrays.asList(super.getSelfLink())))
				.build();
	}
	
	@GET
	@Path("/{comment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentById(
			@PathParam("post_id") int postId,
			@PathParam("comment_id") int commentId) { 
		
		List<Comment> postComments = commentEJB.getAllByPostId(postId);
		Optional<Comment> requestedComment = postComments.stream()
				.filter(c -> c.getId() == commentId)
				.findFirst();
		
		Status responseStatus = 
				requestedComment.isPresent() ? OK : NOT_FOUND;
		
		List<Link> links = Arrays.asList(super.getSelfLink());
		RESTLinkable<Comment> comment = new RESTLinkable<Comment>(requestedComment.orElse(new Comment()), links);
		
		return Response.status(responseStatus)
				.entity(comment)
				.build();
	
	}
	
}
