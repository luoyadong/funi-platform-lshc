package com.funi.platform.lshc.query;

import com.funi.platform.lshc.entity.sys.Archives;

/**
 * Created by sam on 2018/11/28.9:45 PM
 */
public class ArchivesQuery extends GhouseBaseQuery {
    /** 档案室名称*/
    private String archivesName;

    public ArchivesQuery() {
    }

    public ArchivesQuery(Archives archives) {
        this.archivesName = archives.getArchivesName();
    }

    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName;
    }
}
