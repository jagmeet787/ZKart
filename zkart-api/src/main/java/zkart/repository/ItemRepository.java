package zkart.repository;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.repository.CrudRepository;

import zkart.entity.Item;


public interface ItemRepository extends CrudRepository<Item, Integer> {

}
