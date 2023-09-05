package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.dto.UserRankingUpdateDTO;
import br.senai.sc.trunfo.model.dto.UserUpdateDTO;
import br.senai.sc.trunfo.model.entity.Card;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.UserRepository;
import br.senai.sc.trunfo.security.enums.Profile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.senai.sc.trunfo.model.entity.User;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements ServiceGeneralized<User, UserDTO, Long> {
    private final UserRepository userRepository;
    private CardService cardService;

    @Autowired
    private void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public User save(UserDTO objectDTO) {
        User user = new User();
        BeanUtils.copyProperties(objectDTO, user);
        return userRepository.save(user);
    }

    public User saveUser(UserDTO objectDTO) {
        List<Card> cards = new ArrayList<>();
        for (long i = 1L; i <= 6L; i++) {
            cards.add(cardService.list(i));
        }
        objectDTO.setCards(cards);
        return save(objectDTO);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User list(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User listLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new NotFoundException("User Not Found"));
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

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
