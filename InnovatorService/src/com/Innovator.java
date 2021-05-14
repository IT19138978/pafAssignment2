package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class Innovator {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_db", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInnovator(String innovatorName, String projName, String price, String project)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into innovator (innovatorID , innovatorName , projName , price , project)"+ " values (?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, innovatorName);
			 preparedStmt.setString(3, projName);
			 preparedStmt.setString(4, price);
			 preparedStmt.setString(5, project);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newInnovator = readInnovator(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newInnovator + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Innovator.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	
	public String readInnovator()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>innovatorName</th><th>projName</th><th>price</th><th>Disctription</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from innovator"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String innovatorID = Integer.toString(rs.getInt("innovatorID")); 
				 String innovatorName = rs.getString("innovatorName");
				 String projName = rs.getString("projName");
				 String price = rs.getString("price");
				 String project = rs.getString("project");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidInnovatorIDUpdate\' name=\'hidInnovatorIDUpdate\' type=\'hidden\' value=\'" + innovatorID + "'>" 
							+ innovatorName + "</td>"; 
				output += "<td>" + projName + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + project + "</td>";

				  
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iid='" + innovatorID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Innovator.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String updateInnovator(String innovatorID, String innovatorName, String projName, String price, String project)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE innovator SET innovatorName=?,projName=?,price=?,project=? WHERE innovatorID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, innovatorName);
			 preparedStmt.setString(2, projName);
			 preparedStmt.setString(3, price);
			 preparedStmt.setString(4, project);
			 preparedStmt.setInt(5, Integer.parseInt(innovatorID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newInnovator = readInnovator();    
			output = "{\"status\":\"success\", \"data\": \"" + newInnovator + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Innovator.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteInnovator(String innovatorID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from innovator where innovatorID=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(innovatorID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newInnovator = readInnovator();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newInnovator + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Innovator.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
