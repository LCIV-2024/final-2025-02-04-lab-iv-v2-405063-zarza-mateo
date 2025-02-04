package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.exception.GlobalExceptionHandler;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
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

    public List<Device> getByThreshold(Double lowT, Double highT){
        if (highT < lowT)
            throw new IllegalArgumentException("El valor minimo de threshold no puede ser mayor que el valor maximo.");


        List<Device> devices = deviceRepository.findAll();
        List<Device> filteredDevices = new ArrayList<>();

        for (Device device : devices) {
            if (device.getTelemetry().getCpuUsage() >= lowT && device.getTelemetry().getCpuUsage() <= highT)
                filteredDevices.add(device);
        }

        return filteredDevices;
    }

    public List<Device> postApi (){
        List<Device> devicesApi = restTemplate.getForObject("https://67a106a15bcfff4fabe171b0.mockapi.io/api/v1/device/device", List.class);
        List<Device> devicesToAdd = new ArrayList<>();

        int mitad = devicesApi.size() /2;

        for (int i = 0; i < mitad; i++) {
            int aux = new Random(devicesApi.size()).nextInt();
            devicesToAdd.add(devicesApi.get(aux));
            devicesApi.remove(aux);
        }

        return deviceRepository.saveAll(devicesToAdd);
    }
}
