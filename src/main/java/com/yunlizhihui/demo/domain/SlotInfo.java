package com.yunlizhihui.demo.domain;

public class SlotInfo {
    //厂商编码
    private String comType;
    //片区
    private String areaName;
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
    //占用车位总数
    private int occupiedSlotCount;

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

    public int getOccupiedSlotCount() {
        return occupiedSlotCount;
    }

    public void setOccupiedSlotCount(int occupiedSlotCount) {
        this.occupiedSlotCount = occupiedSlotCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "SlotInfo{" +
                "comType='" + comType + '\'' +
                ", areaName='" + areaName + '\'' +
                ", parkName='" + parkName + '\'' +
                ", parkCode=" + parkCode +
                ", lng=" + lng +
                ", lat=" + lat +
                ", dateTime='" + dateTime + '\'' +
                ", slotCount=" + slotCount +
                ", occupiedSlotCount=" + occupiedSlotCount +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
