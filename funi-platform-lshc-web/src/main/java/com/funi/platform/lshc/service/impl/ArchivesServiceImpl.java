package com.funi.platform.lshc.service.impl;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.Archives;
import com.funi.platform.lshc.entity.sys.HouseFile;
import com.funi.platform.lshc.mapper.sys.ArchivesMapper;
import com.funi.platform.lshc.mapper.sys.HouseFileMapper;
import com.funi.platform.lshc.query.ArchivesQuery;
import com.funi.platform.lshc.query.HouseFileQuery;
import com.funi.platform.lshc.service.ArchivesService;
import com.funi.platform.lshc.utils.BaseEntityUtils;
import com.funi.platform.lshc.vo.ArchivesVo;
import com.funi.platform.lshc.vo.HouseFileVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 3
 */
@Service("archivesService")
public class ArchivesServiceImpl implements ArchivesService {

    @Resource
    private ArchivesMapper archivesMapper;
    @Resource
    private HouseFileMapper houseFileMapper;

    @Override
    public List<ArchivesVo> findArchivesVoList(ArchivesQuery archivesQuery, CurrentUser userInfo) {
        return archivesMapper.selectArchivesVoList(archivesQuery);
    }

    @Override
    public void createArchives(Archives archives, CurrentUser userInfo) {
        // 查询档案室名称是否被占用
        List<ArchivesVo> archivesVoList = archivesMapper.selectArchivesVoList(new ArchivesQuery(archives));
        if(CollectionUtils.isNotEmpty(archivesVoList)) {
            throw new RuntimeException("档案室名称重复");
        }
        archives.setCreateUnitId(userInfo.getOrganization().getDm());
        archivesMapper.insert(archives);
    }

    @Override
    public List<ComboboxDto> findArchivesList(Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<>();
        List<ComboboxDto> comboboxDtoList = archivesMapper.selectAllArchive();
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    @Override
    public List<ComboboxDto> findByArchiveId(String archivesId, Boolean isForm) {
        List<ComboboxDto> rtList = new ArrayList<>();
        List<ComboboxDto> comboboxDtoList = houseFileMapper.selectByArchiveId(archivesId);
        if(CollectionUtils.isNotEmpty(comboboxDtoList)) {
            if(isForm == null || ! isForm) {
                ComboboxDto allDto = new ComboboxDto("全部", "");
                rtList.add(allDto);
            }
            rtList.addAll(comboboxDtoList);
        }
        return rtList;
    }

    @Override
    public List<HouseFileVo> findHouseFileDetailByArchiveId(HouseFileQuery houseFileQuery, CurrentUser userInfo) {
        return houseFileMapper.selectHouseFileListByArchivesId(houseFileQuery);
    }

    @Override
    public void updateArchives(Archives archives, CurrentUser currentUser) {
        // 查询档案室名称是否被占用
        List<ArchivesVo> archivesVoList = archivesMapper.selectArchivesVoListById(archives.getId(), archives.getArchivesName());
        if(CollectionUtils.isNotEmpty(archivesVoList)) {
            throw new RuntimeException("档案室名称重复");
        }
        new BaseEntityUtils<Archives>().buildModifyEntity(archives, currentUser);
        archivesMapper.updateByPrimaryKeySelective(archives);
    }

    @Override
    public void removeArchives(Archives archives, String operateUserId) {
        houseFileMapper.deleteByArchivesId(archives.getId(), operateUserId);
        archivesMapper.updateByPrimaryKeySelective(archives);
    }

    @Override
    public void createHouseFile(HouseFile houseFile, CurrentUser userInfo) {
        List<HouseFileVo> houseFileList = houseFileMapper.selectHouseFileListByArchivesId(new HouseFileQuery(houseFile));
        if(CollectionUtils.isNotEmpty(houseFileList)) {
            throw new RuntimeException("档案柜名称重复");
        }
        houseFile.setCreateUnitId(userInfo.getOrganization().getDm());
        houseFileMapper.insert(houseFile);
    }


    @Override
    public void updateHouseFile(HouseFile houseFile, CurrentUser currentUser) {
        List<HouseFile> houseFileList = houseFileMapper.selectHouseFileList(houseFile.getId(), houseFile.getArchivesId(), houseFile.getFileName());
        if(CollectionUtils.isNotEmpty(houseFileList)) {
            throw new RuntimeException("档案柜名称重复");
        }
        new BaseEntityUtils<HouseFile>().buildModifyEntity(houseFile, currentUser);
        houseFileMapper.updateByPrimaryKeySelective(houseFile);
    }

    @Override
    public void removeHouseFile(HouseFile houseFile, CurrentUser currentUser) {
        HouseFile oldHouseFile = houseFileMapper.selectByPrimaryKey(houseFile.getId());
        if (oldHouseFile == null) {
            throw new RuntimeException("档案柜不存在");
        }
        new BaseEntityUtils<HouseFile>().buildRemoveEntity(oldHouseFile, currentUser);
        houseFileMapper.updateByPrimaryKeySelective(oldHouseFile);
    }
}
