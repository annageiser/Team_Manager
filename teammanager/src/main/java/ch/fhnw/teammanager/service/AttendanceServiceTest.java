package ch.fhnw.teammanager.service;

import ch.fhnw.teammanager.model.Attendance;
import ch.fhnw.teammanager.repository.AttendanceRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class AttendanceServiceTest {
    @Test
    void testSetAttendance() {
        AttendanceRepository repo = mock(AttendanceRepository.class);
        AttendanceService service = new AttendanceService(repo);
        Attendance att = new Attendance();
        att.setPlayerId(1L);
        att.setEventId(2L);
        when(repo.save(att)).thenReturn(att);
        service.setAttendance(att);
        verify(repo, times(1)).save(att);
    }
}