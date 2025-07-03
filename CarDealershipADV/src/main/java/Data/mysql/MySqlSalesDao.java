package Data.mysql;

import Data.SalesContractDao;
import Data.VehicleDao;
import Models.SalesContract;
import Models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlSalesDao extends MySqlBaseDao implements SalesContractDao {

	VehicleDao vehicleDao;

	public MySqlSalesDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<SalesContract> getAll() {
		List<SalesContract> contractList = new ArrayList<>();
		String query = "SELECT * FROM sales_contract;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				SalesContract contract = mapRow(results);
				contractList.add(contract);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return contractList;
	}

	private SalesContract mapRow(ResultSet results) throws SQLException {
		String date = results.getString("contract_date");
		String customerName = results.getString("customer_name");
		String customerEmail = results.getString("customer_email");
		Vehicle vehicle = vehicleDao.searchById(results.getInt("vehicle_id"));
		boolean isFinanced = results.getBoolean("is_financed");

		return new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
	}

}
