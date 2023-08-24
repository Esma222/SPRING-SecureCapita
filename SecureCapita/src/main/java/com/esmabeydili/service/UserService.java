package com.esmabeydili.service;

import com.esmabeydili.domain.User;
import com.esmabeydili.dto.UserDTO;

public interface UserService {

    UserDTO createUser(User user);
}
