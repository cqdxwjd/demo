package com.yunlizhihui.demo.service;

import com.yunlizhihui.demo.domain.CarExitInfo;
import com.yunlizhihui.demo.mapper.CarEntryInfoMapper;
import com.yunlizhihui.demo.mapper.CarExitInfoMapper;
import com.yunlizhihui.demo.utils.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarExitInfoServiceImpl implements CarExitInfoService {

    @Autowired
    private CarExitInfoMapper carExitInfoMapper;

    @Override
    public ParkResponse insertCarExitInfo(CarExitInfo carExitInfo) {
        if (null == carExitInfo) {
            return new ParkResponse(205, "数据为空");
        }
        try {
            carExitInfoMapper.insert(carExitInfo);
        } catch (Exception e) {
            return new ParkResponse(200, e.getMessage());
        }
        System.out.println(carExitInfo);
        return new ParkResponse(100, "访问正常，传输成功");
    }
}
