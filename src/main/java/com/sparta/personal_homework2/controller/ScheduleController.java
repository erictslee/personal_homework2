package com.sparta.personal_homework2.controller;

import com.sparta.personal_homework2.dto.ScheduleCreateRequest;
import com.sparta.personal_homework2.dto.ScheduleDeleteRequest;
import com.sparta.personal_homework2.dto.ScheduleResponse;
import com.sparta.personal_homework2.dto.ScheduleUpdateRequest;
import com.sparta.personal_homework2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok()
                .body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        return ResponseEntity.ok()
                .body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> create(
            @Valid @RequestBody ScheduleCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable(name = "scheduleId") long scheduleId,
            @Valid @RequestBody ScheduleUpdateRequest request) {

        return ResponseEntity.ok().body(service.update(scheduleId, request));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> delete(
            @PathVariable(name = "scheduleId") long scheduleId,
            @Valid @RequestBody ScheduleDeleteRequest request) {

        service.delete(scheduleId, request);
        return ResponseEntity.ok()
                .body("성공적으로 삭제되었습니다.");
    }
}