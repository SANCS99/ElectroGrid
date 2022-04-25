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

@Path("/notice")
public class NoticeController {

	Notice_Model notices =new Notice_Model();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_data)
	{
		JsonObject json_parser = new JsonParser().parse(json_data).getAsJsonObject();

		if(json_parser.get("account_id").getAsString()!=""&&json_parser.get("b_id").getAsString()!=""&&json_parser.get("b_amount").getAsString()!=""&&json_parser.get("c_name").getAsString()!=""&&json_parser.get("p_method").getAsString()!=""&&json_parser.get("description").getAsString()!=""&&json_parser.get("date").getAsString()!=""&&json_parser.get("c_nic").getAsString()!=""&&json_parser.get("type").getAsString()!="") {

			notices.addNotice(json_parser.get("account_id").getAsString(),json_parser.get("b_id").getAsString(),Double.parseDouble(json_parser.get("b_amount").getAsString()),json_parser.get("c_name").getAsString(),json_parser.get("p_method").getAsString(),json_parser.get("description").getAsString(),json_parser.get("date").getAsString(),json_parser.get("c_nic").getAsString(),json_parser.get("type").getAsString());
			
			JSONObject output = new JSONObject();
			output.put("success", notices.getRes());
			
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

		if(json_parser.get("id").getAsString()!=""&&json_parser.get("account_id").getAsString()!=""&&json_parser.get("b_id").getAsString()!=""&&json_parser.get("b_amount").getAsString()!=""&&json_parser.get("c_name").getAsString()!=""&&json_parser.get("p_method").getAsString()!=""&&json_parser.get("description").getAsString()!=""&&json_parser.get("date").getAsString()!=""&&json_parser.get("c_nic").getAsString()!=""&&json_parser.get("type").getAsString()!="") {

			notices.editNotice(Integer.parseInt(json_parser.get("id").getAsString()),json_parser.get("account_id").getAsString(),json_parser.get("b_id").getAsString(),Double.parseDouble(json_parser.get("b_amount").getAsString()),json_parser.get("c_name").getAsString(),json_parser.get("p_method").getAsString(),json_parser.get("description").getAsString(),json_parser.get("date").getAsString(),json_parser.get("c_nic").getAsString(),json_parser.get("type").getAsString());
			
			JSONObject output = new JSONObject();
			output.put("success", notices.getRes());
	
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
	
			notices.deleteNotice(Integer.parseInt(json_parser.get("id").getAsString()));
			
			JSONObject output = new JSONObject();
			output.put("success", notices.getRes());
	
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
		return notices.getNotice();
	}
	
}
