package com.yunlizhihui.demo.mapper;

import com.yunlizhihui.demo.domain.CarExitInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarExitInfoMapper {
    @Insert("insert into car_exit_info(comType,parkCode,plateNumber,plateColor,paidMoney,duration,carType,exitPic,exitTime) values(#{comType},#{parkCode},#{plateNumber},#{plateColor},#{paidMoney},#{duration},#{carType},#{exitPic},#{exitTime})")
    int insert(CarExitInfo carExitInfo);
}
