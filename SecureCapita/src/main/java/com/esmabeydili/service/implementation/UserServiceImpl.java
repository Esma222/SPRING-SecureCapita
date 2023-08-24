package com.esmabeydili.service.implementation;

import com.esmabeydili.domain.User;
import com.esmabeydili.dto.UserDTO;
import com.esmabeydili.dtomapper.UserDTOMapper;
import com.esmabeydili.repository.UserRepository;
import com.esmabeydili.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
