package club.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import club.DAO.News;
import club.DAO.User;
import club.EJB.interfaces.LocalNews;
import club.EJB.interfaces.LocalUser;

@Path("/news")
public class NewsResource {
	
	@EJB
	LocalNews newsEJB;
	
	@EJB
	LocalUser userEJB;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		System.out.println();
		return userEJB.getAll();
		
	
	
	}
	
	@Path("/news")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getAllNews(){
		System.out.println();
		return newsEJB.getAll();
		
	
	
	}
	
	
	
}
