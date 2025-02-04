package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class TelemetryServiceImplTest {

    @Mock
    DeviceRepository deviceRepository;
    @Mock
    TelemetryRepository telemetryRepository;
    @InjectMocks
    TelemetryServiceImpl service;

    Telemetry telemetry;
    List<Telemetry> telemetries;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        telemetry = new Telemetry();
        telemetry.setHostname("test");

        telemetries = List.of(telemetry);
    }

    @Test
    void postTelemetry() {
        when(deviceRepository.findById(any())).thenReturn(Optional.of(new Device())).thenReturn(Optional.empty());
        when(telemetryRepository.save(any())).thenReturn(telemetry);

        Telemetry result = service.postTelemetry(telemetry);

        assertEquals(telemetry, result);
        assertThrows(IllegalArgumentException.class, () -> service.postTelemetry(telemetry));

    }

    @Test
    void getAllTelemetries() {
        doReturn(telemetries).when(telemetryRepository).findAll();

        List<Telemetry> result = service.getAllTelemetries(null);

        assertEquals(result, telemetries);
    }
}