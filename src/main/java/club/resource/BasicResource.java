package club.resource;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

public abstract class BasicResource {
	
	@Context
	UriInfo uriInfo;
	
	protected URI getAbsolutePathURIFromContext() {
		
		return uriInfo.getAbsolutePath();
	}
	
	protected Link getSelfLink() {
		URI uri = getAbsolutePathURIFromContext();
		return Link.fromUri(uri).rel("self").build();
	}

}
