package ch.fhnw.teammanager.repository;

import ch.fhnw.teammanager.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEventId(Long eventId);
    List<Attendance> findByPlayerId(Long playerId);
}