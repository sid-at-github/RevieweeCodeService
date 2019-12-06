package team.dexter.RevieweeCodeService.daos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team.dexter.RevieweeCodeService.models.RevieweeCode;

@Repository
public interface RevieweeCodeDao extends MongoRepository<RevieweeCode, String> {

	public List<RevieweeCode> findByIdIn(List<String> idList);

}
