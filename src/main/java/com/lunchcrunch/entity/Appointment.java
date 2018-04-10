package com.lunchcrunch.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * A class to represent an Appointment.
 *
 * @author mchoinoski
 */
@Entity(name = "Appointment")
@Table(name = "appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "appointment_user_id_fk"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id",
            foreignKey = @ForeignKey(name = "appointment_location_id_fk"))
    private Location location;

    @ManyToOne
    @JoinColumn(name = "topic_id",
            foreignKey = @ForeignKey(name = "appointment_topic_id_fk"))
    private Topic topic;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    /**
     * Instantiates a new Appointment.
     */
    public Appointment() {
    }

    /**
     * Instantiates a new Appointment.
     *
     * @param user     the user
     * @param location the location
     * @param topic    the topic
     * @param dateTime the date time
     */
    public Appointment(User user, Location location, Topic topic, LocalDateTime dateTime) {
        this.user = user;
        this.location = location;
        this.topic = topic;
        this.dateTime = dateTime;
    }

    /**
     * Gets id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets date time.
     * @return the date time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets date time.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }

}

