package com.SQLDataBase;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class dataBase1 {

	public static void main(String[] args) {
		
		// Test		
		String emailaddress = "omkaringawale21@gmail.com";
		String zipcode = "222222";
		String fristname = "Omkar";
		String lastname = "Ingawale";
		
		// Input Test Data Using Selenium
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.dollartree.com/email-signup");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(emailaddress);
		driver.findElement(By.xpath("//*[@id='zip']")).sendKeys(zipcode);
		driver.findElement(By.xpath("//div[@class='col-12 col-md-6 half-space indent-30']")).click();
		
		// Connection to DataBase
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String databaseName = "mydatabase";
		String username = "root";
		String password = "Omkar@21";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url+databaseName, username, password);
			
			String sqlQuerry = "SELECT * FROM mydatabase.signupdetails";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sqlQuerry);
			result.next();
			System.out.println(result.getString("emailaddress"));
			System.out.println(result.getString("zipcode"));
			System.out.println(result.getString("fristname"));
			System.out.println(result.getString("lastname"));
			
			if(!result.getString("emailaddress").equals(emailaddress)){
				System.out.println("Email ID is stored wrong");
			}
			if(!result.getString("zipcode").equals(zipcode)){
				System.out.println("ZipCode is stored wrong");
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			if(conn != null){
				conn = null;
			}
		}

		
		
	}

}
