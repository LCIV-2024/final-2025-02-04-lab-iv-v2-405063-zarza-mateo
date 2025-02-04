package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TelemetryServiceImpl implements TelemetryService {

    private final DeviceRepository deviceRepository;
    private final TelemetryRepository telemetryRepository;

public Telemetry postTelemetry(Telemetry telemetry)  {

  if(deviceRepository.findById(telemetry.getHostname()).isEmpty()) {
      throw new IllegalArgumentException("No existe device con el hostname ingresado.");
  }

telemetryRepository.save(telemetry);
  return telemetry;
}

public List<Telemetry> getAllTelemetries(String hostname)  {
    if (hostname == null) {
        return telemetryRepository.findAll();
    }
    else {
        if(deviceRepository.findById(hostname).isEmpty()) {
            throw new IllegalArgumentException("No existe device con el hostname ingresado.");
        }

        List<Telemetry> telemetries = telemetryRepository.findAll();
        List<Telemetry> telemetriesFiltered = new ArrayList<>();
        for (Telemetry telemetry : telemetries) {
            if (telemetry.getHostname().equals(hostname)){
                telemetriesFiltered.add(telemetry);
            }
        }
        return telemetriesFiltered;
    }
}
}
