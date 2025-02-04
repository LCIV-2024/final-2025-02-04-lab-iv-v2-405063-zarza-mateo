package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Telemetry;

import java.util.List;

public interface TelemetryService {

    Telemetry postTelemetry(Telemetry telemetry);

    List<Telemetry> getAllTelemetries(String hostname);
}
