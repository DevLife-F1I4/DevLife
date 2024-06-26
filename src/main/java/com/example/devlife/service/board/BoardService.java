package com.example.devlife.service.board;

import com.example.devlife.dto.BoardResponseDto;
import com.example.devlife.dto.BoardWriteRequestDto;
import com.example.devlife.entity.Board;
import com.example.devlife.entity.Category;
import com.example.devlife.entity.User;
import com.example.devlife.exception.UserNotFoundException;
import com.example.devlife.repository.board.BoardRepository;
import com.example.devlife.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(BoardWriteRequestDto newboardWriteRequestDto,
                          String providerid) {

        User user = userRepository.findByProviderId(providerid);

        if(user == null) throw new UserNotFoundException();

        Board result = Board.builder()
                .category(newboardWriteRequestDto.getCategory())
                .grade(newboardWriteRequestDto.getGrade())
                .title(newboardWriteRequestDto.getTitle())
                .content(newboardWriteRequestDto.getContent())
                .user(user)
                .build();
        boardRepository.save(result);
        return result.getId();
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public List<Board> findAllByCategory(Category category){
        return boardRepository.findByCategory(category);
    }

    public BoardResponseDto boardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        BoardResponseDto result = BoardResponseDto.builder()
                .board(board)
                .build();
        return result;
    }

    public Long boardUpdate(Long id, BoardWriteRequestDto boardWriteRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        board.update(boardWriteRequestDto);
        boardRepository.save(board);

        return board.getId();
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        boardRepository.delete(board);
    }
}
