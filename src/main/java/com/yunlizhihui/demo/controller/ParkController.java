package com.yunlizhihui.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yunlizhihui.demo.domain.CarEntryInfo;
import com.yunlizhihui.demo.domain.CarExitInfo;
import com.yunlizhihui.demo.domain.ParkingLotInfo;
import com.yunlizhihui.demo.domain.SlotInfo;
import com.yunlizhihui.demo.service.CarEntryInfoService;
import com.yunlizhihui.demo.service.CarExitInfoService;
import com.yunlizhihui.demo.service.ParkingLotInfoService;
import com.yunlizhihui.demo.service.SlotInfoService;
import com.yunlizhihui.demo.utils.ParkResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @Autowired
    private CarEntryInfoService carEntryInfoService;
    @Autowired
    private CarExitInfoService carExitInfoService;
    @Autowired
    private ParkingLotInfoService parkingLotInfoService;
    @Autowired
    private SlotInfoService slotInfoService;

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

        ParkResponse response = carEntryInfoService.insertCarEntryInfo(carEntryInfo);

        String json = gson.toJson(response);

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
    @ResponseBody
    public String car_exit_info(@RequestBody JSONObject jsonObject) {

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

        ParkResponse response = carExitInfoService.insertCarExitInfo(carExitInfo);

        String json = gson.toJson(response);

        return json;
    }

    /**
     * 上传停车场信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/parking_lot_info", method = RequestMethod.POST)
    @ResponseBody
    public String parking_lot_info(@RequestBody JSONObject jsonObject) {

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

        ParkResponse response = parkingLotInfoService.insertParkingLotInfo(parkingLotInfo);

        String json = gson.toJson(response);

        return json;

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
    @ResponseBody
    public String slot_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

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

        ParkResponse response = slotInfoService.insertSlotInfo(slotInfo);

        String json = gson.toJson(response);

        return json;
    }
}
