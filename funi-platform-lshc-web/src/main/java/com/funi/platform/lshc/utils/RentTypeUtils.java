package com.funi.platform.lshc.utils;

import com.funi.platform.lshc.dto.ComboboxDto;
import com.funi.platform.lshc.enumatation.RentType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RentTypeUtils {

    static List<ComboboxDto> comboboxDtos = new ArrayList<>();
    static {
        comboboxDtos.add(new ComboboxDto(RentType.A.getDescription(),RentType.A.name()));
        comboboxDtos.add(new ComboboxDto(RentType.B.getDescription(),RentType.B.name()));
        comboboxDtos.add(new ComboboxDto(RentType.C.getDescription(),RentType.C.name()));
        comboboxDtos.add(new ComboboxDto(RentType.D.getDescription(),RentType.D.name()));
    }

    /**
     * 获取建租类型
     * @return
     */
    public static List<ComboboxDto> findRentType(){
        return  comboboxDtos;
    }
}
