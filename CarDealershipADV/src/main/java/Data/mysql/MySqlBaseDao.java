package Data.mysql;

import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlBaseDao {

	private final DataSource dataSource;

	public MySqlBaseDao(DataSource dataSource) {
		this.dataSource = DatabaseConfig.dataSource();
	}

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
