package com.yunlizhihui.demo.domain;


/**
 * 车辆出场信息
 */
public class CarExitInfo {
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
    //出场图URL路径
    private String exitPic;
    //车辆驶出时间
    private String exitTime;
    //停车时长
    private String duration;
    //停车费用
    private String paidMoney;

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

    public String getExitPic() {
        return exitPic;
    }

    public void setExitPic(String exitPic) {
        this.exitPic = exitPic;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
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
        return "CarExitInfo{" +
                "comType='" + comType + '\'' +
                ", parkCode='" + parkCode + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", carType='" + carType + '\'' +
                ", exitPic='" + exitPic + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", duration='" + duration + '\'' +
                ", paidMoney='" + paidMoney + '\'' +
                '}';
    }
}
