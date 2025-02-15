package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService service;

    @PostMapping("/device")
    PostDeviceDto postDevice(@RequestBody PostDeviceDto device) {
        return service.postDevice(device);
    }

    @GetMapping("/device/{type}")
    List<DeviceDto> getDevice(@RequestParam(required = false) DeviceType type) {
        return service.getDevice(type);
    }

    @GetMapping("/device/{lowThreshold}{highThreshold}")
    List<DeviceDto> getDevicesByThreshold(@RequestParam(required = false) Double lowThreshold,
                                          @RequestParam(required = false) Double highThreshold) {
        return service.getDevicesByThreshold(lowThreshold, highThreshold);
    }
}