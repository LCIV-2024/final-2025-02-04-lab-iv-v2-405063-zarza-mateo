package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.DevicePostDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;

import java.util.List;

public interface DeviceService {
    DevicePostDto postDevice(Device device);
    List<Device> getDevicesByType(DeviceType type);
    List<Device> postApi ();
}
