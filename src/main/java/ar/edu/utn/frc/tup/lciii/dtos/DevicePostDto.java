package ar.edu.utn.frc.tup.lciii.dtos;

import ar.edu.utn.frc.tup.lciii.model.DeviceType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DevicePostDto {
String hostname;
DeviceType type;
String os;
String macAddress;

}
