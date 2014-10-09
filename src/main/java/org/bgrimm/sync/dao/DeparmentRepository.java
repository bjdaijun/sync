package org.bgrimm.sync.dao;

import org.bgrimm.sync.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeparmentRepository extends CrudRepository<Department, Long> {

	Department findById(Long id);

}
