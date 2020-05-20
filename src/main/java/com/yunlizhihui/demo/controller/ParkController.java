package com.yunlizhihui.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yunlizhihui.demo.domain.CarInfo;
import com.yunlizhihui.demo.domain.ParkSlotStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ParkController {

    @RequestMapping(value = "/car_info", method = RequestMethod.POST)
    public boolean car_info(@RequestBody JSONObject jsonObject) {

        CarInfo carInfo = jsonObject.toJavaObject(CarInfo.class);

        return false;
    }

    @RequestMapping(value = "/park_slot", method = RequestMethod.POST)
    public boolean park_slot(@RequestBody JSONObject jsonObject) {

        ParkSlotStatus parkSlotStatus = jsonObject.toJavaObject(ParkSlotStatus.class);

        return false;
    }
}
