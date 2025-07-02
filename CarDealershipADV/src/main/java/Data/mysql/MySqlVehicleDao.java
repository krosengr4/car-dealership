package Data.mysql;

import Data.VehicleDao;
import Models.Vehicle;

import javax.sql.DataSource;
import java.util.List;

public class MySqlVehicleDao extends MySqlBaseDao implements VehicleDao {

	public MySqlVehicleDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Vehicle> getAll() {
		return null;
	}

	@Override
	public List<Vehicle> searchByPrice(int minPrice, int maxPrice) {
		return null;
	}

	@Override
	public List<Vehicle> searchByMake(String make) {
		return null;
	}

	@Override
	public List<Vehicle> searchByModel(String model) {
		return null;
	}

	@Override
	public List<Vehicle> searchByYear(int year) {
		return null;
	}

	@Override
	public List<Vehicle> searchByColor(String color) {
		return null;
	}

	@Override
	public List<Vehicle> searchByMileage(int min, int max) {
		return null;
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




}
