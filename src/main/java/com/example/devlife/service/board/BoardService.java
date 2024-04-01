package com.example.devlife.service.board;

import com.example.devlife.dto.NewBoardRequestDto;
import com.example.devlife.entity.Board;
import com.example.devlife.entity.User;
import com.example.devlife.repository.board.BoardRepository;
import com.example.devlife.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public Long saveBoard(NewBoardRequestDto newboardRequestDto,
                          String provider_id) {
        User user = userRepository.findByProviderId(provider_id).orElseThrow(() -> new UsernameNotFoundException("권한이 없습니다."));

        Board result = Board.builder()
                .category(newboardRequestDto.getCategory())
                .title(newboardRequestDto.getTitle())
                .content(newboardRequestDto.getContent())
                .user(user)
                .build();
        return null;
    }

    public Long boardUpdate(Long id, NewBoardRequestDto boardWriteRequestDto) {
        return null;
    }

    public void boardRemove(Long id) {

    }
}
