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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

@RestController
public class ParkController {

    //总线主机名
    private static String host = "10.60.7.14";
    //总线admin端口
    private static int adminPort = 21235;
    //总线admin端口
    private static int consumerPort = 21234;
    //总线admin端口
    private static int producerPort = 21236;
    //车位topic名称
    private final String slot_topic_name = "slot_change";
    //车辆驶入停车场topic名称
    private final String car_entry_topic_name = "car_entry_info_test"; //car_entry_info
    //车辆驶出停车场topic名称
    private final String car_exit_topic_name = "car_exit_info";

    //车辆驶入停车场topic 对象
    private static Topic car_entry_topic = null;
    //车辆驶出停车场topic 对象
    private static Topic car_exit_topic = null;
    //车位变化topic对象
    private Topic slot_topic = null;
    private static Producer producer1 = null;
    private static Producer producer2 = null;
    private static Producer producer3 = null;

    AdminClient adminClient = null;

    @PostConstruct
    private void init() {

        //获取总线管理器
        adminClient = DataBusUtils.getAdminClient(host, adminPort);

        producer1 = new Producer(host, producerPort) {
            @Override
            protected void onError(Throwable throwable) {
                System.out.println("无法创建producer1");
                try {
                    producer1.close();
                    System.out.println("关闭producer1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throwable.printStackTrace();
            }
        };
        producer2 = new Producer(host, producerPort) {
            @Override
            protected void onError(Throwable throwable) {
                System.out.println("无法创建producer2");
                try {
                    producer2.close();
                    System.out.println("关闭producer2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throwable.printStackTrace();
            }
        };
        producer3 = new Producer(host, producerPort) {
            @Override
            protected void onError(Throwable throwable) {
                System.out.println("无法创建producer3");
                try {
                    producer3.close();
                    System.out.println("关闭producer3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throwable.printStackTrace();
            }
        };
    }

    @PreDestroy
    private void destroy() throws IOException {
        producer1.close();
        producer2.close();
        producer3.close();
        adminClient.close();

    }

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

        //判断topic是否存在，没有则自动创建
        if (null == car_entry_topic) {
            List<Topic> topics = adminClient.listTopics();
            car_entry_topic = new Topic(car_entry_topic_name, 1);
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_entry_topic_name)) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆驶入停车场topic
                adminClient.createTopic(car_entry_topic);
            }
        } else {
            List<Topic> topics = adminClient.listTopics();
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_entry_topic.getName())) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆驶入停车场topic
                adminClient.createTopic(car_entry_topic);
            }
        }

        //向topic发送消息
        producer1.sendEventAsync(new Event(car_entry_topic_name, "1", carEntryInfo.toString().getBytes()));

        producer1.flush();

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
    public String car_exit_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        CarExitInfo carExitInfo = jsonObject.toJavaObject(CarExitInfo.class);

        //判断topic是否存在，没有则自动创建
        if (null == car_exit_topic) {
            List<Topic> topics = adminClient.listTopics();
            car_exit_topic = new Topic(car_exit_topic_name, 1);
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_exit_topic_name)) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆驶出停车场topic
                adminClient.createTopic(car_exit_topic);
            }
        } else {
            List<Topic> topics = adminClient.listTopics();
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(car_exit_topic.getName())) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车辆驶出停车场topic
                adminClient.createTopic(car_exit_topic);
            }
        }


        //向topic发送消息
        producer2.sendEventAsync(new Event(car_exit_topic_name, "1", carExitInfo.toString().getBytes()));

        producer2.flush();

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


        //判断topic是否存在，没有则自动创建
        if (null == slot_topic) {
            List<Topic> topics = adminClient.listTopics();
            slot_topic = new Topic(slot_topic_name, 1);
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(slot_topic_name)) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车位变化topic
                adminClient.createTopic(slot_topic);
            }
        } else {
            List<Topic> topics = adminClient.listTopics();
            boolean contains = false;
            for (Topic topic : topics) {
                if (topic.getName().equals(slot_topic.getName())) {
                    contains = true;
                }
            }
            if (!contains) {
                //创建车位变化topic
                adminClient.createTopic(slot_topic);
            }
        }

        //向topic发送消息
        producer3.sendEventAsync(new Event(slot_topic_name, "1", slotInfo.toString().getBytes()));

        producer3.flush();

        ParkResponse response = slotInfoService.insertSlotInfo(slotInfo);

        String json = gson.toJson(response);

        return json;
    }
}
