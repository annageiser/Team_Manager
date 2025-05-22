package ch.fhnw.teammanager.controller;

import ch.fhnw.teammanager.model.Attendance;
import ch.fhnw.teammanager.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public Attendance setAttendance(@RequestBody Attendance attendance) {
        return attendanceService.setAttendance(attendance);
    }

    @GetMapping("/event/{eventId}")
    public List<Attendance> getAttendanceByEvent(@PathVariable Long eventId) {
        return attendanceService.getAttendanceByEvent(eventId);
    }
}