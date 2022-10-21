package com.rstkhldntsk.notification.repository;

import com.rstkhldntsk.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
