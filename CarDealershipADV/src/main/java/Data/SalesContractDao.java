package Data;

import Models.SalesContract;

import java.util.List;

public interface SalesContractDao {

	List<SalesContract> getAll();

	SalesContract getByContractId(int contractId);

	SalesContract getByVehicleId(int vehicleId);

	SalesContract add(SalesContract contract);

	void update(SalesContract contract);

	void delete(int contractId);

}
