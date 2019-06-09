package com.funi.platform.lshc.mapper.sys;

import com.funi.platform.lshc.dto.WorkLogDto;
import com.funi.platform.lshc.entity.sys.JobLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="ghouseJobLogMapper")
public interface JobLogMapper {

    int insert(JobLog record);

    List<WorkLogDto> selectByServiceNum(String serviceNum);
}