package org.bgrimm.sync.dao;

import java.util.List;

import org.bgrimm.sync.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	// 自定义查询方式
	List<City> findByNameAndCountryAllIgnoringCase(String name, String country);
}
