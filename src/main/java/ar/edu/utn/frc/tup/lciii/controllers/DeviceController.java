package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.DevicePostDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<DevicePostDto> getDevice(@RequestBody Device device) {

        return ResponseEntity.ok(deviceService.postDevice(device));
    }

    @GetMapping("/device/{}")
    public ResponseEntity<List<Device>> getByType(@PathVariable DeviceType type) {
        return ResponseEntity.ok(deviceService.getDevicesByType(type));
    }

    @PostMapping("/save-consumed-devices")
    public ResponseEntity<List<Device>> saveConsumedDevices(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.postApi());
    }
}