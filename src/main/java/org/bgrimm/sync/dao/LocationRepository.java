package org.bgrimm.sync.dao;

import org.bgrimm.sync.domain.Location;
import org.bgrimm.sync.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{
	Location findById(Long id);
}
