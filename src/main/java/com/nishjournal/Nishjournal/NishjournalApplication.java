package com.nishjournal.Nishjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NishjournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NishjournalApplication.class, args);
	}
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbfactory){
        return new MongoTransactionManager(dbfactory);
    }
}
