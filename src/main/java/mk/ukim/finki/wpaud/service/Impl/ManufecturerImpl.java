package mk.ukim.finki.wpaud.service.Impl;

import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.repository.Impl.InMemoryManufecturer;
import mk.ukim.finki.wpaud.repository.Jpa.ManufecturerRepository;
import mk.ukim.finki.wpaud.service.ManufecturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class ManufecturerImpl implements ManufecturerService {

    private  final ManufecturerRepository ManufecturerRepository;

    public ManufecturerImpl(ManufecturerRepository ManufecturerRepository) {
        this.ManufecturerRepository = ManufecturerRepository;
    }

    @Override
    public List<Manufacturer> findall() {
        return this.ManufecturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findbyId(Long id) {
        return this.ManufecturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.ManufecturerRepository.save(new Manufacturer(name,address)));
    }

    @Override
    public void deleteByID(Long id) {
         this.ManufecturerRepository.deleteById(id);
    }
}
