package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.DeviceType;

import java.util.List;

public interface DeviceService {
    Device postDevice(Device device);
    List<Device> getDevicesByType(DeviceType type);
    List<Device> postApi ();
}
