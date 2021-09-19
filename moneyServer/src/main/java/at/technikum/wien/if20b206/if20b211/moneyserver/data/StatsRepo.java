package at.technikum.wien.if20b206.if20b211.moneyserver.data;

import at.technikum.wien.if20b206.if20b211.moneyserver.entity.StatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatsRepo extends CrudRepository<StatsEntity, Integer> {

    @Override
    Optional<StatsEntity> findById(Integer integer);
}
