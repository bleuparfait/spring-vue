package com.rest.app.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContactId implements Serializable {
    @JsonView(Views.Id.class)
    private String senderId;
    @JsonView(Views.Id.class)
    private String receiverId;
}
