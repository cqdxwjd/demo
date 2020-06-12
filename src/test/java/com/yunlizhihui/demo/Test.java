package com.yunlizhihui.demo;

import com.yunli.bigdata.eventbus.sdk.AdminClient;
import com.yunli.bigdata.eventbus.sdk.Producer;
import com.yunli.bigdata.eventbus.sdk.Topic;
import com.yunlizhihui.demo.utils.DataBusUtils;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        //总线主机名
        String host = "10.60.7.14";
        //总线admin端口
        int adminPort = 21235;

        int consumerPort = 21234;

        int producerPort = 21236;
        String car_entry_topic_name = "car_entry_info_test"; //car_entry_info

        //车辆驶入停车场topic 对象
        Topic car_entry_topic = null;
        //车辆驶出停车场topic 对象
        Topic car_exit_topic = null;
        //车位变化topic对象
        Topic slot_topic = null;
        Producer producer1 = null;
        Producer producer2 = null;
        Producer producer3 = null;

        AdminClient adminClient = DataBusUtils.getAdminClient(host, adminPort);

        List<Topic> topics = adminClient.listTopics();
        car_entry_topic = new Topic(car_entry_topic_name, 1);
        boolean contains = false;
        for (Topic topic : topics) {
            System.out.println(topic.getName());
        }
        adminClient.close();
    }
}
