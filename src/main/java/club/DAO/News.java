package club.DAO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="NEWS")
public class News extends Post {
	
	public News() {}


}
