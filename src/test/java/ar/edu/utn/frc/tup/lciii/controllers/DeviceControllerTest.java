package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.PostDeviceDto;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import ar.edu.utn.frc.tup.lciii.service.DeviceService;
import ar.edu.utn.frc.tup.lciii.service.DeviceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class DeviceControllerTest {
    @Mock
    DeviceServiceImpl service;

    @InjectMocks
    DeviceController controller;

    private MockMvc mockMvc;

    PostDeviceDto postDeviceDto;
    DeviceDto deviceDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        postDeviceDto = new PostDeviceDto("test-host", DeviceType.Laptop, "Windows 10", "AA:BB:CC:DD:EE:FF");
        deviceDto = new DeviceDto();

        deviceDto.setType(DeviceType.Laptop);
    }

    @Test
    void postDevice() throws Exception {
        when(service.postDevice(any(PostDeviceDto.class))).thenReturn(postDeviceDto);

        mockMvc.perform(post("/api/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"hostname\":\"test-host\"," +  // Note: lowercase h in hostname
                                "\"type\":\"Laptop\"," +
                                "\"os\":\"Windows 10\"," +
                                "\"macAddress\":\"AA:BB:CC:DD:EE:FF\"" +
                                "}"))
                .andDo(print())  // This will print the request/response for debugging
                .andExpect(status().isCreated());

        verify(service, times(1)).postDevice(any(PostDeviceDto.class));
    }

    @Test
    void getDevice() {
    }

    @Test
    void getDevicesByThreshold() {
    }
}