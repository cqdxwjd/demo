package com.yunlizhihui.demo.domain;

import java.io.Serializable;

public class EntryExitRecord implements Serializable {
    //数据类型 0表示入场数据，1表示出场数据
    private String dataType;
    //厂商编码
    private String comType;
    //停车场编码
    private String parkCode;
    //车牌号码
    private String plateNumber;
    //车牌颜色
    private String plateColor;
    //车辆类型
    private String carType;
    //出场或者入场图URL路径
    private String entryOrExitPic;
    //车辆驶出或者驶入时间
    private String entryOrExitTime;
    //停车时长
    private String duration;
    //停车费用
    private String paidMoney;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getEntryOrExitPic() {
        return entryOrExitPic;
    }

    public void setEntryOrExitPic(String entryOrExitPic) {
        this.entryOrExitPic = entryOrExitPic;
    }

    public String getEntryOrExitTime() {
        return entryOrExitTime;
    }

    public void setEntryOrExitTime(String entryOrExitTime) {
        this.entryOrExitTime = entryOrExitTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

    @Override
    public String toString() {
        return "EntryExitRecord{" +
                "dataType='" + dataType + '\'' +
                ", comType='" + comType + '\'' +
                ", parkCode='" + parkCode + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", carType='" + carType + '\'' +
                ", entryOrExitPic='" + entryOrExitPic + '\'' +
                ", entryOrExitTime='" + entryOrExitTime + '\'' +
                ", duration='" + duration + '\'' +
                ", paidMoney='" + paidMoney + '\'' +
                '}';
    }
}
