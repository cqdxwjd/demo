package com.yunlizhihui.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yunli.bigdata.eventbus.sdk.AdminClient;
import com.yunli.bigdata.eventbus.sdk.Event;
import com.yunli.bigdata.eventbus.sdk.Producer;
import com.yunli.bigdata.eventbus.sdk.Topic;
import com.yunlizhihui.demo.domain.CarEntryInfo;
import com.yunlizhihui.demo.domain.CarExitInfo;
import com.yunlizhihui.demo.domain.ParkingLotInfo;
import com.yunlizhihui.demo.domain.SlotInfo;
import com.yunlizhihui.demo.service.CarEntryInfoService;
import com.yunlizhihui.demo.service.CarExitInfoService;
import com.yunlizhihui.demo.service.ParkingLotInfoService;
import com.yunlizhihui.demo.service.SlotInfoService;
import com.yunlizhihui.demo.utils.DataBusUtils;
import com.yunlizhihui.demo.utils.ParkResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.avro.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ParkController {

    //总线主机名
    private String host = "10.60.7.14";
    //总线admin端口
    private int adminPort = 21235;
    //总线admin端口
    private int consumerPort = 21234;
    //总线admin端口
    private int producerPort = 21236;
    //车位topic名称
    private final String slot_topic = "slot_change";
    //车辆进出停车场topic名称
    private final String car_topic = "car_in_out";

    //车辆进出停车场topic 对象
    private static Topic car_in_out = null;

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
    public String car_entry_info(@RequestBody JSONObject jsonObject) throws IOException, InterruptedException {

        //解析json对象
        CarEntryInfo carEntryInfo = jsonObject.toJavaObject(CarEntryInfo.class);
        //提取车辆进出实时变化信息

        //获取总线管理器
        AdminClient adminClient = DataBusUtils.getAdminClient(host, adminPort);


        //判断topic是否存在，没有则自动创建
        if (null == car_in_out) {
            List<Topic> topics = adminClient.listTopics();
            car_in_out = new Topic(car_topic, 1);
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_topic)) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆进出停车场topic
                adminClient.createTopic(car_in_out);
            }
        } else {
            List<Topic> topics = adminClient.listTopics();
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_in_out.getName())) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆进出停车场topic
                adminClient.createTopic(car_in_out);
            }
        }

        new Schema.Parser()

        Producer producer = new Producer(host, producerPort) {
            @Override
            protected void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        //向topic发送消息
        producer.sendEventAsync(new Event(car_topic, "test", "test1".getBytes()));

        producer.flush();
        producer.close();

        //数据还须要插入中间库
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
