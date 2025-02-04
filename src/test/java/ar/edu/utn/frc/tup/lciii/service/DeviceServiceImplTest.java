package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.DevicePostDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class DeviceServiceImplTest {
    @Mock
    DeviceRepository deviceRepository;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    DeviceServiceImpl service;

    Device device;
    DeviceType type;
    DevicePostDto devicePostDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        device = new Device();
        type = DeviceType.LAPTOP;
        device.setType(type);
        device.setHostName("test");
        device.setOs("test");
        device.setMacAddress("test");

        devicePostDto = new DevicePostDto(device.getHostName(), device.getType(), device.getOs(), device.getMacAddress());
    }

    @Test
    void postDevice() {
        when(deviceRepository.findById("test")).thenReturn(Optional.empty()).thenReturn(Optional.of(device));
        doReturn(device).when(deviceRepository).save(any());

        DevicePostDto result = service.postDevice(device);

        assertEquals(devicePostDto.getHostname(), result.getHostname());
        assertThrows(IllegalArgumentException.class, () -> service.postDevice(device));
    }

    @Test
    void getDevicesByType() {
        doReturn(List.of(device)).when(deviceRepository).findAll();

        List<Device> result = service.getDevicesByType(type);

        assertEquals(result, List.of(device));
    }

    @Test
    void getByThreshold() {
        Telemetry telemetry = new Telemetry();
        telemetry.setCpuUsage(4d);
        device.setTelemetry(telemetry);

        doReturn(List.of(device)).when(deviceRepository).findAll();

        List<Device> result = service.getByThreshold(1d, 5d);

        assertEquals(result, List.of(device));


    }

    @Test
    void postApi() {
        doReturn(List.of(device)).when(restTemplate).getForObject(anyString(), any());
        doReturn(List.of(device)).when(deviceRepository).saveAll(any());

        List<Device> result = service.postApi();
        assertEquals(result, List.of(device));

    }
}