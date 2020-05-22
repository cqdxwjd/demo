package com.yunlizhihui.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yunlizhihui.demo.domain.CarEntryInfo;
import com.yunlizhihui.demo.domain.CarExitInfo;
import com.yunlizhihui.demo.domain.ParkingLotInfo;
import com.yunlizhihui.demo.domain.SlotInfo;
import com.yunlizhihui.demo.service.CarEntryInfoService;
import com.yunlizhihui.demo.utils.ParkResponse;
import com.zaxxer.hikari.HikariDataSource;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class ParkController {

    //主机名
    private String host = "localhost";
    //端口
    private int port = 8888;
    //停车场车位topic
    private final String park_topic = "slot_change";
    //车辆进出停车场topic
    private final String car_topic = "car_in_out";
    //总线管理器
//    private AdminClient adminClient = new AdminClient(host, port);
    //总线生产者
//    private Producer producer = new Producer(host, port) {
//        @Override
//        protected void onError(Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    };

//    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CarEntryInfoService carEntryInfoService;

    private static Gson gson = new Gson();


    /**
     * 上传车辆入场信息
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "接口的功能介绍", notes = "提示接口使用者注意事项", httpMethod = "POST")
    @ApiImplicitParam(dataType = "JSONObject", name = "jsonObject", value = "入场信息", required = true)
    @RequestMapping(value = "/car_entry_info", method = RequestMethod.POST)
    @ResponseBody
    public String car_entry_info(@RequestBody JSONObject jsonObject) {

        //解析json对象
        CarEntryInfo carEntryInfo = jsonObject.toJavaObject(CarEntryInfo.class);
        //提取车辆进出实时变化信息

        //创建车辆进出停车场topic
//        adminClient.createTopic(new Topic(car_topic, 1));
        //向topic发送消息
//        producer.sendEventAsync(new Event(car_topic, "test", "data".getBytes()));

//        producer.flush();
//        producer.close();

        /*try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into car_entry_info(comType,parkCode,plateNumber,plateColor,carType,entryPic,entryTime) values ('" +
                    carEntryInfo.getComType() + "','" +
                    carEntryInfo.getParkCode() + "','" +
                    carEntryInfo.getPlateNumber() + "','" +
                    carEntryInfo.getPlateColor() + "','" +
                    carEntryInfo.getCarType() + "','" +
                    carEntryInfo.getEntryPic() + "','" +
                    carEntryInfo.getEntryTime() + "')";
            System.out.println(sql);
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
                return 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        ParkResponse response = carEntryInfoService.insertCarEntryInfo(carEntryInfo);

        String json = gson.toJson(response);
        System.out.println(carEntryInfo);

        return json;
    }

    /**
     * 上传车辆出场信息
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "接口的功能介绍", notes = "提示接口使用者注意事项", httpMethod = "POST")
    @ApiImplicitParam(dataType = "JSONObject", name = "jsonObject", value = "出场信息", required = true)
    @RequestMapping(value = "/car_exit_info", method = RequestMethod.POST)
    public boolean car_exit_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        CarExitInfo carExitInfo = jsonObject.toJavaObject(CarExitInfo.class);
        //提取车辆进出实时变化信息

        //创建车辆进出停车场topic
//        adminClient.createTopic(new Topic(car_topic, 1));
        //向topic发送消息
//        producer.sendEventAsync(new Event(car_topic, "test", "data".getBytes()));

//        producer.flush();
//        producer.close();

        //将CarInfo对象存入mysql数据库
//        jdbcTemplate.update("xxx");

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into car_exit_info(comType,parkCode,plateNumber,plateColor,carType,exitPic,exitTime) values ('" +
                    carExitInfo.getComType() + "','" +
                    carExitInfo.getParkCode() + "','" +
                    carExitInfo.getPlateNumber() + "','" +
                    carExitInfo.getPlateColor() + "','" +
                    carExitInfo.getCarType() + "','" +
                    carExitInfo.getExitPic() + "','" +
                    carExitInfo.getExitTime() + "')";
            System.out.println(sql);
            int resultSet = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject.toString());

        return false;
    }

    /**
     * 上传停车场信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/parking_lot_info", method = RequestMethod.POST)
    public boolean parking_lot_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        ParkingLotInfo parkingLotInfo = jsonObject.toJavaObject(ParkingLotInfo.class);
        //提取车位实时变化信息

        //创建停车场车位topic
//        adminClient.createTopic(new Topic(park_topic, 1));
        //向topic发送消息
//        producer.sendEventAsync(new Event(park_topic, "test", "data".getBytes()));

//        producer.flush();
//        producer.close();

        //将ParkSlotStatus对象存入mysql数据库
//        jdbcTemplate.update("xxx");

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into parking_lot_info(comType,areaName,areaId,parkName,parkCode,lng,lat,dateTime,slotCount,status) values ('" +
                    parkingLotInfo.getComType() + "','" +
                    parkingLotInfo.getAreaName() + "','" +
                    parkingLotInfo.getAreaId() + "','" +
                    parkingLotInfo.getParkName() + "','" +
                    parkingLotInfo.getParkCode() + "','" +
                    parkingLotInfo.getLng() + "','" +
                    parkingLotInfo.getLat() + "','" +
                    parkingLotInfo.getDateTime() + "','" +
                    parkingLotInfo.getSlotCount() + "','" +
                    parkingLotInfo.getStatus() + "')";
            System.out.println(sql);
            int resultSet = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject.toString());

        return false;

    }

    /**
     * 上传车位信息
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "接口的功能介绍", notes = "提示接口使用者注意事项", httpMethod = "POST")
    @ApiImplicitParam(dataType = "JSONObject", name = "jsonObject", value = "车位信息", required = true)
    @RequestMapping(value = "/slot_info", method = RequestMethod.POST)
    public boolean slot_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        SlotInfo slotInfo = jsonObject.toJavaObject(SlotInfo.class);
        //提取车辆进出实时变化信息

        //创建车辆进出停车场topic
//        adminClient.createTopic(new Topic(car_topic, 1));
        //向topic发送消息
//        producer.sendEventAsync(new Event(car_topic, "test", "data".getBytes()));

//        producer.flush();
//        producer.close();

        //将CarInfo对象存入mysql数据库
//        jdbcTemplate.update("xxx");

        System.out.println(dataSource instanceof HikariDataSource);
        System.out.println(slotInfo.getLat());

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "insert into slot_info(comType,areaName,parkName,parkCode,lng,lat,dateTime,slotCount,occupiedSlotCount) values ('" +
                    slotInfo.getComType() + "','" +
                    slotInfo.getAreaName() + "','" +
                    slotInfo.getParkName() + "','" +
                    slotInfo.getParkCode() + "','" +
                    slotInfo.getLng() + "','" +
                    slotInfo.getLat() + "','" +
                    slotInfo.getDateTime() + "','" +
                    slotInfo.getSlotCount() + "','" +
                    slotInfo.getOccupiedSlotCount() + "')";
            System.out.println(sql);
            int resultSet = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(slotInfo.toString());

        return false;
    }
}
