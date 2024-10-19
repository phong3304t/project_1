package com.library.model.documents;

public class Thesis extends Document {
    private String topic;

    public Thesis(String type, String title, String author, int quantity, String topic) {
        super(type, title, author, quantity);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getInfo() {
        return "Thesis [" + super.getInfo() + ", Topic: " + topic + "]";
    }
}
