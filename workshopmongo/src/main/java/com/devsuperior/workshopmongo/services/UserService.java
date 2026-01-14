package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.dto.UserDTO;
import com.devsuperior.workshopmongo.models.entities.User;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import com.devsuperior.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        User entity = getEntityById(id);
        return new UserDTO(entity);
    }

    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity = userRepository.insert(entity);
        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO dto) {
        User entity = getEntityById(id);
        copyDtoToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    private User getEntityById(String id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado!"));
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }
}
