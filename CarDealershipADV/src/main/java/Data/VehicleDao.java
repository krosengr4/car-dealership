package Data;

import Models.Printable;
import Models.Vehicle;

import java.util.List;

public interface VehicleDao {

	List<Vehicle> getAll();

	List<Vehicle> searchByPrice();

	List<Vehicle> searchByMake();

	List<Vehicle> searchByModel();

	List<Vehicle> searchByYear();

	List<Vehicle> searchByColor();

	List<Vehicle> searchByMileage();

	List<Vehicle> searchByType();

	Vehicle searchById();

	Vehicle add();

	void update();

	void delete();
}
