package covid_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.*;

@RestController
public class FetchDetails {
	@RequestMapping("/2/{state}")
	public Map<String, String> fetch(@PathVariable("state") String ustate) {
		
		System.out.println("Testing JDBC MySQL Connection");
		String query = "SELECT * FROM CovidData WHERE state=?";
		HashMap<String, String> map = new HashMap<>();
		boolean exists = false;
		try(Connection conn= DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sys", "root", "malleswari123456789");
			PreparedStatement preparedStatement = conn.prepareStatement(query)){
			preparedStatement.setString(1, ustate);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					map.put("State", resultSet.getString("state"));
					map.put("Tested", String.valueOf(resultSet.getInt("tested")));
					map.put("Confirmed", String.valueOf(resultSet.getInt("confirmed")));
					map.put("Cured", String.valueOf(resultSet.getInt("cured")));
					map.put("Expired", String.valueOf(resultSet.getInt("expired")));
					exists=true;
				}
			}
		}
		catch(SQLException e){
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(!exists)
			map.put(ustate, "None");
		return map;
	}
}
