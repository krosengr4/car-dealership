package Logic;

import UI.UserInterface;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

public class Logic {

	static BasicDataSource dataSource = DatabaseConfig.dataSource();
	static UserInterface ui = new UserInterface();



}
