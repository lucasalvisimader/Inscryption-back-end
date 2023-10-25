package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.dto.UserRankingUpdateDTO;
import br.senai.sc.trunfo.model.dto.UserUpdateDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.entity.User;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.senai.sc.trunfo.security.enums.Profile.PLAYER;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private CardService cardService;

    @Autowired
    private void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public User save(UserDTO objectDTO) {
        return getUser(objectDTO);
    }

    private User getUser(UserDTO objectDTO) {
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUsername(objectDTO.getUsername());
        user.setPassword(objectDTO.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities(List.of(PLAYER));
        List<Card> cards = new ArrayList<>();
        for (long i = 1L; i <= 5L; i++) {
            cards.add(cardService.list(i));
        }
        user.setCards(cards);
        return userRepository.save(user);
    }

    public User list(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User listLogin(String username, String password) {
        return userRepository.findByUsername(username);
    }

    public User update(Long id, UserUpdateDTO objectDTO) {
        User user = list(id);
        BeanUtils.copyProperties(objectDTO, user);
        return userRepository.save(user);
    }

    public User update(Long id, UserRankingUpdateDTO objectDTO) {
        User user = list(id);
        BeanUtils.copyProperties(objectDTO, user);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
