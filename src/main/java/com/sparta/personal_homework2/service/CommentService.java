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

    @Transactional
    public CommentResponse update(Long scheduleId, Long commentId, CommentUpdateRequest request){

        if(commentId==null || scheduleId==null){
            throw new IllegalArgumentException("선택한 일정이나 댓글 ID가 입력되지 않았습니다.");
        }

        scheduleService.findScheduleById(scheduleId);
        .orElseThrow(() -> new IllegalArgumentException(("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : "+scheduleId)));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow() -> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다.")


        if (!Objects.equals(comment.getUsername(), request.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        comment.update(request.getComment());
        return CommentResponse.toDto(comment);
    }

    public void delete(Long scheduleId, Long commentId, String username){

        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 일정 데이터가 없습니다. 아이디 : " + scheduleId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new DataNotFoundException("해당 댓글이 DB에 존재하지 않습니다."));

        if(!Objects.equals(comment.getUsername(), username)) {
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);

    }

    }
}
