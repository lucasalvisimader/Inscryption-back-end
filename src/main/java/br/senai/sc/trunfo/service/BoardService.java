package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.model.exception.InvalidPosition;
import br.senai.sc.trunfo.repository.BoardRepository;
import br.senai.sc.trunfo.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;

    public Board list(@NotNull HttpServletRequest request) {
        User user = JWTUtil.getUser(request);
        return user.getBoard();
    }

    public Board getNewBoard() {
        return null;
    }

    public Board changePlayerCardPosition(@NotNull HttpServletRequest request, @Valid CardPositionDTO cardPositionDTO) {
        User user = JWTUtil.getUser(request);
        Board board = user.getBoard();
        int position = cardPositionDTO.getPosition();

        if (position < board.getPlayerCards().size()) {
            List<Card> playerCards = board.getPlayerCards();
            playerCards.set(position, cardPositionDTO.getCard());
            board.setPlayerCards(playerCards);
            return boardRepository.save(board);
        }
        throw new InvalidPosition("Invalid card position");
    }

    public Board endTurn(@NotNull HttpServletRequest request) {
        return null;
    }
}
