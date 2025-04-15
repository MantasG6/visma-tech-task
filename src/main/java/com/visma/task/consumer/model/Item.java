package com.visma.task.consumer.model;

public class Item {

    private String content;
    private StatusType status;

    public Item(String content, StatusType status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }
}
