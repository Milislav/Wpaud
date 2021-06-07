package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ManufecturerService {

    List<Manufacturer> findall();
    Optional<Manufacturer> findbyId(Long id);
    Optional<Manufacturer> save(String name , String address);
    void deleteByID(Long id);
}
