package ru.bantu.demo;

public class App {
    public static void main( String[] args ) {
        TestService service = new TestService();
        service.retrieveMessage();
        service.retrieveMessage();
        service.exampleException();
    }

}