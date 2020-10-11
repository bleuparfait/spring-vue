package com.rest.app.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserContact implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private UserContactId id;

    @MapsId("senderId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private User sender;

    @MapsId("receiverId")
    @ManyToOne
    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private User receiver;

    @JsonView(Views.IdName.class)
    private boolean active;

    public UserContact(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.id = new UserContactId(sender.getId(), receiver.getId());
    }

    public UserContact(User receiver, User sender, boolean b) {
        this.receiver = receiver;
        this.sender = sender;
        this.id = new UserContactId(receiver.getId(), sender.getId());
        this.active = b;
    }
}
