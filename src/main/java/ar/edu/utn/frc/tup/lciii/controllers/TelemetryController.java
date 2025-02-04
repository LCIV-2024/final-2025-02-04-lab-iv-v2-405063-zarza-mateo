package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TelemetryController {
    private  final TelemetryService telemetryService;

    @PostMapping("/telemetry")
    public ResponseEntity<Telemetry> getDevice(@RequestBody Telemetry telemetry) {
        return ResponseEntity.ok(telemetryService.postTelemetry(telemetry));
    }

    @GetMapping("/telemetry")
    public ResponseEntity<List<Telemetry>> getList(@RequestParam(required = false) String hostname) {
        if (hostname != null){
            return ResponseEntity.ok(telemetryService.getAllTelemetries(hostname));
        }
        return ResponseEntity.ok(telemetryService.getAllTelemetries(null));
    }
}