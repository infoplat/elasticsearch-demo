package com.suyuening.elasticsearch.enums;

/**
 * elasticsearch集群服务器配置枚举
 * @author suyuening
 *
 */
public enum ESHost {
    ES_HOST_01("bi_server_01", 9300),
    ES_HOST_02("bi_server_02", 9300),
    ES_HOST_03("bi_server_03", 9300);

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
