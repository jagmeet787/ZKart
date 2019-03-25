package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{
	List<Review> findAllByItemId(Integer itemId);
	List<Review> findAllBySellerId(Integer id);
	List<Review> findAllByUserId(Integer id);
}
