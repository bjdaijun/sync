package org.bgrimm.sync.service;

import org.bgrimm.sync.dao.DeparmentRepository;
import org.bgrimm.sync.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeparmentService {
	@Autowired
	private DeparmentRepository deparmentRepository;

	public void save(Department dp) {
		deparmentRepository.save(dp);
	}

}
