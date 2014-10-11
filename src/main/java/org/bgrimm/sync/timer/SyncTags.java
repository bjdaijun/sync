package org.bgrimm.sync.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bgrimm.sync.domain.Asset;
import org.bgrimm.sync.domain.Category;
import org.bgrimm.sync.domain.Department;
import org.bgrimm.sync.domain.Tag;
import org.bgrimm.sync.service.AssetService;
import org.bgrimm.sync.service.CategoryService;
import org.bgrimm.sync.service.DeparmentService;
import org.bgrimm.sync.service.ServiceLocator;
import org.bgrimm.sync.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aeroscout.mobileview.api.dto.QueryDTO;
import com.aeroscout.mobileview.api.dto.asset.ArrayOfCategoryDTO;
import com.aeroscout.mobileview.api.dto.asset.ArrayOfTagDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetCriteriaDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetQueryResultDTO;
import com.aeroscout.mobileview.api.dto.asset.CategoryDTO;
import com.aeroscout.mobileview.api.dto.asset.TagCriteriaDTO;
import com.aeroscout.mobileview.api.dto.asset.TagDTO;
import com.aeroscout.mobileview.api.dto.asset.TagQueryResultDTO;
import com.aeroscout.mobileview.api.dto.security.ArrayOfDepartmentDTO;
import com.aeroscout.mobileview.api.dto.security.DepartmentDTO;
import com.aeroscout.mobileview.api.service.AssetAPIServicePortType;
import com.aeroscout.mobileview.api.service.CategoryAPIServicePortType;
import com.aeroscout.mobileview.api.service.DepartmentAPIServicePortType;
import com.aeroscout.mobileview.api.service.TagAPIServicePortType;
import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Component
public class SyncTags {
	@Autowired
	private ServiceLocator locator;

	@Autowired
	private TagService tagService;

	@Autowired
	private DeparmentService deparmentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AssetService assetService;

	@Scheduled(fixedDelay = 5000)
	public void test() {
		
		// tongbu deparment
		syncDeparment();
		syncCategary();
		// tongbu asset
				syncAsset();
		//
		// syncTag();

	}

	private void syncCategary() {
		CategoryAPIServicePortType catPort = locator.getLocator()
				.getCategoryAPIService();

		ArrayOfCategoryDTO arr = catPort.findAllCategories();
		List<CategoryDTO> list = arr.getCategoryDTO();

		Map<Long, Long> idMap = new HashMap();
		for (CategoryDTO dto : list) {
			Category cat = new Category();
			cat.setStatus(dto.getActivityStatus().toString());
			cat.setVersion(dto.getVersion());
			cat.setId(dto.getId());
			cat.setDescription(dto.getDescription());
			cat.setName(dto.getName());
			cat.setImage(dto.getImage());
			CategoryDTO parent = dto.getParent();
			if (parent != null) {
				idMap.put(cat.getId(), parent.getId());
			}
			categoryService.save(cat);
		}
		for (Long key : idMap.keySet()) {
			Category c = categoryService.findById(key);
			Category p = categoryService.findById(idMap.get(key));
			c.setParent(p);
			categoryService.save(c);
		}

	}

	private void syncDeparment() {
		AeroScoutServiceLocator serviceLocator = locator.getLocator();
		DepartmentAPIServicePortType dt = serviceLocator.getDepartmentService();
		ArrayOfDepartmentDTO arr = dt.findAllDepartments();
		List<DepartmentDTO> list = arr.getDepartmentDTO();
		// <ID, ParentID>
		Map<Long, Long> idMap = new HashMap();
		for (DepartmentDTO dto : list) {
			Department deparment = new Department();
			deparment.setId(dto.getId());
			deparment.setName(dto.getName());
			DepartmentDTO parent = dto.getParent();
			if (parent != null) {
				// Department depParent = saveParent(parent);
				// deparment.setParent(depParent);
				idMap.put(deparment.getId(), parent.getId());
			}
			deparmentService.save(deparment);
		}

		for (Long id : idMap.keySet()) {
			Department d = deparmentService.findById(id);
			Department p = deparmentService.findById(idMap.get(id));
			d.setParent(p);
			deparmentService.save(d);
		}
		// idList to update asset
		updateAssetDeparments(list);
	}

	private void updateAssetDeparments(List<DepartmentDTO> list) {
		AssetAPIServicePortType assetPort = locator.getLocator()
				.getAssetAPIService();
		for (DepartmentDTO dep : list) {
			AssetCriteriaDTO cri = new AssetCriteriaDTO();
			ArrayOfDepartmentDTO arr = new ArrayOfDepartmentDTO();
		}

	}

