package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final TelemetryRepository telemetryRepository;
    private final RestTemplate restTemplate;

    public Device postDevice(Device device) {
        if (deviceRepository.findById(device.getHostName()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un dispositivo con ese id.");
        }

        return deviceRepository.save(device);
    }

    public List<Device> getDevicesByType(DeviceType type) {

        List<Device> devices = deviceRepository.findAll();
        List<Device> devicesFiltered = new ArrayList<>();
        for (Device device : devices) {
            if (device.getType().equals(type)) {
                devicesFiltered.add(device);
            }
        }
        return devicesFiltered;
    }

    public List<Device> postApi (){

    }
}
