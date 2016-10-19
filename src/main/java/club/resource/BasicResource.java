package club.resource;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
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
		return Link.fromUri(uri).rel("self").build();
	}
	
	protected Link appendResourceToSelf(Class<? extends BasicResource> resourceClass, String relName) {
		// URI selfUri = getAbsolutePathURIFromContext();
		URI selfUri  = getSelfLink().getUriBuilder().build();
		System.out.println("SelfLink: " + selfUri.toString());
		String resourceURL = 
				selfUri.getPath() + 
				UriBuilder.fromResource(resourceClass).build().getPath().replaceAll("/", "");
		Link resourceLink = Link.fromUri(resourceURL)
				//.baseUri(selfUri.getPath())
				.rel(relName)
				.build();
		return resourceLink;
	}

}
