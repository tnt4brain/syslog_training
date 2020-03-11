package ru.bantu.demo;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.LogManager;


import java.util.Random;

public class LogService {
    private String[] messages;
    private Logger logger = LogManager.getLogger(LogService.class);
    private Random rand = new Random();

    public void setMessages(String[] messages) {
        logger.entry();
        this.messages = messages;
        logger.traceExit();
    }

    public String[] getMessages() {
        logger.entry();
        return logger.exit(messages);
    }

    public String retrieveMessage() {
        logger.entry("retrieveMessage");
        String testMsg = getMessage(getKey());
        return logger.exit(testMsg);
    }

    public void exampleException() {
        try {
            logger.error("An exception should have been thrown");
            String msg = messages[messages.length];
        } catch (Exception ex) {
            logger.catching(ex);
        }
        logger.exit("Leaving....");
    }

    public String getMessage(int key) {
        logger.entry("getMessage");
        String value = messages[key];
        return logger.exit(value);
    }

    private int getKey() {
        logger.entry("getKey");
        int key = rand.nextInt(messages.length);
        return logger.exit(key);
    }
}