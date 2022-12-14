package com.rstkhldntsk.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.rstkhldntsk.notification",
        "com.rstkhldntsk.amqp"
})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.rstkhldntsk.client")
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
