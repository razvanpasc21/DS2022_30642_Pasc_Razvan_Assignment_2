package com.razvan.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Controller
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;
    public String publishMessage(String message, String deviceId) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessageDate(String.valueOf(System.currentTimeMillis()));
        customMessage.setMessageId(deviceId);
        customMessage.setMessage(message);
        System.out.println(customMessage.toString());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, customMessage);

        return "Message Published";
    }

}
