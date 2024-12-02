package com.leilao.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "auction_bid")
@Data
public class AuctionBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @PrePersist
    public void prePersist() {
        this.dateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateTime = LocalDateTime.now();
    }
}
