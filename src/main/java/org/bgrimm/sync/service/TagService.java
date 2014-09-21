package org.bgrimm.sync.service;

import org.bgrimm.sync.dao.TagRepository;
import org.bgrimm.sync.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tagService")
@Transactional
public class TagService {

	@Autowired
	private  TagRepository tagRepopsitory;
	public Tag save(Tag a) {
			
			return tagRepopsitory.save(a);
		}

}
