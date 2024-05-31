package com.sparta.personal_homework2.service;

import com.sparta.personal_homework2.dto.CommentCreateRequest;
import com.sparta.personal_homework2.dto.CommentResponse;
import com.sparta.personal_homework2.dto.CommentUpdateRequest;
import com.sparta.personal_homework2.exception.DataNotFoundException;
import com.sparta.personal_homework2e.model.Comment;
import com.sparta.personal_homework2.model.Schedule;
import com.sparta.personal_homework2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse save(long scheduleId, CommentCreateRequest request) {

        // DB에 일정이 존재하지 않는 경우
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        Comment comment = commentRepository.save(new Comment(request.getComment(), request.getUsername(), schedule));
        return CommentResponse.toDto(comment);
    }
