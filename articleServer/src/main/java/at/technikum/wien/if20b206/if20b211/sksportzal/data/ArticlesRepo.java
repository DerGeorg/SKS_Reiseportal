package at.technikum.wien.if20b206.if20b211.sksportzal.data;

import at.technikum.wien.if20b206.if20b211.sksportzal.entity.ArticlesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticlesRepo extends CrudRepository<ArticlesEntity, Integer> {

    @Override
    Optional<ArticlesEntity> findById(Integer integer);
}
