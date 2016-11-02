package club.backingBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import club.DAO.user.User;
import club.EJB.interfaces.LocalUser;

@Named(value="chatBean")
public class ChatBean {

	@EJB
	private LocalUser userEJB;	

	private Integer chatWithId;
	
	public class Chat {
		
		private String link;
		private String name;

		public Chat(String name, String link) {
			this.link = link;
			this.name = name;
		}
		
		public String getLink() {
			return link;
		}

		public String getName() {
			return name;
		}

	}
	
	@PostConstruct
	public void init() {
		
		chatWithId = null;
		
		Map<String, String> params =  FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap();
		if(params.get("id")!=null)
			chatWithId = Integer.parseInt(params.get("id"));
		
	}
	public User getChatWith() {
		if(chatWithId==null) return null;
		return userEJB.getById(chatWithId);
	}

	public String getChatRoom() {
		if(chatWithId!=null) return null;
		return "main";
	}
	
	public List<Chat> getLinks() {
		List<Chat> links = new ArrayList<>();
		links.add(new Chat("main","r"));
		
		for(User user : userEJB.getAllValidUser()) {
			links.add(new Chat(user.getFullName(), "u"+user.getId()  ));			
		}
		
		return links;
	}

}
