package com.funi.platform.lshc.dto;

import java.util.List;

/**
 * @author 3
 */
public class ContractViewDto extends ContractFormDto {

    private List<HouseDto> houseDtoList;

    public List<HouseDto> getHouseDtoList() {
        return houseDtoList;
    }

    public void setHouseDtoList(List<HouseDto> houseDtoList) {
        this.houseDtoList = houseDtoList;
    }
}
