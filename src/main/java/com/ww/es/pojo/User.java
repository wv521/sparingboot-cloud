package com.ww.es.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "customer",type = "my_customer",replicas = 1,shards = 5)
public class User {
}
