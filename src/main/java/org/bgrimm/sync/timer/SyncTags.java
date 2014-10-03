package org.bgrimm.sync.timer;

import java.util.List;

import org.bgrimm.sync.domain.Asset;
import org.bgrimm.sync.domain.Department;
import org.bgrimm.sync.domain.Tag;
import org.bgrimm.sync.service.AssetService;
import org.bgrimm.sync.service.ServiceLocator;
import org.bgrimm.sync.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aeroscout.mobileview.api.dto.QueryDTO;
import com.aeroscout.mobileview.api.dto.asset.ArrayOfTagDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetDTO;
import com.aeroscout.mobileview.api.dto.asset.TagCriteriaDTO;
import com.aeroscout.mobileview.api.dto.asset.TagDTO;
import com.aeroscout.mobileview.api.dto.asset.TagQueryResultDTO;
import com.aeroscout.mobileview.api.dto.security.ArrayOfDepartmentDTO;
import com.aeroscout.mobileview.api.service.TagAPIServicePortType;
import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Component
public class SyncTags {
	@Autowired
	private ServiceLocator locator;

	@Autowired
	private TagService tagService;

	@Autowired
	private AssetService assetService;

	@Scheduled(fixedDelay = 5000)
	public void test() {
		// tongbu asset
		syncAsset();
		// tongbu deparment
		syncDeparment();
		//
		syncTag();

	}

	private void syncDeparment() {

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
		// TODO Auto-generated method stub

	}

}
