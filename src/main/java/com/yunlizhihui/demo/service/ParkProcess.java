package com.yunlizhihui.demo.service;

import com.yunli.toolkit.common.process.CollectionProcess;
import com.yunlizhihui.demo.domain.CarInfo;

import java.util.List;

public class ParkProcess implements CollectionProcess<CarInfo> {
    @Override
    public List<CarInfo> collectDataResourceData() {
        //在这里解析数据

        //数据存到中间数据库

        //中间数据存到数据中台
        return null;
    }
}
