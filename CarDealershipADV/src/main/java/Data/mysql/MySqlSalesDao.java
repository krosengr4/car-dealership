package Data.mysql;

import Data.SalesContractDao;
import Data.VehicleDao;
import Models.Contract;
import Models.SalesContract;
import Models.Vehicle;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSalesDao extends MySqlBaseDao implements SalesContractDao {

	static BasicDataSource dataSource = DatabaseConfig.setDataSource();
	static VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);

	public MySqlSalesDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Contract> getAll() {
		List<Contract> contractList = new ArrayList<>();
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

	public SalesContract getByContractId(int contractId) {
		String query = "SELECT * FROM sales_contract " +
							   "WHERE sales_contract_id = ?;";

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

	public SalesContract getByVehicleId(int vehicleId) {
		String query = "SELECT * FROM sales_contract " +
							   "WHERE vehicle_id = ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vehicleId);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public SalesContract add(SalesContract contract) {
		String query = "INSERT INTO sales_contract (vehicle_id, contract_date, customer_name, customer_email, is_financed, total_price) " +
							   "VALUES (?, ?, ?, ?, ?, ?);";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, contract.getVehicleSold().getVehicleId());
			statement.setDate(2, contract.getDateOfContract());
			statement.setString(3, contract.getCustomerName());
			statement.setString(4, contract.getCustomerEmail());
			statement.setBoolean(5, contract.isFinance());
			statement.setDouble(6, contract.calculateTotalPrice());

			int rows = statement.executeUpdate();
			if(rows > 0) {
				System.out.println("Success! Sales contract was added!!!");
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

	public void update(SalesContract contract, int contractId) {
		String query = "UPDATE sales_contract " +
							   "SET vehicle_id = ?, contract_date = ?, customer_name = ?, customer_email = ?, is_financed = ?, total_price = ? " +
							   "WHERE sales_contract_id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, contract.getVehicleSold().getVehicleId());
			statement.setDate(2, contract.getDateOfContract());
			statement.setString(3, contract.getCustomerName());
			statement.setString(4, contract.getCustomerEmail());
			statement.setBoolean(5, contract.isFinance());
			statement.setDouble(6, contract.calculateTotalPrice());
			statement.setInt(7, contractId);

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! The Sales Contract information has been updated!!!");
			else
				System.err.println("ERROR! Could not update Sales Contract information!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int contractId) {
		String query = "DELETE FROM sales_contract " +
							   "WHERE sales_contract_id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, contractId);

			int rows = statement.executeUpdate();
			if (rows > 0)
				System.out.println("Success! The Sales Contract was deleted!!!");
			else
				System.err.println("ERROR! Could not delete the Sales Contract!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private SalesContract mapRow(ResultSet results) throws SQLException {
		Date date = results.getDate("contract_date");
		String customerName = results.getString("customer_name");
		String customerEmail = results.getString("customer_email");
		Vehicle vehicle = vehicleDao.searchById(results.getInt("vehicle_id"));
		boolean isFinanced = results.getBoolean("is_financed");

		return new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
	}

}
