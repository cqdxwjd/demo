package com.yunlizhihui.demo.service;

import com.yunlizhihui.demo.domain.ParkingLotInfo;
import com.yunlizhihui.demo.mapper.ParkingLotInfoMapper;
import com.yunlizhihui.demo.utils.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotInfoServiceImpl implements ParkingLotInfoService {
    @Autowired
    private ParkingLotInfoMapper parkingLotInfoMapper;

    @Override
    public ParkResponse insertParkingLotInfo(ParkingLotInfo parkingLotInfo) {
        if (parkingLotInfo == null) {
            return new ParkResponse(205, "数据为空");
        }
        try {
            parkingLotInfoMapper.insert(parkingLotInfo);
        } catch (Exception e) {
            return new ParkResponse(200, e.getMessage());
        }
        System.out.println(parkingLotInfo);
        return new ParkResponse(100, "访问正常，传输成功");
    }
}
