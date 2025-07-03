package Data.mysql;

import Data.VehicleDao;
import Models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
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
		List<Vehicle> vehicleList = new ArrayList<>();
		String query = "SELECT * FROM vehicles " +
							   "WHERE type = ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, type);

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
	public Vehicle searchById(int id) {
		String query = "SELECT * FROM vehicles " +
							   "WHERE id = ?;";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return mapRow(result);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Vehicle add(Vehicle vehicle) {
		String query = "INSERT INTO vehicles (vin, year_made, make, model, color, vehicle_type, odometer, price, sold, id " +
							   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vehicle.getVin());
			statement.setInt(2, vehicle.getYear());
			statement.setString(3, vehicle.getMake());
			statement.setString(4, vehicle.getModel());
			statement.setString(5, vehicle.getColor());
			statement.setString(6, vehicle.getVehicleType());
			statement.setInt(7, vehicle.getOdometer());
			statement.setDouble(8, vehicle.getPrice());
			statement.setBoolean(9, vehicle.isSold());
			statement.setInt(10, vehicle.getVehicleId());

			int rows = statement.executeUpdate();
			if(rows > 0) {
				System.out.println("Success! The vehicle was added!!");
				ResultSet keys = statement.getGeneratedKeys();

				if(keys.next()) {
					int vehicleId = keys.getInt(1);
					return searchById(vehicleId);
				}
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public void update(Vehicle vehicle, int id) {
		String query = "UPDATE vehicles " +
							   "SET vin = ?, year_made = ?, make = ?, model = ?, color = ?, vehicle_type = ?, odometer = ?, price = ?, sold = ? " +
							   "WHERE id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, vehicle.getVin());
			statement.setInt(2, vehicle.getYear());
			statement.setString(3, vehicle.getMake());
			statement.setString(4, vehicle.getModel());
			statement.setString(5, vehicle.getColor());
			statement.setString(6, vehicle.getVehicleType());
			statement.setInt(7, vehicle.getOdometer());
			statement.setDouble(8, vehicle.getPrice());
			statement.setBoolean(9, vehicle.isSold());
			statement.setInt(10, vehicle.getVehicleId());

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! The vehicle information was updated!!!");
			else
				System.err.println("ERROR! Could not update vehicle information!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int id) {
		String query = "DELETE FROM vehicles " +
							   "WHERE id = ?;";
		try(Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			int rows = statement.executeUpdate();
			if(rows > 0)
				System.out.println("Success! The vehicle has been deleted!");
			else
				System.err.println("ERROR! Could not delete the vehicle!!!");

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Vehicle mapRow(ResultSet results) throws SQLException {
		int id = results.getInt("id");
		String vin = results.getString("vin");
		int year = results.getInt("year_made");
		String make = results.getString("make");
		String model = results.getString("model");
		String color = results.getString("color");
		String type = results.getString("vehicle_type");
		int odometer = results.getInt("odometer");
		double price = results.getFloat("price");
		boolean ifSold = results.getBoolean("sold");

		return new Vehicle(id, vin, year, make, model, color, type, odometer, price, ifSold);
	}

}
