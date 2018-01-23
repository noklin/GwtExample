package com.example.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.shared.User; 
 
@Path("/api")
public class RestExample {
	
	private static Map<Integer,User> users = new HashMap<>();

	@PostConstruct
	private void init(){
		users.put(0, new User(0, 1, "Anya"));
		users.put(1, new User(1, 1, "Vanya"));
		users.put(2, new User(2, 1, "Galya"));
		users.put(3, new User(3, 1, "Sanya"));
	}
	
	@GET
	@Path("/user/{id}") 
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getUser(@PathParam("id") int id){
		User user = users.get(id);
		if(user == null){
			return Response.status(404).build();
		}else{
			return Response.status(200).entity(user).build();
		}
	}

	@GET
	@Path("/users") 
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<User> getUsers(){
		return users.values().stream().collect(Collectors.toList());
	}

}