package com.rstkhldntsk.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.rstkhldntsk.amqp",
        "com.rstkhldntsk.customer"
})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.rstkhldntsk.client")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
