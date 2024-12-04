package com.fullstackproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstackproject.dto.BidDTO;
import com.fullstackproject.enums.BidStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity //Makes a table in database
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="car_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    private BidStatus bidStatus;

    public BidDTO getBidDTO(){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(id);
        bidDTO.setPrice(price);
        bidDTO.setCarId(car.getId());
        bidDTO.setCarName(car.getName());
        bidDTO.setCarBrand(car.getBrand());
        bidDTO.setBidStatus(bidStatus);
        bidDTO.setEmail(user.getEmail());
        bidDTO.setUsername(user.getName());
        bidDTO.setSellerName(car.getUser().getName());
        return bidDTO;
    }
}
