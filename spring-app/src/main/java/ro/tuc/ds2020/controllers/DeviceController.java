package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.DeviceRepository;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping()
public class DeviceController {

    DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/devices")
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/devices/{id}")
    public Optional<Device> getUserById(@PathVariable UUID id) {
        return deviceRepository.findById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/devices")
    public boolean addDevice(@RequestBody Device device) {
        device.setId(UUID.randomUUID());
        boolean deviceExists = deviceRepository.findById(device.getId()).isPresent();
        if(!deviceExists) {
            deviceRepository.save(device);
            return true;
        }
        else {
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/devices")
    public boolean updateDevice(@RequestBody Device device) {
        boolean deviceExists = deviceRepository.findById(device.getId()).isPresent();
        if(deviceExists) {
            deviceRepository.save(device);
            return true;
        }
        else {
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/devices/{id}")
    public boolean deleteUser(@PathVariable UUID id) {
        boolean deviceExists = deviceRepository.findById(id).isPresent();
        if(deviceExists) {
            deviceRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

}
