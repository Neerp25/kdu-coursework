package com.example.Miniproject.entity.model;

import com.example.Miniproject.entity.Userrole;
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
public class Houseowner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String houseOwnerId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Userrole role;

    @Override
    public String toString() {
        return "HouseOwner{" +
                "houseOwnerId=" + houseOwnerId +
                ", house=" + house +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
