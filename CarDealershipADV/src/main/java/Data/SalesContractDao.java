package Data;

import Models.SalesContract;

import java.util.List;

public interface SalesContractDao {

	List<SalesContract> getAll();

	SalesContract add();

	void update();

	void delete();

}
