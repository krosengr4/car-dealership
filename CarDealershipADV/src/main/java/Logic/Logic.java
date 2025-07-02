package Logic;

import UI.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Logic {

	static BasicDataSource dataSource = new BasicDataSource();
	static UserInterface ui = new UserInterface();

	public static void setDataSource() {
		dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership_db");
		dataSource.setUsername("root");
		dataSource.setPassword(System.getenv("SQL_PASSWORD"));
	}

}
