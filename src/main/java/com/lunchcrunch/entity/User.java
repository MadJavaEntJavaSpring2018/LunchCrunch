package com.lunchcrunch.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * A class to represent a user.
 *
 * @author mchoinoski
 */
@Entity(name = "User")
@Table(name = "user")
//public class User implements Serializable {
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_active")
    private LocalDateTime dateActive;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "organization")
    private String organization;


    //    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<Appointment> appointments = new HashSet<>();

    //    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "topic_id",
//            foreignKey = @ForeignKey(name = "appointment_topic_id_fk"))
//    private Topic topic = new Topic();


    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param apiKey       the apiKey
     * @param active       the active
     * @param dateActive   the date active
     * @param firstName    the first name
     * @param lastName     the last name
     * @param organization the organization
     */
    public User(String apiKey,
                boolean active,
                LocalDateTime dateActive,
                String firstName,
                String lastName,
                String organization) {
        this.apiKey = apiKey;
        this.active = active;
        this.dateActive = dateActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets apiKey.
     *
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets apiKey.
     *
     * @param apiKey the apiKey
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets date active.
     *
     * @return the date active
     */
    public LocalDateTime getDateActive() {
        return dateActive;
    }

    /**
     * Sets date active.
     *
     * @param dateActive the date active
     */
    public void setDateActive(LocalDateTime dateActive) {
        this.dateActive = dateActive;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }



    /**
     * Gets appointments.
     *
     * @return the appointments
     */
//    public Set<Appointment> getAppointments() {
//        return appointments;
//    }

    /**
     * Sets appointments.
     *
     * @param appointments the appointments
     */
//    public void setAppointments(Set<Appointment> appointments) {
//        this.appointments = appointments;
//    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
//    public Topic getTopic() {
//        return topic;
//    }

    /**
     * Sets topic.
     *
     * @param topic the topic
     */
//    public void setTopic(Topic topic) {
//        this.topic = topic;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                Objects.equals(apiKey, user.apiKey) &&
                Objects.equals(dateActive, user.dateActive) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(organization, user.organization);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, apiKey, active, dateActive, firstName, lastName, organization);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", active=" + active +
                ", dateActive=" + dateActive +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

}