	private Department saveParent(DepartmentDTO parent) {
		Department dp = new Department();
		dp.setId(parent.getId());
		dp.setName(parent.getName());
		deparmentService.save(dp);
		return dp;
	}

	private void syncTag() {
		AeroScoutServiceLocator serviceLocator = locator.getLocator();
		TagAPIServicePortType tagapis = serviceLocator.getTagAPIService();
		TagCriteriaDTO tagcriter1 = new TagCriteriaDTO();
		QueryDTO qdto = new QueryDTO();
		qdto.setPageNo(1);
		qdto.setPageSize(999);
		TagQueryResultDTO resul = tagapis.findTagsByCriteria(tagcriter1, qdto);
		ArrayOfTagDTO tagarr = resul.getTags();
		List<TagDTO> dtolist = tagarr.getTagDTO();
		for (TagDTO tagDto : dtolist) {
			if (tagDto.getAsset() != null) {
				System.out.println(tagDto.getAsset().getId());
			} else {
				System.out.println("NULL");
			}
			Tag tag = new Tag();
			tag.setId(tagDto.getId());
			// tag.setCreateDate(tagDto.g)
			tag.setNetworkID(tagDto.getDeviceId());
			tag.setTagclass(tagDto.getTagType().toString());
			tag.setBatterystatus(tagDto.getBatteryStatus().toString());
			// add asset
			AssetDTO assetDto = tagDto.getAsset();
			if (assetDto != null) {
				Asset asset = new Asset();

				asset.setId(assetDto.getId());
				asset.setName(assetDto.getName());
				ArrayOfDepartmentDTO departArr = assetDto.getDepartments();

				assetService.save(asset);
				tag.setAsset(asset);
			}
			//
			tagService.save(tag);
		}
	}

	private void syncAsset() {
		AssetAPIServicePortType assetPort = locator.getLocator()
				.getAssetAPIService();
		AssetCriteriaDTO cri = new AssetCriteriaDTO();
		QueryDTO query = new QueryDTO();
		query.setPageNo(1);
		query.setPageSize(999);
		AssetQueryResultDTO resultDto = assetPort.findAssetsByCriteria(cri,
				query);
		List<AssetDTO> list = resultDto.getAssets().getAssetDTO();
		List<Long> idList = new ArrayList();
		for (AssetDTO dto : list) {
			Asset a = new Asset();
			a.setId(dto.getId());
			a.setActivityStatus(dto.getActivityStatus().toString());
			a.setBusinessStatus(dto.getAssetBusinessStatus().getName());
			a.setDeleted(dto.isDeleted());
			a.setPopulated(dto.isPopulated());
			a.setSerialNo(dto.getSerialNo());
			a.setVersion(dto.getVersion());
			Long primaryCategoryId = dto.getPrimaryCategory().getId();
			Category c = categoryService.findById(primaryCategoryId);
			a.setPrimaryCategory(c);
			a.setDescription(dto.getDescription());
			assetService.save(a);
			idList.add(a.getId());
		}
		updateAssetDepartments(idList);
		updateAssetCategories(idList);
	}

	private void updateAssetCategories(List<Long> idList) {
		AssetAPIServicePortType assetPort = locator.getLocator()
				.getAssetAPIService();
		for (Long id : idList) {
			AssetDTO dto = assetPort.findPopulatedAssetById(id);
			Asset a = assetService.findById(id);

			List<CategoryDTO> catList=dto.getCategories().getCategoryDTO();
			
			List<Category> list = new ArrayList();

			for (CategoryDTO d : catList) {
				Category dp = categoryService.findById(d.getId());
				if (dp != null)
					list.add(dp);
			}
			a.setCategories(list);
			assetService.save(a);
		}

	}

	private void updateAssetDepartments(List<Long> idList) {
		AssetAPIServicePortType assetPort = locator.getLocator()
				.getAssetAPIService();
		for (Long id : idList) {
			AssetDTO dto = assetPort.findPopulatedAssetById(id);
			Asset a = assetService.findById(id);
			List<DepartmentDTO> depList = dto.getDepartments()
					.getDepartmentDTO();

			List<Department> list = new ArrayList();

			for (DepartmentDTO d : depList) {
				Department dp = deparmentService.findById(d.getId());
				if (dp != null)
					list.add(dp);
			}
			a.setDepartments(list);
			assetService.save(a);
		}

	}

	private List<Department> getDepListByDTO(
			ArrayOfDepartmentDTO arrayOfDepartmentDTO) {

		List<Department> result = new ArrayList();
		if (arrayOfDepartmentDTO != null) {
			List<DepartmentDTO> list = arrayOfDepartmentDTO.getDepartmentDTO();
			for (DepartmentDTO dto : list) {
				result.add(deparmentService.findById(dto.getId()));
			}
		}

		return result;
	}

}
