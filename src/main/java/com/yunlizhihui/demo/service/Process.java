package com.yunlizhihui.demo.service;

import com.yunli.bigdata.eventbus.sdk.AdminClient;
import com.yunli.bigdata.eventbus.sdk.ConsumerGroup;
import com.yunli.bigdata.eventbus.sdk.Event;
import com.yunli.bigdata.eventbus.sdk.Topic;
import com.yunli.toolkit.common.process.AbstractProcess;
import com.yunli.toolkit.common.request.RequestImportCsvParams;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Process extends AbstractProcess {
    @Override
    public RequestImportCsvParams initParams() {
        return null;
    }

    @Override
    public void process() {

    }

    @Test
    void testConsumerProducerWithSDK() throws InterruptedException, ExecutionException, IOException {
        final String topic = "foo";
        final String group = "group1";

        AdminClient adminClient = new AdminClient("localhost", 8888);
        adminClient.createTopic(new Topic(topic, 1));
        Thread.sleep(1000);
        adminClient.registerConsumerGroup(new ConsumerGroup(group, topic));
        Thread.sleep(1000);
        assertEquals(1, adminClient.listTopics().size());
        assertEquals(1, adminClient.listConsumerGroups(topic).size());

        String desc = "1Go 2is 3an 4open 5source 6programming 7language 8that 9makes 10it 11easy 12to 13build 14simple, " +
                "15reliable, 16and 17efficient 18software.";
        String[] contents = desc.split("\\W+");

        CountDownLatch latch = new CountDownLatch(1);

        com.yunli.bigdata.eventbus.sdk.Consumer consumer = new com.yunli.bigdata.eventbus.sdk.Consumer("localhost",
                8888,
                new ConsumerGroup(group, topic),
                Collections.singletonList(0)) {
            private int index = 0;

            @Override
            protected void onError(Throwable t) {
                t.printStackTrace();
                fail();
            }

            @Override
            protected void onEvent(Event e) {
                assertEquals(contents[index], new String(e.getData()));
                index++;
                if (index == contents.length) {
                    latch.countDown();
                }
            }
        };
        Thread.sleep(1000);

        com.yunli.bigdata.eventbus.sdk.Producer producer = new com.yunli.bigdata.eventbus.sdk.Producer("localhost",
                8888) {
            @Override
            protected void onError(Throwable t) {
                t.printStackTrace();
                fail();
            }
        };
        for (String content : contents) {
            Event event = new Event(topic, content, content.getBytes());
            producer.sendEventAsync(event);
        }
        producer.flush();
        producer.close();
        latch.await();
        consumer.close();

        adminClient.unregisterConsumerGroup(new ConsumerGroup(group, topic));
        Thread.sleep(1000);
        adminClient.dropTopic(topic);

        assertEquals(0, adminClient.listTopics().size());
        adminClient.close();
    }

}
