package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService service;

    @PostMapping("/device")
    ResponseEntity<PostDeviceDto>  postDevice(@RequestBody PostDeviceDto device) {
        return new ResponseEntity<>(service.postDevice(device), HttpStatus.CREATED);    }

    @GetMapping("/device")
    List<DeviceDto> getDevices(
            @RequestParam(required = false) DeviceType type,
            @RequestParam(required = false) Double lowThreshold,
            @RequestParam(required = false) Double highThreshold) {

        if (type != null) {
            return service.getDevice(type);
        } else if (lowThreshold != null && highThreshold != null) {
            return service.getDevicesByThreshold(lowThreshold, highThreshold);
        }
        return service.getDevice(null);
    }
}