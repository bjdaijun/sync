package org.bgrimm.sync.dao;

import org.bgrimm.sync.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findById(Long id);
}
