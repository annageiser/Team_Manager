package ch.fhnw.teammanager.service;

import ch.fhnw.teammanager.model.Attendance;
import ch.fhnw.teammanager.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance setAttendance(Attendance attendance) {
    List<Attendance> existing = attendanceRepository.findByPlayerId(attendance.getPlayerId())
        .stream().filter(a -> a.getEventId().equals(attendance.getEventId())).toList();
    if (!existing.isEmpty()) {
        throw new RuntimeException("Attendance already set for this event!");
    }
    return attendanceRepository.save(attendance);
}

    public List<Attendance> getAttendanceByEvent(Long eventId) {
        return attendanceRepository.findByEventId(eventId);
    }
}