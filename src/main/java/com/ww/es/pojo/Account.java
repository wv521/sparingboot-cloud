package com.ww.es.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bank",type = "my_bank",shards = 5,replicas = 1)
public class Account {
}
