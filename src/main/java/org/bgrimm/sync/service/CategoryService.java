package org.bgrimm.sync.service;

import javax.transaction.Transactional;

import org.bgrimm.sync.dao.CategoryRepository;
import org.bgrimm.sync.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category save(Category cat) {
		return repo.save(cat);
	}

	public Category findById(Long key) {
		return repo.findById(key);
	}
}
