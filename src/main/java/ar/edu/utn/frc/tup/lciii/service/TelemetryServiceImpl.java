package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.PostTelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.TelemetryDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TelemetryServiceImpl implements TelemetryService {
    private final DeviceRepository deviceRepository;
    private final TelemetryRepository telemetryRepository;

    @Override
    public PostTelemetryDto PostTelemtry(PostTelemetryDto telemetry) {
        Device device = deviceRepository.findByHostName(telemetry.getHostname());
        if (device == null)
            throw new IllegalArgumentException("No existe device con ese hostname");
        Telemetry newTelemetry =  Telemetry.builder()
                .ip(telemetry.getIp())
                .audioCaptureAllowed(telemetry.getAudioCapturedAllowed())
                .screenCaptureAllowed(telemetry.getScreenCaptureAllowed())
                .cpuUsage(telemetry.getCpuUsage()).dataDate(LocalDateTime.now())
                .hostDiskFree(telemetry.getHostDiskFree())
                .microphoneState(telemetry.getMicrophoneState())
                .build();
        newTelemetry.setDevice(device);
        telemetryRepository.save(newTelemetry);
        return telemetry;
    }

    @Override
    public List<TelemetryDto> getTelemetry(String hostname) {
        List<TelemetryDto> result = new ArrayList<>();
        if (hostname == null) {
            for (Telemetry telemetry : telemetryRepository.findAll()) {
                result.add(new TelemetryDto(telemetry.getIp(), telemetry.getHostname(), telemetry.getHostDiskFree(), telemetry.getMicrophoneState(), telemetry.getScreenCaptureAllowed(), telemetry.getAudioCaptureAllowed(), telemetry.getDataDate()));
            }
        } else {
            for (Telemetry telemetry : telemetryRepository.findAll()) {
                if (telemetry.getHostname().equals(hostname))
                    result.add(new TelemetryDto(telemetry.getIp(), telemetry.getHostname(), telemetry.getHostDiskFree(), telemetry.getMicrophoneState(), telemetry.getScreenCaptureAllowed(), telemetry.getAudioCaptureAllowed(), telemetry.getDataDate()));
            }
        }
        return result;

    }
}
