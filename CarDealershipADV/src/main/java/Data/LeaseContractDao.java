package Data;

import Models.Contract;
import Models.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

	List<Contract> getAll();

	LeaseContract getByContractId(int contractId);

	LeaseContract getByVehicleId(int vehicleId);

	LeaseContract add(LeaseContract contract);

	void update(LeaseContract contract, int contractId);

	void delete(int contractId);

}
