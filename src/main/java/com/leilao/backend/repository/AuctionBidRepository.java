package com.leilao.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.leilao.backend.model.AuctionBid;

public interface AuctionBidRepository extends JpaRepository<AuctionBid, Long> {

}
