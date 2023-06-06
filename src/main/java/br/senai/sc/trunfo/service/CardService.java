package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.CardDTO;
import br.senai.sc.trunfo.model.dto.CardUpdateDTO;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.CardRepository;
import br.senai.sc.trunfo.model.enums.SigilsType;
import br.senai.sc.trunfo.model.enums.ImageType;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.senai.sc.trunfo.model.entity.Card;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardService implements ServiceGeneralized<Card, CardDTO, Long> {
    private CardRepository cardRepository;

    @Override
    public Card save(CardDTO objectDTO) {
        Card card = new Card();
        BeanUtils.copyProperties(objectDTO, card);
        return cardRepository.save(card);
    }

    public Page<Card> listAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findAll(pageable);
    }

    @Override
    public Card list(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card Not Found"));
    }

    public Card update(Long id, CardUpdateDTO objectDTO) {
        Card card = list(id);
        BeanUtils.copyProperties(objectDTO, card);
        return cardRepository.save(card);
    }

    @Override
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
