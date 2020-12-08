package covid_api;
import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void random(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Please enter \n 1 for adding\n 2 for fetching");
		Scanner sc= new Scanner(System.in);
		int choice = sc.nextInt();
		
		String ustate;
		int utested = 0;
		int uconfirmed = 0;
		int ucured = 0;
		int uexpired = 0;
		
		while(true) {
			if(choice == 1) {
				System.out.println("Please enter details");
				Scanner sc1=new Scanner(System.in);
				ustate=sc1.nextLine();
				utested=sc.nextInt();
				uconfirmed=sc.nextInt();
				ucured=sc.nextInt();
				uexpired=sc.nextInt();
				break;
			}
			else if(choice ==2){
				System.out.println("Please enter State Name to fetch");
				Scanner sc1=new Scanner(System.in);
				ustate=sc1.nextLine();
				break;
			}
			System.out.println("Please enter \n 1 for adding\n 2 for fetching");
			choice = sc.nextInt();
		}
		
		boolean exists=false;
		
		System.out.println("Testing JDBC MySQL Connection");
		
		String SQL_Query="select * from CovidData where state = ?";
		String SQL_Query1="insert into CovidData values (?, ?, ?, ?, ?)";
		String SQL_Query2="update CovidData set tested=?, confirmed=?, cured=?, expired=? where state=?";
		
		try(Connection conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/sys","root", "malleswari123456789");
				PreparedStatement preparedStatement=conn.prepareStatement(SQL_Query)){
				preparedStatement.setString(1, ustate);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
            //System.out.println("The details are");
	            while(resultSet.next()) {
	            	String state = resultSet.getString("state");
	                int tested = resultSet.getInt("tested");
	                int confirmed = resultSet.getInt("confirmed");
	                int cured = resultSet.getInt("cured");
	                int expired = resultSet.getInt("expired");
	                //System.out.println(state+" "+ustate);
	                //if(state.equals(ustate))
	                exists=true;
	                System.out.println("Details of requested state \n"+state + ", " + tested + ", " + confirmed +", " + cured + ", " + expired);
	            }
	            if(!exists&&choice==1) {
	            	System.out.println("in");
	            	try(PreparedStatement preparedStatement1=conn.prepareStatement(SQL_Query1)){
	            		preparedStatement1.setString(1, ustate);
	            		preparedStatement1.setInt(2, utested);	
	            		preparedStatement1.setInt(3, uconfirmed);
	            		preparedStatement1.setInt(4, ucured);
	            		preparedStatement1.setInt(5, uexpired);
	            		preparedStatement1.execute();
	            		System.out.println("Done");
	            	}
	            }
	            else if(choice==1) {
	            	try(PreparedStatement preparedStatement1=conn.prepareStatement(SQL_Query2)){
	            		preparedStatement1.setInt(1, utested);	
	            		preparedStatement1.setInt(2, uconfirmed);
	            		preparedStatement1.setInt(3, ucured);
	            		preparedStatement1.setInt(4, uexpired);
	            		preparedStatement1.setString(5, ustate);
	            		preparedStatement1.execute();
	            		System.out.println("Done");
	            	}
	            }
			}
		}
		catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
