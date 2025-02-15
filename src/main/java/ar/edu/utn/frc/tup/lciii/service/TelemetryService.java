package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.*;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;

import java.util.List;

public interface TelemetryService {
    PostTelemetryDto PostTelemtry(PostTelemetryDto telemetry);
    List<TelemetryDto> getTelemetry(String hostname);

}
