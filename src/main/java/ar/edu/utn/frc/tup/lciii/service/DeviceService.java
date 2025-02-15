package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.*;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;

import java.util.List;

public interface DeviceService {
    PostDeviceDto postDevice (PostDeviceDto device);
    List<DeviceDto> getDevice  (DeviceType type);
    List<DeviceDto> getDevicesByThreshold(Double low, Double high);
    List<DeviceDto> postDevicesByApi ();
}
