package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeviceControllerTest {
    private MockMvc mockMvc;

    @Mock
    DeviceService service;
    @InjectMocks
    DeviceController controller;

    Device device;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        device = new Device();
        device.setType(DeviceType.LAPTOP);
    }

    @Test
    void getDevice() throws Exception {
        when(service.postDevice(device)).thenReturn(device);
        mockMvc.perform(get("/api/device")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).postDevice(device);

    }

    @Test
    void getByType() {
        String continent = "Americas";
        doReturn(countries).when(countryService).getCountryByContinent(continent);
        mockMvc.perform(get("/countries/{continent}/continent", continent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(countryService, times(1)).getCountryByContinent(continent);

    }

    @Test
    void saveConsumedDevices() {
    }
}