package com.fullstackproject.repositories;


import com.fullstackproject.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByUserId(Long userId);

    Long countByUserId(Long userId);

    Long countByUserIdAndSoldTrue(Long userId);
}
