package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Player {

    @Id
    @GeneratedValue
    private int id;

}
