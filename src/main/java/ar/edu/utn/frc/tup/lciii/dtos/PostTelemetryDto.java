package ar.edu.utn.frc.tup.lciii.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostTelemetryDto {
    String hostname;
    String ip;
    Double cpuUsage;
    Double hostDiskFree;
    String microphoneState;
    Boolean screenCaptureAllowed;
    Boolean audioCapturedAllowed;
    LocalDateTime dataDate;
}
