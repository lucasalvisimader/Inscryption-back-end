package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.exception.InvalidPosition;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;

    public Board save(@Valid Object objectDTO) {
        Board board = new Board();
        BeanUtils.copyProperties(objectDTO, board);
        return board;
    }

    public Board list(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Board Not Found"));
    }

    public Board changePlayerCardPosition(Long id, @Valid CardPositionDTO cardPositionDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Board Not Found"));
        int position = cardPositionDTO.getPosition();

        if (position < board.getPlayerCards().size()) {
            List<Card> playerCards = board.getPlayerCards();
            playerCards.set(position, cardPositionDTO.getCard());
            board.setPlayerCards(playerCards);
            return boardRepository.save(board);
        }
        throw new InvalidPosition("Invalid card position");
    }

    public Board endTurn(Long id) {
        return null;
    }
}
