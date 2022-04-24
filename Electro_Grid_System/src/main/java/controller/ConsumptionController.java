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

@Path("/consumption")
public class ConsumptionController {

	Consumption_Model consumption =new Consumption_Model();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_data)
	{
		JsonObject json_parser = new JsonParser().parse(json_data).getAsJsonObject();

		if(json_parser.get("meter_box_id").getAsString()!=""&&json_parser.get("address").getAsString()!=""&&json_parser.get("unit_size").getAsString()!=""&&json_parser.get("admin").getAsString()!=""&&json_parser.get("activity").getAsString()!=""&&json_parser.get("used_units").getAsString()!=""&&json_parser.get("house_id").getAsString()!=""&&json_parser.get("meter_reader_id").getAsString()!="") {

			consumption.addConsumption(json_parser.get("meter_box_id").getAsString(),json_parser.get("address").getAsString(),json_parser.get("unit_size").getAsString(),json_parser.get("admin").getAsString(),json_parser.get("activity").getAsString(),Integer.parseInt(json_parser.get("used_units").getAsString()),Integer.parseInt(json_parser.get("house_id").getAsString()),Integer.parseInt(json_parser.get("meter_reader_id").getAsString()));
			
			JSONObject output = new JSONObject();
			output.put("success", consumption.getRes());
			
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

		if(json_parser.get("id").getAsString()!=""&&json_parser.get("meter_box_id").getAsString()!=""&&json_parser.get("address").getAsString()!=""&&json_parser.get("unit_size").getAsString()!=""&&json_parser.get("admin").getAsString()!=""&&json_parser.get("activity").getAsString()!=""&&json_parser.get("used_units").getAsString()!=""&&json_parser.get("house_id").getAsString()!=""&&json_parser.get("meter_reader_id").getAsString()!="") {

			consumption.editConsumption(Integer.parseInt(json_parser.get("id").getAsString()),json_parser.get("meter_box_id").getAsString(),json_parser.get("address").getAsString(),json_parser.get("unit_size").getAsString(),json_parser.get("admin").getAsString(),json_parser.get("activity").getAsString(),Integer.parseInt(json_parser.get("used_units").getAsString()),Integer.parseInt(json_parser.get("house_id").getAsString()),Integer.parseInt(json_parser.get("meter_reader_id").getAsString()));
			
			JSONObject output = new JSONObject();
			output.put("success", consumption.getRes());
	
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
	
			consumption.deleteConsumption(Integer.parseInt(json_parser.get("id").getAsString()));
			
			JSONObject output = new JSONObject();
			output.put("success", consumption.getRes());
	
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
		return consumption.getConsumption();
	}
	
}
