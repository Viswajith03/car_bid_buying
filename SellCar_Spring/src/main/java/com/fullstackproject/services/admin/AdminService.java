package com.fullstackproject.services.admin;

import com.fullstackproject.dto.BidDTO;
import com.fullstackproject.dto.CarDTO;
import com.fullstackproject.dto.SearchCarDTO;

import java.util.List;

public interface AdminService {
    List<CarDTO> getAllCars();

    CarDTO getCarById(Long id);

    void deleteCar(Long id);

    List<CarDTO> searchCar(SearchCarDTO searchCarDTO);

    List<BidDTO> getBids();

    boolean changeBidStatus(Long bidId, String status);
}
