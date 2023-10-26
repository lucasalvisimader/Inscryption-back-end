package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardBoardPositionDTO;
import br.senai.sc.trunfo.model.entity.Board;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.CardBoardPosition;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.PhasesBoard;
import br.senai.sc.trunfo.repository.BoardRepository;
import br.senai.sc.trunfo.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.senai.sc.trunfo.model.enums.ImageType.*;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;
    private CardService cardService;
    private CardBoardPositionService cardBoardPositionService;

    public Board list(@NotNull HttpServletRequest request) {
        User user = JWTUtil.getUser(request);
        return user.getBoard();
    }

    public Board getNewBoard(@NotNull HttpServletRequest request) {
        User user = JWTUtil.getUser(request);
        Board board = user.getBoard();
        PhasesBoard randomPhasedBoard = PhasesBoard.values()[(int) (Math.random() * PhasesBoard.values().length)];
        board.setPhasesBoard(randomPhasedBoard);
        List<CardBoardPosition> cardBoardPositions = getEnemyUpComingCards(board.getPhasesBoard(), user);
        board.setEnemyUpComingCards(cardBoardPositions);
        return boardRepository.save(board);
    }

    private List<CardBoardPosition> getEnemyUpComingCards(PhasesBoard phasesBoard, User user) {
        return switch (phasesBoard) {
            case DEFAULT -> mapPositions(
                    List.of(BULLFROG, BLOODHOUND, WOLF, WOLFCUB, SKINK, ADDER, MAGPIE, RATTLER, OPOSSUM),
                    5, 3, user);
            case FOREST -> mapPositions(
                    List.of(ELK, PRONGHORN, COYOTE, MOOSEBUCK, BEE, PORCUPINE, WOLFCUB,
                            ANTQUEEN, WORKERANT, BEEHIVE, ELKFAWN, GRIZZLY, MANTIS),
                    7, 4, user);
            case MOUNTAIN -> mapPositions(
                    List.of(SPARROW, RAVEN, KINGFISHER, PORCUPINE, STRANGELARVA, BEAVER, COCKROACH,
                            RAVENEGG, MAGPIE, TURKEYVULTURE, STRANGEPUPA),
                    6, 3, user);
            case SWAMP -> mapPositions(
                    List.of(GECK, SKINK, ADDER, BULLFROG, RIVERSNAPPER,
                            GREATWHITE, AMOEBA, AMALGAM, RATTLER, MANTIS),
                    6, 2, user);
            case CAVERN -> mapPositions(
                    List.of(BAT, MOLE, MOLEMAN, FIELDMICE, WOLFCUB, ALPHA,
                            RABBIT, RATKING, MANTISGOD, COCKROACH),
                    6, 3, user);
            default -> mapPositions(
                    List.of(SPARROW, RAVEN, ADDER, ELK, ALPHA, MOOSEBUCK, STRANGELARVA, RABBIT,
                            URAYULI, RAVENEGG),
                    5, 3, user);
        };
    }

    private List<CardBoardPosition> mapPositions(List<ImageType> imageTypes, int beginHard, int size, User user) {
        List<CardBoardPosition> cardBoardPositions = new ArrayList<>();
        if (user.getCards().size() % 2 == 0) {
            imageTypes = imageTypes.subList(0, beginHard);
            size--;
        }

        int quantitySizeCard = (user.getCards().size() / (size * 2));

        cardBoardPositionService.deleteAll(user.getBoard().getEnemyUpComingCards());
        user.getBoard().setEnemyUpComingCards(null);
        boardRepository.save(user.getBoard());

        for (int i = 0; i < (quantitySizeCard); i++) {
            for (ImageType imageType : imageTypes) {
                cardBoardPositions.add(getRandomCardPosition(cardService.listByName(imageType), quantitySizeCard, imageTypes.size()));
            }
        }
        return cardBoardPositions;
    }

    private CardBoardPosition getRandomCardPosition(Card card, int quantitySizeCard, int imageTypesSize) {
        CardBoardPosition cardBoardPosition = new CardBoardPosition();
        cardBoardPosition.setCard(card);

        List<Short> positions = new ArrayList<>();
        List<CardBoardPosition> allCardBoardPositions = cardBoardPositionService.listAll();
        for (CardBoardPosition positionCard : allCardBoardPositions) {
            short position = positionCard.getPosition();
            positions.add(position);
        }

        short positionRandom;
        do {
            positionRandom = (short) (Math.random() * ((quantitySizeCard * imageTypesSize) * 2));
        } while (positions.contains(positionRandom));

        cardBoardPosition.setPosition(positionRandom);
        cardBoardPositionService.save(cardBoardPosition);

        return cardBoardPosition;
    }


    public Board endTurn(@NotNull HttpServletRequest request, @Valid List<CardBoardPositionDTO> cardBoardPositionDTOS) {
        User user = JWTUtil.getUser(request);
        Board board = user.getBoard();

        for (CardBoardPositionDTO cardBoardPositionDTO : cardBoardPositionDTOS) {
            CardBoardPosition cardBoardPosition = new CardBoardPosition();
            cardBoardPosition.setPosition(cardBoardPositionDTO.getPosition());
            cardBoardPosition.setCard(cardBoardPositionDTO.getCard());
            board.getPlayerCards().add(cardBoardPosition);
        }

        return board;
    }
}
