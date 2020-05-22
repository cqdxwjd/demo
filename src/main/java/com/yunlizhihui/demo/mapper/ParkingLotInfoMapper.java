package com.yunlizhihui.demo.mapper;

import com.yunlizhihui.demo.domain.ParkingLotInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingLotInfoMapper {
    @Insert("insert into parking_lot_info(comType,areaName,areaId,parkName,parkCode,lng,lat,dateTime,slotCount,chargeRule,status) values(#{comType},#{areaName},#{areaId},#{parkName},#{parkCode},#{lng},#{lat},#{dateTime},#{slotCount},#{chargeRule},#{status})")
    int insert(ParkingLotInfo parkingLotInfo);
}
