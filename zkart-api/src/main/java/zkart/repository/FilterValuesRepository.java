package zkart.repository;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Filter;
import zkart.entity.FilterValues;

public interface FilterValuesRepository extends CrudRepository<FilterValues, Integer>{

}
