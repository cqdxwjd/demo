package com.yunlizhihui.demo.mapper;

import com.yunlizhihui.demo.domain.CarEntryInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarEntryInfoMapper {
    @Insert("insert into car_entry_info(comType,parkCode,plateNumber,plateColor,carType,entryPic,entryTime,createTime) values(#{comType},#{parkCode},#{plateNumber},#{plateColor},#{carType},#{entryPic},#{entryTime},#{createTime})")
    int insert(CarEntryInfo carEntryInfo);
}
