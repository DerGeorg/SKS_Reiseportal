package at.technikum.wien.if20b206.if20b211.sightseeingserver.data;

import at.technikum.wien.if20b206.if20b211.sightseeingserver.entity.SightseeingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SightseeingRepo extends CrudRepository<SightseeingEntity, Integer> {

    @Override
    Optional<SightseeingEntity> findById(Integer integer);
}
