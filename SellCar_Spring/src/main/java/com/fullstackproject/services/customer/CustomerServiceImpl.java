package com.fullstackproject.services.customer;

import com.fullstackproject.dto.AnalyticsDTO;
import com.fullstackproject.dto.BidDTO;
import com.fullstackproject.dto.CarDTO;
import com.fullstackproject.dto.SearchCarDTO;
import com.fullstackproject.entities.Bid;
import com.fullstackproject.entities.Car;
import com.fullstackproject.entities.User;
import com.fullstackproject.enums.BidStatus;
import com.fullstackproject.repositories.BidRepository;
import com.fullstackproject.repositories.CarRepository;
import com.fullstackproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    private final BidRepository bidRepository;

    @Override
    public boolean createCar(CarDTO carDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(carDTO.getUserId());
        if(optionalUser.isPresent()){
            Car car= new Car();
            car.setName(carDTO.getName());
            car.setBrand(carDTO.getBrand());
            car.setPrice(carDTO.getPrice());
            car.setDescription(carDTO.getDescription());
            car.setColor(carDTO.getColor());
            car.setTransmission(carDTO.getTransmission());
            car.setSold(false);
            car.setYear(carDTO.getModel());
            car.setImg(carDTO.getImg().getBytes());
            car.setUser(optionalUser.get());
            carRepository.save(car);
            return true;

        }
        return false;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDTO).orElse(null);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean updateCar(Long id, CarDTO carDTO) throws IOException {
        Optional<Car> optionalCar=carRepository.findById(id);
        if((optionalCar.isPresent())){
            Car car = optionalCar.get();
            car.setName(carDTO.getName());
            car.setBrand(carDTO.getBrand());
            car.setPrice(carDTO.getPrice());
            car.setDescription(carDTO.getDescription());
            car.setColor(carDTO.getColor());
            car.setTransmission(carDTO.getTransmission());
            car.setYear(carDTO.getModel());
            if(carDTO.getImg() != null)
                car.setImg(carDTO.getImg().getBytes());
            carRepository.save(car);
            return true;

        }
        return false;
    }

    @Override
    public List<CarDTO> searchCar(SearchCarDTO searchCarDTO) {
        Car car = new Car();
        car.setType(searchCarDTO.getType());
        car.setColor(searchCarDTO.getColor());
        car.setType(searchCarDTO.getType());
        car.setType(searchCarDTO.getTransmission());
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> carExample = Example.of(car, exampleMatcher);
        List<Car> cars = carRepository.findAll(carExample);
        return cars.stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getMyCars(Long userId) {
        return carRepository.findAllByUserId(userId).stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bidACar(BidDTO bidDTO) {
        Optional<Car> optionalCar = carRepository.findById(bidDTO.getCarId());
        Optional<User> optionalUser = userRepository.findById(bidDTO.getUserId());
        if(optionalCar.isPresent() && optionalUser.isPresent()){
            Bid bid = new Bid();
            bid.setUser(optionalUser.get());
            bid.setCar(optionalCar.get());
            bid.setPrice(bidDTO.getPrice());
            bid.setBidStatus(BidStatus.PENDING);
            bidRepository.save(bid);
            return true;
        }
        return false;
    }

    @Override
    public List<BidDTO> getBidsByUserId(Long userId) {
        return bidRepository.findAllByUserId(userId).stream().map(Bid::getBidDTO).collect(Collectors.toList());
    }

    @Override
    public List<BidDTO> getBidsByCarId(Long carId) {
        return bidRepository.findAllByCarId(carId).stream().map(Bid::getBidDTO).collect(Collectors.toList());
    }

    @Override
    public boolean changeBidStatus(Long bidId, String status) {
        Optional<Bid> optionalBid = bidRepository.findById(bidId);
        if(optionalBid.isPresent()){
            Bid existingBid = optionalBid.get();
            Car car = optionalBid.get().getCar();
            if(existingBid.getCar().getSold()){
                return false;
            }
            if(Objects.equals(status, "Approve")) {
                existingBid.setBidStatus(BidStatus.APPROVED);
                car.setSold(true);
            }
            else
                existingBid.setBidStatus(BidStatus.REJECTED);
            bidRepository.save(existingBid);
            return true;
        }
        return false;
    }

    @Override
    public AnalyticsDTO getAnalytics(Long userId) {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setTotalCars(carRepository.countByUserId(userId));
        analyticsDTO.setSoldCars(carRepository.countByUserIdAndSoldTrue(userId));
        return analyticsDTO;
    }
}
