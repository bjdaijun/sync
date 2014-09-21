package org.bgrimm.sync.dao;


import org.bgrimm.sync.domain.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository  extends CrudRepository<Tag, Long>{
	// 自定义查询方式
		//List<Tag> findByNameAndCountryAllIgnoringCase(String name, String country);

}
