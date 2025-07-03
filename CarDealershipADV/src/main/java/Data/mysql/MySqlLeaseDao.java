package Data.mysql;

import Data.LeaseContractDao;
import Data.VehicleDao;
import Models.Contract;
import Models.LeaseContract;
import Models.Vehicle;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
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

	@Override
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

	@Override
	public LeaseContract getByVehicleId(int vehicleId) {
		String query = "SELECT * FROM lease_contract " +
							   "WHERE vehicle_id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vehicleId);

			ResultSet result = statement.executeQuery();
			if(result.next())
				return mapRow(result);

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public LeaseContract add(LeaseContract contract) {
		String query = "INSERT INTO lease_contract (vehicle_id, contract_date, customer_name, customer_email, monthly_payment, total_price " +
							   "VALUES (?, ?, ?, ?, ?, ?);";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, contract.getVehicleSold().getVehicleId());
			statement.setString(2, contract.getDateOfContract());
			statement.setString(3, contract.getCustomerName());
			statement.setString(4, contract.getCustomerEmail());
			statement.setDouble(5, contract.calculateMonthlyPayment());
			statement.setDouble(6, contract.calculateTotalPrice());

			int rows = statement.executeUpdate();
			if(rows > 0) {
				System.out.println("Success! The Lease Contract was added!!");

				ResultSet keys = statement.getGeneratedKeys();
				if(keys.next()) {
					int contractId = keys.getInt(1);
					return getByContractId(contractId);
				}
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public void update(LeaseContract contract, int contractId) {
		String query = "UPDATE lease_contract " +
							   "SET vehicle_id = ?, contract_date = ?, customer_name = ?, customer_email = ?, monthly_payment = ?, total_price = ? " +
							   "WHERE lease_contract_id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, contract.getVehicleSold().getVehicleId());
			statement.setString(2, contract.getDateOfContract());
			statement.setString(3, contract.getCustomerName());
			statement.setString(4, contract.getCustomerEmail());
			statement.setDouble(5, contract.calculateMonthlyPayment());
			statement.setDouble(6, contract.calculateTotalPrice());
			statement.setInt(7, contractId);

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! The Lease Contract information has been updated!!!");
			else
				System.err.println("ERROR! Could not update the Lease Contract information!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int contractId) {
		String query = "DELETE FROM lease_contract " +
							   "WHERE lease_contract_id = ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, contractId);

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! The Lease Contract has been deleted!!!");
			else
				System.err.println("ERROR! Could not delete the Lease Contract!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private LeaseContract mapRow(ResultSet results) throws SQLException {
		String date = results.getString("contract_date");
		String customerName = results.getString("customer_name");
		String customerEmail = results.getString("customer_email");
		Vehicle vehicle = vehicleDao.searchById(results.getInt("vehicle_id"));

		return new LeaseContract(date, customerName, customerEmail, vehicle);
	}
}
