package com.example.demo.model;

import com.example.demo.model.enumeration.GameStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(optional = false)
    private Player player;


    @Column(nullable = false)
    private int boardRow;

    @Column(nullable = false)
    private int boardColumn;

    @Enumerated(value = EnumType.STRING)
    private GameStatus gameStatus;
}
