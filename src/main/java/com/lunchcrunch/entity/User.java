package com.lunchcrunch.entity;

import java.io.Serializable;
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

    @Column(name = "key")
    private String key;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_active")
    private LocalDateTime dateActive;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @Column(name = "organization")
    private String organization;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param key          the key
     * @param active       the active
     * @param dateActive   the date active
     * @param firstName    the first name
     * @param lastName     the last name
     * @param organization the organization
     */
    public User(String key,
                boolean active,
                LocalDateTime dateActive,
                String firstName,
                String lastName,
                String organization) {
        this.key = key;
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
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                Objects.equals(key, user.key) &&
                Objects.equals(dateActive, user.dateActive) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(organization, user.organization);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, key, active, dateActive, firstName, lastName, organization);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", active=" + active +
                ", dateActive=" + dateActive +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

}

