package club.resource;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class BasicResource {
	
	@Context
	UriInfo uriInfo;
	
	protected URI getAbsolutePathURIFromContext() {
		return uriInfo.getAbsolutePath();
	}
	
	protected Link getSelfLink() {
		URI uri = getAbsolutePathURIFromContext();
		return new Link("self", uri.getPath());
	}
	
	protected Link appendResourceToSelf(Class<? extends BasicResource> resourceClass, String relName) {
		// URI selfUri = getAbsolutePathURIFromContext();
		String selfHref  = getSelfLink().getHref();
		String resourceURL = 
				selfHref + "/" +
				UriBuilder.fromResource(resourceClass).build().getPath().replaceAll("/", "");
		return new Link(relName, resourceURL);
	}

}
