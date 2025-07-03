package Data;

import Models.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

	List<LeaseContract> getAll();

	List<LeaseContract> getById();

	LeaseContract add();

	void update();

	void delete();

}
