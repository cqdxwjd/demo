package com.yunlizhihui.demo.service;

import com.yunlizhihui.demo.domain.CarEntryInfo;
import com.yunlizhihui.demo.utils.ParkResponse;

public interface CarEntryInfoService {
    ParkResponse insertCarEntryInfo(CarEntryInfo carEntryInfo);
}
