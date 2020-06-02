package com.yunlizhihui.demo.domain;

/**
 * 停车场信息
 */
public class ParkingLotInfo {
    //厂商编码
    private String comType;
    //片区
    private String areaName;
    //片区ID
    private int areaId;
    //停车场名称
    private String parkName;
    //停车场编码
    private int parkCode;
    //停车场经度
    private double lng;
    //停车场纬度
    private double lat;
    //时间，格式：YYYY-MM-DD HH:mm:ss
    private String dateTime;
    //车位总数
    private int slotCount;
    //停车场状态
    private boolean status;
    //收费标准
    private int chargeRule;

    //记录创建时间
    private String createTime;

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getParkCode() {
        return parkCode;
    }

    public void setParkCode(int parkCode) {
        this.parkCode = parkCode;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getChargeRule() {
        return chargeRule;
    }

    public void setChargeRule(int chargeRule) {
        this.chargeRule = chargeRule;
    }

    public boolean isStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ParkingLotInfo{" +
                "comType='" + comType + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaId=" + areaId +
                ", parkName='" + parkName + '\'' +
                ", parkCode=" + parkCode +
                ", lng=" + lng +
                ", lat=" + lat +
                ", dateTime='" + dateTime + '\'' +
                ", slotCount=" + slotCount +
                ", status=" + status +
                ", chargeRule=" + chargeRule +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
