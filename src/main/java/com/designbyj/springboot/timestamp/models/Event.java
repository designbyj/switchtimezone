package com.designbyj.springboot.timestamp.models;

import javax.persistence.*;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;


@Entity
@Table(name = "Event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public String change(String timezone, Event event) {
        ZoneId id = ZoneId.of(timezone);
        Instant inst = event.getCreatedAt().toInstant();
        OffsetDateTime offset = OffsetDateTime.ofInstant(inst, id);
        String offsetTime = offset.toString();
        return offsetTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                "create=" + createdAt +
                '}';
    }
}
