/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bgrimm.sync.service;

import java.util.List;

import org.bgrimm.sync.dao.CityRepository;
import org.bgrimm.sync.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("cityService")
@Transactional
public class CityService {

	private final CityRepository cityRepository;

	@Autowired
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public List<City> getCity(String name, String country) {
		return this.cityRepository.findByNameAndCountryAllIgnoringCase(name,
				country);
	}

	public City save(City city) {
		return cityRepository.save(city);
	}
	
	public List<City> findAll(){
		return (List<City>) cityRepository.findAll();
	}

}
