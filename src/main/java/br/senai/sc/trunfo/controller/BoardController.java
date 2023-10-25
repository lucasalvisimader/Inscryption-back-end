package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<Board> list(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(boardService.list(request));
    }

    @PutMapping("/changePlayerCardPosition")
    public ResponseEntity<Board> changePlayerCardPosition(@NotNull HttpServletRequest request, @RequestBody CardPositionDTO cardPositionDTO) {
        return ResponseEntity.ok(boardService.changePlayerCardPosition(request, cardPositionDTO));
    }

    @PutMapping("/endTurn")
    public ResponseEntity<Board> endTurn(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(boardService.endTurn(request));
    }
}
