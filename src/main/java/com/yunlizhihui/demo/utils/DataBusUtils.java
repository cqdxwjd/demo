package com.yunlizhihui.demo.utils;

import com.yunli.bigdata.eventbus.sdk.AdminClient;

public class DataBusUtils {
    private static AdminClient adminClient = null;

    public static AdminClient getAdminClient(String host, int port) {
        if (null == adminClient) {
            adminClient = new AdminClient(host, port);
        }
        return adminClient;
    }
}
