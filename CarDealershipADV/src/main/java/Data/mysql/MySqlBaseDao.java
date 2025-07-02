package Data.mysql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlBaseDao {

	private final BasicDataSource dataSource;

	public MySqlBaseDao(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
