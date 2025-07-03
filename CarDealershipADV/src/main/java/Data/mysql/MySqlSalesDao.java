package Data.mysql;

import Data.SalesContractDao;
import Data.VehicleDao;
import Models.SalesContract;
import Models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
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
			statement.setString(2, contract.getDateOfContract());
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

	private SalesContract mapRow(ResultSet results) throws SQLException {
		String date = results.getString("contract_date");
		String customerName = results.getString("customer_name");
		String customerEmail = results.getString("customer_email");
		Vehicle vehicle = vehicleDao.searchById(results.getInt("vehicle_id"));
		boolean isFinanced = results.getBoolean("is_financed");

		return new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
	}

}
