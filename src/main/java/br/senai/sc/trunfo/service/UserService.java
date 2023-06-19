package br.senai.sc.trunfo.service;

import br.senai.sc.trunfo.model.dto.UserDTO;
import br.senai.sc.trunfo.model.dto.UserRankingUpdateDTO;
import br.senai.sc.trunfo.model.dto.UserUpdateDTO;
import br.senai.sc.trunfo.model.exception.NotFoundException;
import br.senai.sc.trunfo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import br.senai.sc.trunfo.model.entity.User;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements ServiceGeneralized<User, UserDTO, Long> {
    private final UserRepository userRepository;

    @Override
    public User save(UserDTO objectDTO) {
        User user = new User();
        BeanUtils.copyProperties(objectDTO, user);
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User list(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User listLogin(String name, String password) {
        return userRepository.findByNameAndPassword(name, password).orElseThrow(() -> new NotFoundException("User Not Found"));
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
