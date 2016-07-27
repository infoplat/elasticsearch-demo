package com.suyuening.elasticsearch.enums;

/**
 * elasticsearch集群服务器配置枚举
 * @author suyuening
 *
 */
public enum ESHost {
    ES_HOST_01("Centos_6_6_01", 9300),
//    ES_HOST_02("Centos_6_6_02", 9300),
    ES_HOST_03("Centos_6_6_03", 9300);
//    ES_HOST_04("Centos_6_6_04", 9300);

    private String hostName;
    private int port;
    
    private ESHost(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }
}
