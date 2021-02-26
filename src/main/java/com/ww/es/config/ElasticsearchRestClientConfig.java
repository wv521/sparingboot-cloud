package com.ww.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchRestClientConfig {
    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.http.port}")
    private int port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        String[] hosts = host.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            String addr = hosts[i];
            httpHosts[i] = new HttpHost(addr, port);
        }

        return new RestHighLevelClient(RestClient.builder(httpHosts).build());
    }
}

