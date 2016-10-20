package club.DAO.news;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import club.DAO.post.Post;

@Entity
@DiscriminatorValue(value="NEWS")

@NamedQueries({
    @NamedQuery(name="News.findAll", query="SELECT n FROM News n")
}) 
public class News extends Post {
	
	public News() {}


}
