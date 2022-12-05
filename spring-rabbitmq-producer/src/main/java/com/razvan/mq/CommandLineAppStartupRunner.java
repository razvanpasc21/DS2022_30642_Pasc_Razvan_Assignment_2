package com.razvan.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void run(String...args) throws Exception {
        String deviceId = UUID.randomUUID().toString();
        Scanner sc = null;
        try {
            sc = new Scanner(new File("sensor.csv"));
            sc.useDelimiter("\n");   //sets the delimiter pattern
            while (sc.hasNext())  //returns a boolean value
            {
                String line = sc.next().trim();
                messagePublisher.publishMessage(line, deviceId);
                TimeUnit.SECONDS.sleep(5);
            }
            sc.close();  //closes the scanner
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}