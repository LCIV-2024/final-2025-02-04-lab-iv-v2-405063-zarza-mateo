package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository repository;
    private final RestTemplate restTemplate;


    @Override
    public PostDeviceDto postDevice(PostDeviceDto device) {
        if (repository.findByHostName(device.getHostname()) != null)
            throw new IllegalArgumentException("Ya existe device con ese hostname");
        repository.save(Device.builder().os(device.getOs()).createdDate(LocalDateTime.now()).hostName(device.getHostname()).type(device.getType()).macAddress(device.getMacAddress()).build());
        return device;
    }

    @Override
    public List<DeviceDto> getDevice(DeviceType type) {
        List<DeviceDto> result = new ArrayList<>();

        for (Device device : repository.findByType(type)) {
            result.add(new DeviceDto(device.getId(), device.getHostName(), device.getType(), device.getOs(), device.getMacAddress()));
        }
        return result;
    }

    @Override
    public List<DeviceDto> getDevicesByThreshold(Double low, Double high) {
        List<Device> devices = repository.findAll();
        List<DeviceDto> result = new ArrayList<>();

        for (Device device : devices) {
            if (device.getTelemetry().getCpuUsage() >= low && device.getTelemetry().getCpuUsage() <= high)
                result.add(new DeviceDto(device.getId(), device.getHostName(), device.getType(), device.getOs(), device.getMacAddress()));
        }
        if (result.isEmpty())
            throw new IllegalArgumentException("No se han encontrado devices con esos parametros.");
        return result;
    }

    @Override
    public List<DeviceDto> postDevicesByApi() {

        List<Device> devices = restTemplate.exchange(
                "https://67a106a15bcfff4fabe171b0.mockapi.io/api/v1/device/device",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Device>>() {
                }
        ).getBody();

        assert devices != null;
        int mitad = devices.size() / 2;

        List<Device> toSave = new ArrayList<>();
        List<DeviceDto> result = new ArrayList<>();


        for (int i = 0; i < mitad; i++) {
            int aux = new Random(mitad).nextInt();
            Device device = devices.get(aux);
            toSave.add(device);
            result.add(new DeviceDto(device.getId(), device.getHostName(), device.getType(), device.getOs(), device.getMacAddress()));
            devices.remove(aux);
        }
        repository.saveAll(toSave);
        return result;
    }
}
