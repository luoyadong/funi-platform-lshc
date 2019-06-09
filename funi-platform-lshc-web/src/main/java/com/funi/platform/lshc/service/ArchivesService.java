package com.funi.platform.lshc.service;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.entity.sys.Archives;
import com.funi.platform.lshc.entity.sys.HouseFile;
import com.funi.platform.lshc.query.ArchivesQuery;
import com.funi.platform.lshc.query.HouseFileQuery;
import com.funi.platform.lshc.vo.ArchivesVo;
import com.funi.platform.lshc.vo.HouseFileVo;

import java.util.List;

/**
 * 档案室业务层
 * @author 3
 */
public interface ArchivesService {
    /**
     * 分页查询档案室列表
     * @param archivesQuery
     * @return
     */
    List<ArchivesVo> findArchivesVoList(ArchivesQuery archivesQuery, CurrentUser userInfo);

    /**
     * 创建档案室
     * @param archives
     */
    void createArchives(Archives archives, CurrentUser userInfo);

    /**
     * 查询全部档案室列表
     * @return
     */
    List<ComboboxDto> findArchivesList(Boolean isForm);

    /**
     * 根据档案ID查询全部档案柜集合
     * 用于前端的下拉列表
     * @return
     */
    List<ComboboxDto> findByArchiveId(String archivesId, Boolean isForm);

    /**
     * 根据档案ID查询全部档案柜集合
     * 用于页面展示
     * @param houseFileQuery
     * @return
     */
    List<HouseFileVo> findHouseFileDetailByArchiveId(HouseFileQuery houseFileQuery, CurrentUser userInfo);

    /**
     * 修改档案室名称
     * @param archives
     */
    void updateArchives(Archives archives, CurrentUser currentUser);

    /**
     * 删除档案室
     * @param archives
     */
    void removeArchives(Archives archives, String operateUserId);

    /**
     * 创建档案柜
     * @param houseFile
     */
    void createHouseFile(HouseFile houseFile, CurrentUser userInfo);

    /**
     * 修改档案柜
     * @param houseFile
     */
    void updateHouseFile(HouseFile houseFile, CurrentUser currentUser);

    /**
     * 删除档案柜
     * @param houseFile
     */
    void removeHouseFile(HouseFile houseFile, CurrentUser currentUser);
}
