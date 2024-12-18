package com.fullstackproject.repositories;

import com.fullstackproject.entities.Bid;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

    List<Bid> findAllByUserId(Long userId);

    List<Bid> findAllByCarId(Long carId);
}


