package mainpackage.repository;

import mainpackage.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,Long> {

    Post findById(long id);
}
