package club.resource;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

public class JAXRS extends Application {

	public JsonArray get() {
		System.out.println("AAAAPAAAAA____________---------");
		Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		WebTarget path = client.target("…");
		return path.request().get(JsonArray.class);
	}

}
