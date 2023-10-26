package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardBoardPositionDTO;
import br.senai.sc.trunfo.model.entity.CardBoardPosition;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.CardBoardPositionRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardBoardPositionService {
    private CardBoardPositionRepository cardBoardPositionRepository;

    public CardBoardPosition save(@Valid CardBoardPositionDTO cardBoardPositionDTO) {
        CardBoardPosition cardBoardPosition = new CardBoardPosition();
        cardBoardPosition.setPosition(cardBoardPositionDTO.getPosition());
        cardBoardPosition.setCard(cardBoardPosition.getCard());
        return cardBoardPositionRepository.save(cardBoardPosition);
    }

    public void save(@Valid CardBoardPosition cardBoardPosition) {
        cardBoardPositionRepository.save(cardBoardPosition);
    }

    public CardBoardPosition list(Long id) {
        return cardBoardPositionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Card board position not found"));
    }

    public List<CardBoardPosition> listAll() {
        return cardBoardPositionRepository.findAll();
    }

    public void deleteAll(List<CardBoardPosition> cardBoardPositions) {
        cardBoardPositionRepository.deleteAllInBatch(cardBoardPositions);
    }
}
