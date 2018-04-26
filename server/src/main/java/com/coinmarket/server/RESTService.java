package com.coinmarket.server;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coinmarket.response.Message;
import com.coinmarket.service.UserService;

@Path("/")
public class RESTService {
	
	UserService service = new UserService();
	
	@POST 
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Message signup(@FormParam("name")String name,@FormParam("email")String email, @FormParam("password")String password) {
		return service.signup(name, email, password);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Message login (@FormParam("email")String email, @FormParam("password") String password) {
		return service.login(email, password);
	}
	
	@GET
	@Path("/favourites")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getFavourites(@QueryParam("email") String email) {
		
		return service.getFavourites(email);
	}
	
	@POST
	@Path("/favourites")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addFavourites(@FormParam("email")String email, @FormParam("item")String item) {
		return service.addFavourites(email, item);
	}
}
