package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connection.DB;

public class Consumption_Model {
	
	private String res;
	
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getConsumption() {
		PreparedStatement ps;
		String data="";
		
		try {
			
			Connection conn = DB.getConn();
			ps = conn.prepareStatement("SELECT * FROM consumption");
			
			ResultSet res_Set = ps.executeQuery();
			
			data = "<table>"
		            +"<tr>"
		            +"<th style='border-style: dotted;'>ID</th>"
	                +"<th style='border-style: dotted;'>Meter Box ID</th>"
	                +"<th style='border-style: dotted;'>Address</th>"
	                +"<th style='border-style: dotted;'>Unit Size</th>"
	                +"<th style='border-style: dotted;'>Admin</th>"
	                +"<th style='border-style: dotted;'>Electric Activity</th>"
	                +"<th style='border-style: dotted;'>Used Units</th>"
	                +"<th style='border-style: dotted;'>House ID</th>"
	                +"<th style='border-style: dotted;'>Meter Reader ID</th>"
	                +"<th style='border-style: dotted;'>Edit/Delete</th>"
	                +"</tr>";
			
			while (res_Set.next()) {
				
				String button = "<button>Edit</button><button>Delete</button>";
				
				data = data+"<tr><td style='border-style: dotted;'>"+res_Set.getString(1)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(2)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(3)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(4)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(5)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(6)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(7)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(8)+"</td>"
						+ "<td style='border-style: dotted;'>"+res_Set.getString(9)+"</td>"
						+ "<td style='border-style: dotted;'>"+button+"</td>"
					  + "</tr>";
				
			}
			
			ps.close();
			conn.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}
	
	public void addConsumption(String meter_box_id,String address,String unit_size,String admin,String activity,int used_units,int house_id,int meter_reader_id) {
		PreparedStatement ps;
		
		try {
			Connection conn = DB.getConn();
			
			ps = conn.prepareStatement("insert into consumption (meter_box_id, address, unit_size, admin, activity, used_units, house_id, meter_reader_id) values (?,?,?,?,?,?,?,?)");
			ps.setString(1, meter_box_id);
			ps.setString(2, address);
			ps.setString(3, unit_size);
			ps.setString(4, admin);
			ps.setString(5, activity);
			ps.setInt(6, used_units);
			ps.setInt(7, house_id);
			ps.setInt(8, meter_reader_id);
			ps.execute();
			ps.close();
			conn.close();
			setRes("Done");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setRes("Error");
		}
	}

	public void editConsumption(int id,String meter_box_id,String address,String unit_size,String admin,String activity,int used_units,int house_id,int meter_reader_id) {
		PreparedStatement ps;
		
		try {
			Connection conn = DB.getConn();
			
				ps = conn.prepareStatement("UPDATE consumption SET meter_box_id=?, address=?, unit_size=?, admin=?, activity=?, used_units=?, house_id=?, meter_reader_id=? where id=?");
				ps.setString(1, meter_box_id);
				ps.setString(2, address);
				ps.setString(3, unit_size);
				ps.setString(4, admin);
				ps.setString(5, activity);
				ps.setInt(6, used_units);
				ps.setInt(7, house_id);
				ps.setInt(8, meter_reader_id);
				ps.setInt(9,id);
				ps.execute();
				ps.close();
				conn.close();
				setRes("Done");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setRes("Error");
		}
	}

	public void deleteConsumption(int id) {
		PreparedStatement ps;
		
		try {
			Connection conn = DB.getConn();
			
			ps = conn.prepareStatement("DELETE FROM consumption WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
			setRes("Done");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setRes("Error");
		}
	}
	
}
