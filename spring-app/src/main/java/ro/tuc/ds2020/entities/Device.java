package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length=16)
    @Type(type = "uuid-binary")
    private UUID id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "maxenergyconsumption", nullable = false)
    private Float maxEnergyConsumption;

    public Device(UUID id, String description, String address, Float maxEnergyConsumption) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxEnergyConsumption = maxEnergyConsumption;
    }

    public Device() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getMaxEnergyConsumption() {
        return maxEnergyConsumption;
    }

    public void setMaxEnergyConsumption(Float maxEnergyConsumption) {
        this.maxEnergyConsumption = maxEnergyConsumption;
    }
}
