package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pharmacy {

	public Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremgnt?serverTimezone=UTC", "root",
					"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String add_pharmacy(String name, String owner, String address, String email, String phone) {
		String output = "";

		try {
			Connection con = connect();
			
			String query = " insert into pharmacy(p_id,p_name,p_owner,p_address,p_email,p_phone) values(?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, owner);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phone);
			
			preparedStmt.execute();
			con.close();
			String newItems = view();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String view() {
		String output = "";

		try {
			Connection con = connect();

			output = " <table border=\"1\"><tr><th>ID</th><th>Pharmacy Name</th><th>Owner</th>"
					+ "<th>address</th><th>Emailil</th><th>Phone</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from pharmacy";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("p_id"));
				String name = rs.getString("p_name");
				String owner = rs.getString("p_owner");
				String address = rs.getString("p_address");
				String email = rs.getString("p_email");
				String phone = rs.getString("p_phone");

				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + owner + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						 + id + "'>" + "</td></tr>";
			}

			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String update(String id, String name, String owner, String address, String email, String phone) {
		String output = "";
		
		try {
			Connection con = connect();
			
			
			String query = "UPDATE pharmacy SET p_name=?,p_owner=?,p_address=?,p_email=?,p_phone=? WHERE p_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, name);
			preparedStmnt.setString(2, owner);
			preparedStmnt.setString(3, address);
			preparedStmnt.setString(4, email);
			preparedStmnt.setString(5, phone);
			
			preparedStmnt.setInt(6, Integer.parseInt(id));
			
			preparedStmnt.execute();
			con.close();
			String newItems = view();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}catch(Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String remove(String id){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		 
		 String query = "delete from pharmacy where p_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 preparedStmt.setInt(1, Integer.parseInt(id));

		 preparedStmt.execute();
		 con.close();
		 String newItems = view();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage());
	 }
	return output;
	}

}
