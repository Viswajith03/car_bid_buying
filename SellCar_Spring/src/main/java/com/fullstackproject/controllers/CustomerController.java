package com.fullstackproject.controllers;

import com.fullstackproject.dto.BidDTO;
import com.fullstackproject.dto.CarDTO;
import com.fullstackproject.dto.SearchCarDTO;
import com.fullstackproject.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@ModelAttribute CarDTO carDTO) throws IOException {
        boolean success = customerService.createCar(carDTO);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(customerService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        customerService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id,@ModelAttribute CarDTO carDTO) throws IOException {
        boolean success = customerService.updateCar(id,carDTO);
        if(success) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCar(@RequestBody SearchCarDTO searchCarDTO){
        return ResponseEntity.ok(customerService.searchCar(searchCarDTO));
    }

    @GetMapping("/my-cars/{userId}")
    public ResponseEntity<List<CarDTO>> getMyCars(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getMyCars(userId));
    }

    @PostMapping("/car/bid")
    public ResponseEntity<?> bidACar(@RequestBody BidDTO bidDTO){
        boolean success = customerService.bidACar(bidDTO);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/bids/{userId}")
    public ResponseEntity<List<BidDTO>> getBidsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBidsByUserId(userId));
    }

    @GetMapping("/car/{carId}/bids")
    public ResponseEntity<List<BidDTO>> getBidsByCarId(@PathVariable Long carId){
        return ResponseEntity.ok(customerService.getBidsByCarId(carId));
    }

    @GetMapping("/car/bid/{bidId}/{status}")
    public ResponseEntity<Void> changeBidStatus(@PathVariable Long bidId, @PathVariable String status){
        boolean success = customerService.changeBidStatus(bidId,status);
        if(success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/car/analytics/{userId}")
    public ResponseEntity<?> getAnalytics(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getAnalytics(userId));
    }

}
