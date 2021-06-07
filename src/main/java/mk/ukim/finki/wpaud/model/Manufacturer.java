package mk.ukim.finki.wpaud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manufecturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;

    @Column(name = "manufecturer_address")
    private  String address;

    public Manufacturer(){

    }

    public Manufacturer( String name, String address) {
        this.ID = (long) (Math.random() * 1000);
        this.name = name;
        this.address = address;
    }


}
