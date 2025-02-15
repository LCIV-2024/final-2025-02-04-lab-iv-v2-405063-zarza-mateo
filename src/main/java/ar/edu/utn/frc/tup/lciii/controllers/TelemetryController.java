package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.TelemetryDto;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TelemetryController {
    private final TelemetryService service;


    @PostMapping("/telemetry")
    PostTelemetryDto postTelemetry(@RequestBody PostTelemetryDto telemetry) {
        return service.PostTelemtry(telemetry);
    }

    @GetMapping("/telemetry")
    List<TelemetryDto> getDevice(@PathVariable(required = false, value = "hostname") String hostname) {
        return service.getTelemetry(hostname);
    }

}