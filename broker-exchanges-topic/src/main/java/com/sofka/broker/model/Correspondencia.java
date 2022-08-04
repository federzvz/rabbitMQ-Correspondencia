package com.sofka.broker.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;


@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Correspondencia.class)
public class Correspondencia implements Serializable {
    private Integer id;
    private String messageContent;

    public Correspondencia() {
    }

    public Correspondencia(Integer id, String messageContent) {
        this.id = id;
        this.messageContent = messageContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Correspondencia{" +
                "id=" + id +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}
