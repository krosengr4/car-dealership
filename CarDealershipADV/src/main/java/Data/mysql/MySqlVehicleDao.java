package Data.mysql;

import Data.VehicleDao;
import Models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehicleDao extends MySqlBaseDao implements VehicleDao {

	public MySqlVehicleDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Vehicle> getAll() {
		List<Vehicle> vehiclesList = new ArrayList<>();
		String query = "SELECT * FROM vehicles;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehiclesList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehiclesList;
	}

	@Override
	public List<Vehicle> searchByPrice(double minPrice, double maxPrice) {
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE price BETWEEN ? AND ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, minPrice);
			statement.setDouble(2, maxPrice);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByMake(String make) {
		List<Vehicle> vehicleList = new ArrayList<>();

		String query = "SELECT * FROM vehicles " +
							   "WHERE make LIKE ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + make + "%");

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByModel(String model) {
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE model LIKE ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + model + "%");

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByYear(int minYear, int maxYear) {
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE year BETWEEN ? AND ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, minYear);
			statement.setInt(2, maxYear);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByColor(String color) {
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE color LIKE ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + color + "%");

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByMileage(int minMiles, int maxMiles) {
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE odometer BETWEEN ? AND ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, minMiles);
			statement.setInt(2, maxMiles);

			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Vehicle vehicle = mapRow(results);
				vehicleList.add(vehicle);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicle> searchByType(String type) {
		return null;
	}

	@Override
	public Vehicle searchById(int id) {
		return null;
	}

	public Vehicle add(Vehicle vehicle) {
		return null;
	}

	public void update(Vehicle vehicle, int id) {

	}

	public void delete(int id) {

	}

	private Vehicle mapRow(ResultSet results) throws SQLException {
		String vin = results.getString("vin");
		int year = results.getInt("year_made");
		String make = results.getString("make");
		String model = results.getString("model");
		String color = results.getString("color");
		String type = results.getString("vehicle_type");
		int odometer = results.getInt("odometer");
		double price = results.getFloat("price");
		boolean ifSold = results.getBoolean("sold");

		return new Vehicle(vin, year, make, model, color, type, odometer, price);
	}


}
