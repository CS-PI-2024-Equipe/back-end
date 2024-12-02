package com.leilao.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.leilao.backend.model.Auction;
import com.leilao.backend.model.AuctionBid; 
import com.leilao.backend.repository.AuctionBidRepository;
import com.leilao.backend.repository.AuctionRepository;
import com.leilao.backend.repository.PersonRepository;
import com.leilao.backend.security.JwtService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class AuctionWebSocketController {

    @Autowired
    private AuctionBidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PersonRepository personRepository; 

    @MessageMapping("/bid/{auctionId}") 
    @SendTo("/topic/auction/{auctionId}") 
    public BidMessageReponse handleBid(BidMessageRequest bidMessage) {
        Auction auction = auctionRepository.findById(bidMessage.getAuctionId()).get();
        auction.setEmailUserBid(jwtService.extractUsername(bidMessage.getUserToken()));
        auction.setValueBid(auction.getValueBid()+auction.getIncrementValue());
        auctionRepository.save(auction);

        AuctionBid auctionBid = new AuctionBid();
        auctionBid.setAuction(auction);
        auctionBid.setPerson(personRepository.findByEmail(jwtService.extractUsername(bidMessage.getUserToken())).get());
        bidRepository.save(auctionBid);

        return new BidMessageReponse(auction.getId(), auctionBid.getPerson().getEmail(), auction.getValueBid());
    }
}

@Data
 class BidMessageRequest {
    private Long auctionId;
    private String userToken;
}

@Data
@AllArgsConstructor
 class BidMessageReponse {
    private Long auctionId;
    private String emailUser;
    private Double newValue;
}

