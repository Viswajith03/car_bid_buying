package com.fullstackproject.controllers;

import com.fullstackproject.dto.BidDTO;
import com.fullstackproject.dto.CarDTO;
import com.fullstackproject.dto.SearchCarDTO;
import com.fullstackproject.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminService.deleteCar(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCar(@RequestBody SearchCarDTO searchCarDTO){
        return ResponseEntity.ok(adminService.searchCar(searchCarDTO));
    }

    @GetMapping("/car/bids")
    public ResponseEntity<List<BidDTO>> getBids(){
        return ResponseEntity.ok(adminService.getBids());
    }

    @GetMapping("/car/bid/{bidId}/{status}")
    public ResponseEntity<Void> changeBidStatus(@PathVariable Long bidId, @PathVariable String status){
        boolean success = adminService.changeBidStatus(bidId,status);
        if(success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }



}
