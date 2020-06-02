package com.yunlizhihui.demo.domain;


public class CarEntryInfo {
    private String comType;//厂商编码

    private int parkCode;//停车场编码

    private String plateNumber;//车牌号码

    private String plateColor;//车牌颜色

    private String carType;//车辆类型

    private String entryPic;//入场图URL路径

    private String entryTime;//车辆驶入时间

    private String createTime;//记录创建时间

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public int getParkCode() {
        return parkCode;
    }

    public void setParkCode(int parkCode) {
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

    public String getEntryPic() {
        return entryPic;
    }

    public void setEntryPic(String entryPic) {
        this.entryPic = entryPic;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CarEntryInfo{" +
                "comType='" + comType + '\'' +
                ", parkCode=" + parkCode +
                ", plateNumber='" + plateNumber + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", carType='" + carType + '\'' +
                ", entryPic='" + entryPic + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
