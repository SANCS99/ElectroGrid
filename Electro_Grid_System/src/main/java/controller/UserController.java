package controller;


import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import java.util.ArrayList;

import com.google.gson.*;
import org.json.simple.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.*;

@Path("/user")
public class UserController {

	User_Model users =new User_Model();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_data)
	{
		JsonObject json_parser = new JsonParser().parse(json_data).getAsJsonObject();

		if(json_parser.get("fname").getAsString()!=""&&json_parser.get("lname").getAsString()!=""&&json_parser.get("mobile").getAsString()!=""&&json_parser.get("email").getAsString()!=""&&json_parser.get("nic").getAsString()!=""&&json_parser.get("address").getAsString()!=""&&json_parser.get("password").getAsString()!=""&&json_parser.get("account_number").getAsString()!="") {

			users.addUser(json_parser.get("fname").getAsString(),json_parser.get("lname").getAsString(),json_parser.get("mobile").getAsString(),json_parser.get("email").getAsString(),json_parser.get("nic").getAsString(),json_parser.get("address").getAsString(),json_parser.get("password").getAsString(),json_parser.get("account_number").getAsString());
			
			JSONObject output = new JSONObject();
			output.put("success", users.getRes());
			
			return output.toString();
			
		}else {
			
			JSONObject output = new JSONObject();
			output.put("success", "required");
			
			return output.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String json_data)
	{
		JsonObject json_parser = new JsonParser().parse(json_data).getAsJsonObject();

		if(json_parser.get("id").getAsString()!=""&&json_parser.get("fname").getAsString()!=""&&json_parser.get("lname").getAsString()!=""&&json_parser.get("mobile").getAsString()!=""&&json_parser.get("email").getAsString()!=""&&json_parser.get("nic").getAsString()!=""&&json_parser.get("address").getAsString()!=""&&json_parser.get("password").getAsString()!=""&&json_parser.get("account_number").getAsString()!="") {

			users.editUser(Integer.parseInt(json_parser.get("id").getAsString()),json_parser.get("fname").getAsString(),json_parser.get("lname").getAsString(),json_parser.get("mobile").getAsString(),json_parser.get("email").getAsString(),json_parser.get("nic").getAsString(),json_parser.get("address").getAsString(),json_parser.get("password").getAsString(),json_parser.get("account_number").getAsString());
			
			JSONObject output = new JSONObject();
			output.put("success", users.getRes());
	
			return output.toString();
			
		}else {
			
			JSONObject output = new JSONObject();
			output.put("success", "required");
			
			return output.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String json_data)
	{
		JsonObject json_parser = new JsonParser().parse(json_data).getAsJsonObject();
		if(json_parser.get("id").getAsString()!="") {
	
			users.deleteUser(Integer.parseInt(json_parser.get("id").getAsString()));
			
			JSONObject output = new JSONObject();
			output.put("success", users.getRes());
	
			return output.toString();
			
		}else {
			
			JSONObject output = new JSONObject();
			output.put("success", "required");
			
			return output.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String json_data)
	{
		return users.getUser();
	}
	
}
