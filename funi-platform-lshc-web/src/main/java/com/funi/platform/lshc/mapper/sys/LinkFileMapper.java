package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.entity.sys.LinkFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("linkFileMapper")
public interface LinkFileMapper {

    int deleteByPrimaryKey(String id);

    int insert(LinkFile record);

    int insertSelective(LinkFile record);

    LinkFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LinkFile record);

    int updateByPrimaryKey(LinkFile record);

    List<LinkFile> selectListByBiz(Map<String,String> param);

    void copyFile(@Param("linkId")String linkId,@Param("newLinkId")String newLinkId);
}