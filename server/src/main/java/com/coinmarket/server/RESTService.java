package com.coinmarket.server;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coinmarket.response.Message;
import com.coinmarket.service.UserService;

@Path("/")
public class RESTService {
	
	UserService service = new UserService();
	
	@POST 
	@Path("/signup/{name}/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message signup(@PathParam("name")String name,@PathParam("email")String email, @PathParam("password")String password) {
		return service.signup(name, email, password);
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Message login (@QueryParam("email")String email, @QueryParam("password") String password) {
		return service.login(email, password);
	}
	
	@GET
	@Path("/g/favourites")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getFavourites(@QueryParam("email") String email) {
		return service.favourites(email);
	}
}
