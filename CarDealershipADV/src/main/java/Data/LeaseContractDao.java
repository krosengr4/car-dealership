package Data;

import Models.LeaseContract;

import java.util.List;

public interface LeaseContractDao {

	List<LeaseContract> getAll();

	LeaseContract add();

	void update();

	void delete();

}
