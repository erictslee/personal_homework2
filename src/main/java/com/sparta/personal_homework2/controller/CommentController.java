package com.sparta.personal_homework2.controller;

import com.sparta.personal_homework2.dto.CommentCreateRequest;
import com.sparta.personal_homework2.dto.CommentResponse;
import com.sparta.personal_homework2.dto.CommentUpdateRequest;
import com.sparta.personal_homework2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule/{scheduleId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable(name = "scheduleId") long scheduleId,
            @RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(scheduleId, request));
    }

}