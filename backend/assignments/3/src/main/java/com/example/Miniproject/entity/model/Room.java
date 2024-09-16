package com.example.Miniproject.entity.model;

import com.example.Miniproject.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty("id")
    private String roomId;

    @JsonProperty("room_name")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
