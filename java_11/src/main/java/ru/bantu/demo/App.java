package ru.bantu.demo;

public class App {
    static final private String[] messages = new String[] {
        "Hello, World",
        "Goodbye Cruel World",
        "You had me at hello"
    };
    public static void main( String[] args ) {
        LogService service = new LogService();
        service.setMessages(messages);
        service.retrieveMessage();
        service.retrieveMessage();
        service.exampleException();
    }

}