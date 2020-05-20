package com.yunlizhihui.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yunli.bigdata.eventbus.sdk.AdminClient;
import com.yunli.bigdata.eventbus.sdk.Event;
import com.yunli.bigdata.eventbus.sdk.Producer;
import com.yunli.bigdata.eventbus.sdk.Topic;
import com.yunlizhihui.demo.domain.CarInfo;
import com.yunlizhihui.demo.domain.ParkSlotStatus;
import com.yunlizhihui.demo.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private AdminClient adminClient = new AdminClient(host, port);
    //总线生产者
    private Producer producer = new Producer(host, port) {
        @Override
        protected void onError(Throwable throwable) {
            throwable.printStackTrace();
        }
    };

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 上传车辆进出信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/car_info", method = RequestMethod.POST)
    public boolean car_info(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        CarInfo carInfo = jsonObject.toJavaObject(CarInfo.class);
        //提取车辆进出实时变化信息

        //创建车辆进出停车场topic
        adminClient.createTopic(new Topic(car_topic, 1));
        //向topic发送消息
        producer.sendEventAsync(new Event(car_topic, "test", "data".getBytes()));

        producer.flush();
        producer.close();

        //将CarInfo对象存入mysql数据库
        jdbcTemplate.update("xxx");

        return false;
    }

    /**
     * 上传停车场信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/park_slot", method = RequestMethod.POST)
    public boolean park_slot(@RequestBody JSONObject jsonObject) throws InterruptedException, IOException {

        //解析json对象
        ParkSlotStatus parkSlotStatus = jsonObject.toJavaObject(ParkSlotStatus.class);
        //提取车位实时变化信息

        //创建停车场车位topic
        adminClient.createTopic(new Topic(park_topic, 1));
        //向topic发送消息
        producer.sendEventAsync(new Event(park_topic, "test", "data".getBytes()));

        producer.flush();
        producer.close();

        //将ParkSlotStatus对象存入mysql数据库
        jdbcTemplate.update("xxx");

        return false;

    }
}
