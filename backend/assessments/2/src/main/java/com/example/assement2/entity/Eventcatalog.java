package com.example.assement2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Eventcatlog")
public class Eventcatalog {
    @Id
    Integer eventid;
    String name;
    Date date;
    String venu;
    Integer avilabeticket;

}
