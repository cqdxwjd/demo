package com.yunlizhihui.demo.mapper;

import com.yunlizhihui.demo.domain.SlotInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SlotInfoMapper {
    @Insert("insert into slot_info(comType,areaName,parkName,parkCode,lng,lat,dateTime,slotCount,occupiedSlotCount,createTime) values(#{comType},#{areaName},#{parkName},#{parkCode},#{lng},#{lat},#{dateTime},#{slotCount},#{occupiedSlotCount},#{createTime})")
    int insert(SlotInfo slotInfo);
}
