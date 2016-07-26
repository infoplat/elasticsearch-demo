package com.suyuening.elasticsearch.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.suyuening.elasticsearch.enums.ESHost;

/**
 * ES Client工具类，提供Client单例和关闭方法
 * 
 * @author suyuening
 *
 */
public final class ESClient {
    private ESClient() {}

    private static Client client = null;
    private static final String DEFAULT_CLUSTER_NAME = "elasticsearch";

    /**
     * 执行此方法后会得到Client实例(集群名称默认为"elasticsearch")，再次调用此方法不再生成新的实例。<br/>
     * 要想再次得到新的实例，需要调用{@link ESClient#close()}.<br/>
     * 一般使用完Client后，必须调用{@link ESClient#close()}
     * 
     * @return ES客户端
     */
    public static Client client() {
        return client(DEFAULT_CLUSTER_NAME);
    }

    /**
     * 执行此方法后会得到Client实例，再次调用此方法不再生成新的实例。<br/>
     * 要想再次得到新的实例，需要调用{@link ESClient#close()}.<br/>
     * 一般使用完Client后，必须调用{@link ESClient#close()}
     * 
     * @param clusterName ES集群名称，当集群名称不是"elasticsearch"时，需要指定。
     * @return ES客户端
     */
    public static Client client(String clusterName) {
        if (client == null) {
            Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
            try {
                TransportClient addTransportAddress =
                        TransportClient.builder().settings(settings).build();
                for (ESHost host : ESHost.values()) {
                    addTransportAddress.addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName(host.getHostName()), host.getPort()));
                }
                client = addTransportAddress;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    /**
     * 关闭Client实例，并且设置Client实例为NULL
     */
    public static void close() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
