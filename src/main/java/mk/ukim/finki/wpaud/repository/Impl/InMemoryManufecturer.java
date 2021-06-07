package mk.ukim.finki.wpaud.repository.Impl;

import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufecturer {

    public List<Manufacturer> findAll(){
        return Dataholder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return Dataholder.manufacturers.
                stream().
                filter(manufacturer -> manufacturer.getID().equals(id)).findFirst();
    }
    public Optional<Manufacturer> save(String name, String address){
        Manufacturer m = new Manufacturer(name,address);
        Dataholder.manufacturers.add(m);
        return Optional.of(m);
    }

    public boolean deletebyID(Long id){
        return Dataholder.manufacturers.removeIf(manufacturer -> manufacturer.getID().equals(id));
    }
}
