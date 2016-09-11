package club.domain;

import java.time.LocalDateTime;
import java.util.List;

public class News extends Post {

	public News(String description, String title, UserBean author, LocalDateTime created, List<UserBean> followers) {
		super(description, title, author, created, followers);
	}

}
