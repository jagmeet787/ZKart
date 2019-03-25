package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Review;
import zkart.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	public List<Review> getAllReviews() {
		ArrayList<Review> list = new ArrayList<Review>();
		reviewRepository.findAll().forEach(list::add);
		return list;
	}

	public boolean saveReview(Review review) {
		return reviewRepository.save(review) != null;
	}

	public List<Review> getAllReviewsByItemId(Integer id) {
		ArrayList<Review> list = new ArrayList<Review>();
		reviewRepository.findAllByItemId(id).forEach(list::add);
		return list;
	}

	public List<Review> getAllReviewsBySellerId(Integer id) {
		ArrayList<Review> list = new ArrayList<Review>();
		reviewRepository.findAllBySellerId(id).forEach(list::add);
		return list;
	}

	public List<Review> getAllReviewByUserId(Integer id) {
		ArrayList<Review> list = new ArrayList<Review>();
		reviewRepository.findAllByUserId(id).forEach(list::add);
		return list;
	}

	public void delete(Integer id) {
		reviewRepository.deleteById(id);
	}
	
}
