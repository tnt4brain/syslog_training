package ru.bantu.demo;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.LogManager;


import java.util.Random;

public class TestService {
    private Logger logger = LogManager.getLogger("mylogger");
    private String[] messages = new String[] {
        "Hello, World",
        "Goodbye Cruel World",
        "You had me at hello"
    };
    private Random rand = new Random();

    public void setMessages(String[] messages) {
        logger.traceEntry(new SimpleMessage(messages[0]));
        this.messages = messages;
        logger.traceExit();
    }

    public String[] getMessages() {
        logger.traceEntry();
        return logger.traceExit(messages);
    }

    public String retrieveMessage() {
        logger.entry();
        String testMsg = getMessage(getKey());
        return logger.exit(testMsg);
    }

    public void exampleException() {
        logger.entry();
        try {
            logger.error("An exception should have been thrown");
            String msg = messages[messages.length];
        } catch (Exception ex) {
            logger.catching(ex);
        }
        logger.exit();
    }

    public String getMessage(int key) {
        logger.entry(key);
        String value = messages[key];
        return logger.exit(value);
    }

    private int getKey() {
        logger.entry();
        int key = rand.nextInt(messages.length);
        return logger.exit(key);
    }
}