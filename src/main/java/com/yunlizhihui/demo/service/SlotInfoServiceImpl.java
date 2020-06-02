package com.yunlizhihui.demo.service;

import com.yunlizhihui.demo.domain.SlotInfo;
import com.yunlizhihui.demo.mapper.SlotInfoMapper;
import com.yunlizhihui.demo.utils.ParkResponse;
import com.yunlizhihui.demo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotInfoServiceImpl implements SlotInfoService {
    @Autowired
    private SlotInfoMapper slotInfoMapper;

    @Override
    public ParkResponse insertSlotInfo(SlotInfo slotInfo) {
        if (slotInfo == null) {
            return new ParkResponse(205, "数据为空");
        }
        try {
            slotInfo.setCreateTime(TimeUtils.getDateTime());
            slotInfoMapper.insert(slotInfo);
        } catch (Exception e) {
            return new ParkResponse(200, e.getMessage());
        }
        System.out.println(slotInfo);
        return new ParkResponse(100, "访问正常，传输成功");
    }
}
