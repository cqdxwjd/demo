package com.yunlizhihui.demo.service;

import com.yunlizhihui.demo.domain.CarEntryInfo;
import com.yunlizhihui.demo.mapper.CarEntryInfoMapper;
import com.yunlizhihui.demo.utils.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarEntryInfoServiceImpl implements CarEntryInfoService {

    @Autowired
    private CarEntryInfoMapper carEntryInfoMapper;

    @Override
    public ParkResponse insertCarEntryInfo(CarEntryInfo carEntryInfo) {
        if (carEntryInfo == null) {
            return new ParkResponse(205, "数据为空");
        }
        try {
            carEntryInfoMapper.insert(carEntryInfo);
        } catch (Exception e) {
            return new ParkResponse(200, e.getMessage());
        }
        return new ParkResponse(100, "访问正常，传输成功");
    }
}
