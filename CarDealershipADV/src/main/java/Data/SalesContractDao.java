package Data;

import Models.Contract;
import Models.SalesContract;

import java.util.List;

public interface SalesContractDao {

	List<Contract> getAll();

	SalesContract getByContractId(int contractId);

	SalesContract getByVehicleId(int vehicleId);

	SalesContract add(SalesContract contract);

	void update(SalesContract contract, int contractId);

	void delete(int contractId);

}
