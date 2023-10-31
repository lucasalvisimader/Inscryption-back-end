package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CanPutCardOnBoardDTO;
import br.senai.sc.trunfo.model.dto.CardBoardPositionDTO;
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

import java.util.List;

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

    @PutMapping("/endTurn")
    public ResponseEntity<Board> endTurn(@NotNull HttpServletRequest request, @RequestBody List<CardBoardPositionDTO> cardBoardPositionDTOS) {
        return ResponseEntity.ok(boardService.endTurn(request, cardBoardPositionDTOS));
    }

    @GetMapping("/canPutCardOnBoard")
    public ResponseEntity<Boolean> canPutCardOnBoard(@RequestBody CanPutCardOnBoardDTO canPutCardOnBoardDTO) {
        return ResponseEntity.ok(boardService.canPutCardOnBoard(canPutCardOnBoardDTO));
    }

    @PutMapping("/getNewBoard")
    public ResponseEntity<Board> getNewBoard(@NotNull HttpServletRequest request) {
        return ResponseEntity.ok(boardService.getNewBoard(request));
    }
}
