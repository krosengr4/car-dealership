package Data;

import Models.Vehicle;

import java.util.List;

public interface VehicleDao {

	List<Vehicle> getAll();

	List<Vehicle> searchByPrice(double minPrice, double maxPrice);

	List<Vehicle> searchByMake(String make);

	List<Vehicle> searchByModel(String model);

	List<Vehicle> searchByYear(int minYear, int maxYear);

	List<Vehicle> searchByColor(String color);

	List<Vehicle> searchByMileage(int minMiles, int maxMiles);

	List<Vehicle> searchByType(String type);

	Vehicle searchById(int id);

	Vehicle add(Vehicle vehicle);

	void update(Vehicle vehicle, int id);

	void delete(int id);
}
