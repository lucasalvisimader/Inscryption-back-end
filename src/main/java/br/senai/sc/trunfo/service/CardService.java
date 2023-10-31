package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.enums.ImageType;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.CardRepository;
import br.senai.sc.trunfo.security.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public Card save(CardDTO objectDTO) {
        Card card = new Card();
        BeanUtils.copyProperties(objectDTO, card);
        return cardRepository.save(card);
    }

    public Page<Card> listAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findAll(pageable);
    }

    public Card list(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card Not Found"));
    }

    public Card listByName(ImageType imageType) {
        return cardRepository.findByImageType(imageType);
    }

    public List<List<Card>> listFromUser(@NotNull HttpServletRequest request) {
        List<Card> grossCards = JWTUtil.getUser(request).getCards();
        List<List<Card>> cards = new ArrayList<>();

        Collections.shuffle(cards);

        List<Card> firstCards = grossCards.subList(0, 4);
        firstCards.add(listByName(ImageType.SQUIRREL));
        Collections.shuffle(firstCards);
        cards.add(firstCards);

        List<Card> deckCards = grossCards.subList(5, grossCards.size() - 1);
        cards.add(deckCards);

        cards.add(squirrelDeck());

        return cards;
    }

    public List<Card> squirrelDeck() {
        List<Card> squirrelCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squirrelCards.add(listByName(ImageType.SQUIRREL));
        }
        return squirrelCards;
    }

    public Integer qtyCost(Long id) {
        Card card = list(id);
        return card.getPriceType().getType();
    }

    public Card update(Long id, CardUpdateDTO objectDTO) {
        Card card = list(id);
        BeanUtils.copyProperties(objectDTO, card);
        return cardRepository.save(card);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    public ImageType[] getImages() {
        return ImageType.values();
    }

    public SigilsType[] getSigils() {
        return SigilsType.values();
    }
}
