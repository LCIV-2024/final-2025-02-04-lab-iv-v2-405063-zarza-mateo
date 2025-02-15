package ar.edu.utn.frc.tup.lciii.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelemetryDto
{
    String ip;
    String hostname;
    Double hostDiskFree;
    String microphoneState;
    Boolean screenCaptureAllowed;
    Boolean audioCapturedAllowed;
    LocalDateTime dataDate;
}
