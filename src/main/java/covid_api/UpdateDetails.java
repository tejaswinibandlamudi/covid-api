package covid_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.sql.*;

@RestController
public class UpdateDetails {
	@RequestMapping("/1/{state}/{tested}/{confirmed}/{cured}/{expired}")
	public String update(
			@PathVariable("state") String state,
			@PathVariable("tested") int tested,
			@PathVariable("confirmed") int confirmed,
			@PathVariable("cured") int cured,
			@PathVariable("expired") int expired) {
		
		String query = "INSERT INTO CovidData VALUES (?, ?, ?, ?, ?)";
		boolean done=false;
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sys", "root", "malleswari123456789");
				PreparedStatement preparedStatement = conn.prepareStatement(query)){
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, tested);
			preparedStatement.setInt(3, confirmed);
			preparedStatement.setInt(4, cured);
			preparedStatement.setInt(5, expired);
			preparedStatement.execute();
			done=true;
		}
		catch(SQLException e) {
			System.err.format("SQL State: %s \n %s", e.getSQLState(), e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return (done?"Succesfully Updated":"Already record exists, Please Update instead");
	}
}
