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
    private String areaId;
    //停车场名称
    private String parkName;
    //停车场编码
    private String parkCode;
    //停车场经度
    private String lng;
    //停车场纬度
    private String lat;
    //时间，格式：YYYY-MM-DD HH:mm:ss
    private String dateTime;
    //车位总数
    private String slotCount;
    //停车场状态
    private String status;
    //收费标准
    private String chargeRule;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(String slotCount) {
        this.slotCount = slotCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargeRule() {
        return chargeRule;
    }

    public void setChargeRule(String chargeRule) {
        this.chargeRule = chargeRule;
    }

    @Override
    public String toString() {
        return "ParkingLotInfo{" +
                "comType='" + comType + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", parkName='" + parkName + '\'' +
                ", parkCode='" + parkCode + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", slotCount='" + slotCount + '\'' +
                ", status='" + status + '\'' +
                ", chargeRule='" + chargeRule + '\'' +
                '}';
    }
}
