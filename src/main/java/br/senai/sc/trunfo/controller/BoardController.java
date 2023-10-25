package br.senai.sc.trunfo.controller;

import br.senai.sc.trunfo.model.dto.CardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.service.BoardService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@NoArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<Board> save(Object objectDTO) {
        return ResponseEntity.ok(boardService.save(objectDTO));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Board> list(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.list(id));
    }

    @PutMapping("/changePlayerCardPosition/{id}")
    public ResponseEntity<Board> changePlayerCardPosition(@PathVariable Long id, @RequestBody CardPositionDTO cardPositionDTO) {
        return ResponseEntity.ok(boardService.changePlayerCardPosition(id, cardPositionDTO));
    }

    @PutMapping("/endTurn/{id}")
    public ResponseEntity<Board> endTurn(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.endTurn(id));
    }
}
