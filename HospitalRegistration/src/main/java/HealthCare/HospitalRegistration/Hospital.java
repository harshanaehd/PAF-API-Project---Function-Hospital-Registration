// Guide ---------------> This is Hospital.java (Model) file Hospital Registration function of HealthCare System PAF.

package HealthCare.HospitalRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Guide ---------------> Under this line Hospital class.

public class Hospital{

	public String hoptId;
	public String hoptName;
	public String hoptAddress;
	public double hoptPNumber;
	public Float hoptCharge;

// Guide ---------------> Under this line database connectivity.
	
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
// Guide ---------------> Under this line database details - DBServer/DBName, user name, password.
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalsdb", "root", "");
			System.out.println("Sucessfully : Database is connected.");
		}catch (Exception e) {
			System.out.println("Error : Database is not connect." );
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String hoptId, String hoptName, String hoptAddress, String hoptPNumber, float hoptCharge){
		String output = "";
		try {
			Connection con = connect();
			if (con == null){
				return "Error : Insert data while connecting to the database.";
			}
			
// Guide ---------------> Under this line set the prepared statement.
			
			String query = " INSERT INTO `hospital`(`hoptId`, `hoptName`, `hoptAddress`, `hoptPNumber`, `hoptCharge`) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);

// Guide ---------------> Under this line wrap the values.

			preparedStatement.setInt(1, hoptId);
			preparedStatement.setString(2, hoptName);
			preparedStatement.setString(3, hoptAddress);
			preparedStatement.setDouble(4, Double.parseDouble(hoptPNumber));
			preparedStatement.setFloat(5, hoptCharge);

// Guide ---------------> Under this line execute the statement.
			
			preparedStatement.execute();
			con.close();
			output = "Successfully : Inserted.";
		}catch (Exception e){
			output = "Error : While inserting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

// Guide --------------->	
	
	public String readHospital() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error : Read data while connecting to the database.";
			}
			
// Guide ---------------> Under this line set the HTML table.
			
			output = "<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Hospital Address</th><th>Hospital Phone Number</th><th>Update</th><th>Delete</th></tr>";
			String query = "select*from hospitals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

// Guide ---------------> Under this line using the loop for iterate results set.
			
			while (rs.next()) {
				String hoptId = Integer.toString(rs.getInt("hoptId"));
				String hoptName = rs.getString("hoptName");
				String hoptAddress = rs.getString("hoptAddress");
				String hoptPNumber = Double.toString(rs.getDouble("hoptPNumber"));
				Float hoptCharge = rs.getFloat("hoptCharge");
				
// Guide ---------------> Under this line insert into the HTML table.
				
				output += "<tr><td>" + hoptName + "</td>";
				output += "<td>" + hoptAddress + "</td>";
				output += "<td>" + hoptPNumber + "</td>";
				output += "<td>" + hoptCharge + "</td>";

				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"hospital.jsp\">"
						+ "<input name=\"btnDelete\" type=\"submit\" value=\"Delete\"class=\"btn btn-danger\">"
						+ "<input name=\"hoptId\" type=\"hidden\" value=\"" + hoptId + "\">" + "</form></td></tr>";
			}
			con.close();
			
// Guide ---------------> End of the HTML table.
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error : While reading the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String hoptId, String hoptName, String hoptAddress, String hoptPNumber, float hoptCharge) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error : Update data while connecting to the database.";
			}
			
// Guide ---------------> Under this line set the prepared statement.
			
			String query = "UPDATE hospitals SET hoptName=?,hoptAddress=?,hoptPNumber=?,hoptCharge=? WHERE hoptId=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
// Guide ---------------> Under this line wrap the values.
			
			preparedStatement.setString(1, hoptName);
			preparedStatement.setString(2, hoptAddress);
			preparedStatement.setDouble(3, Double.parseDouble(hoptPNumber));
			preparedStatement.setFloat(4, hoptCharge);
			preparedStatement.setInt(5, Integer.parseInt(hoptId));
			
// Guide ---------------> Under this line execute the statement.
			
			preparedStatement.execute();
			con.close();
			output = "Successfully : Updated.";
		} catch (Exception e) {
			output = "Error : While reading the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hoptId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error : Delet data while connecting to the database.";
			}
			
// Guide ---------------> Under this line set the prepared statement.
			
			String query = "delete from hospitals where hoptId=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
// Guide ---------------> Under this line wrap the values.
			
			preparedStatement.setInt(1, Integer.parseInt(hoptId));

// Guide ---------------> Under this line execute the statement.
			
			preparedStatement.execute();
			con.close();
			output = "Successfully : Deleted.";
		} catch (Exception e) {
			output = "Error : While deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

// Guide ---------------> End of the Hospital.java (Model) file.