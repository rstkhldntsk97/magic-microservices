package com.rstkhldntsk.notification.rabbitmq;

import com.rstkhldntsk.client.notification.NotificationRequest;
import com.rstkhldntsk.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record NotificationConsumer(NotificationService notificationService) {

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }

}
