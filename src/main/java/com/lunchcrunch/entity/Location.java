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
 * A class to represent a user.
 *
 * @author mchoinoski
 */
@Entity(name = "Location")
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "location_user_id_fk"))

    private User user;

    @Column(name = "description")
    private String description;

    /**
     * Instantiates a new Location.
     */
    public Location() {
    }

    /**
     * Instantiates a new Location.
     *
     * @param user        the user
     * @param description the description
     */
    public Location(User user, String description) {
    this.user = user;
    this.description = description;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(description, location.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}


