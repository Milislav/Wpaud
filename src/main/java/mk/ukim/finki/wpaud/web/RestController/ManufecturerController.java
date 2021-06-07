package mk.ukim.finki.wpaud.web.RestController;


import mk.ukim.finki.wpaud.bootstrap.Dataholder;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.service.ManufecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufecturers")
public class ManufecturerController {

    private final ManufecturerService manufecturerService;

    public ManufecturerController(ManufecturerService manufecturerService) {
        this.manufecturerService = manufecturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return manufecturerService.findall();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.manufecturerService.findbyId(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam(value = "name") String name){
        ;
        return this.manufecturerService.save(name,"sk")
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

  //  @DeleteMapping("/delete/{id}")
    @RequestMapping(value = "/delete/{id}" , method = { RequestMethod.DELETE , RequestMethod.GET } )
    public ResponseEntity delete(@PathVariable Long id){
        this.manufecturerService.deleteByID(id);

        if(this.manufecturerService.findbyId(id).isEmpty()){
            return new ResponseEntity<>( HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
