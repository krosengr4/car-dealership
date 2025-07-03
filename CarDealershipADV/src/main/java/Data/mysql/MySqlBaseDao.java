package Data.mysql;

import configurations.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlBaseDao {

	private final DataSource dataSource;

	public MySqlBaseDao(DataSource dataSource) {
		this.dataSource = DatabaseConfig.setDataSource();
	}

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
