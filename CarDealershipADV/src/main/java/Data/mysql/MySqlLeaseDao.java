package Data.mysql;

import Data.LeaseContractDao;
import Data.VehicleDao;
import Models.Contract;
import Models.LeaseContract;
import Models.Vehicle;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlLeaseDao extends MySqlBaseDao implements LeaseContractDao {

	static BasicDataSource dataSource = DatabaseConfig.setDataSource();
	static VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);

	public MySqlLeaseDao(DataSource datasource) {
		super(datasource);
	}

	@Override
	public List<Contract> getAll() {
		List<Contract> contractList = new ArrayList<>();
		String query = "SELECT * FROM lease_contract;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				LeaseContract contract = mapRow(results);
				contractList.add(contract);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return contractList;
	}

	public LeaseContract getByContractId(int contractId) {
		String query = "SELECT * FROM lease_contract " +
							   "WHERE lease_contract_id = ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, contractId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private LeaseContract mapRow(ResultSet results) throws SQLException {
		String date = results.getString("contract_date");
		String customerName = results.getString("customer_name");
		String customerEmail = results.getString("customer_email");
		Vehicle vehicle = vehicleDao.searchById(results.getInt("vehicle_id"));

		return new LeaseContract(date, customerName, customerEmail, vehicle);
	}
}
