package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.PhasesBoard;
import br.senai.sc.trunfo.model.exception.InvalidPosition;
import br.senai.sc.trunfo.repository.BoardRepository;
import br.senai.sc.trunfo.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;
    private CardService cardService;

    public Board list(@NotNull HttpServletRequest request) {
        User user = JWTUtil.getUser(request);
        return user.getBoard();
    }

    public Board getNewBoard(@NotNull HttpServletRequest request) {
        User user = JWTUtil.getUser(request);
        Board board = user.getBoard();
        PhasesBoard randomPhasedBoard = PhasesBoard.values()[(int) (Math.random() * PhasesBoard.values().length)];
        board.setPhasesBoard(randomPhasedBoard);
        switch (randomPhasedBoard) {
            case DEFAULT -> {
                if (user.getCards().size() % 2 == 0) {
                    for (int i = 0; i < user.getCards().size() / 3; i++) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(cardService.listByName(ImageType.BULLFROG));
                        cards.add(cardService.listByName(ImageType.BLOODHOUND));
                        cards.add(cardService.listByName(ImageType.WOLF));
                        cards.add(cardService.listByName(ImageType.WOLFCUB));
                        cards.add(cardService.listByName(ImageType.ADDER));
                        board.setEnemyUpComingCards(cards);
                    }
                } else {
                    for (int i = 0; i < user.getCards().size() / 4; i++) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(cardService.listByName(ImageType.BULLFROG));
                        cards.add(cardService.listByName(ImageType.BLOODHOUND));
                        cards.add(cardService.listByName(ImageType.WOLF));
                        cards.add(cardService.listByName(ImageType.SKINK));
                        cards.add(cardService.listByName(ImageType.ADDER));
                        cards.add(cardService.listByName(ImageType.MAGPIE));
                        cards.add(cardService.listByName(ImageType.RATTLER));
                        cards.add(cardService.listByName(ImageType.OPOSSUM));
                        board.setEnemyUpComingCards(cards);
                    }
                }
            }
            case FOREST -> {
                if (user.getCards().size() % 2 == 0) {
                    for (int i = 0; i < user.getCards().size() / 3; i++) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(cardService.listByName(ImageType.ELKFAWN));
                        cards.add(cardService.listByName(ImageType.MOOSEBUCK));
                        cards.add(cardService.listByName(ImageType.BEE));
                        cards.add(cardService.listByName(ImageType.PORCUPINE));
                        cards.add(cardService.listByName(ImageType.WOLFCUB));
                        cards.add(cardService.listByName(ImageType.MANTIS));
                        cards.add(cardService.listByName(ImageType.ANTQUEEN));
                        cards.add(cardService.listByName(ImageType.WORKERANT));
                        cards.add(cardService.listByName(ImageType.ANTQUEEN));
                        cards.add(cardService.listByName(ImageType.ANTQUEEN));
                        board.setEnemyUpComingCards(cards);
                    }
                } else {
                    for (int i = 0; i < user.getCards().size() / 2; i++) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(cardService.listByName(ImageType.ELK));
                        cards.add(cardService.listByName(ImageType.PRONGHORN));
                        cards.add(cardService.listByName(ImageType.WOLF));
                        cards.add(cardService.listByName(ImageType.COYOTE));
                        cards.add(cardService.listByName(ImageType.SPARROW));
                        board.setEnemyUpComingCards(cards);
                    }
                }
            }
            case MOUNTAIN -> {

            }
            case SWAMP -> {

            }
            case CAVERN -> {

            }
            default -> {

            }
        }
        return boardRepository.save(board);
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
