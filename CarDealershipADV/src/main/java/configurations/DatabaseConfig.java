package configurations;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConfig {

	private static final BasicDataSource dataSource = new BasicDataSource();

	public static BasicDataSource setDataSource() {
		dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership_db");
		dataSource.setUsername("root");
		dataSource.setPassword(System.getenv("SQL_PASSWORD"));
		return dataSource;
	}



}
