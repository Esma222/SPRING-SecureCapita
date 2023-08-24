package com.esmabeydili.repository;

import com.esmabeydili.domain.Role;
import com.esmabeydili.domain.User;

import java.util.Collection;

public interface RoleRepository  <T extends Role>{
    //basic CRUD Operation

    T create(T data);

    Collection<T> list(int page, int pageSize);

    T get(Long id);

    T update(T data);

    void delete(Long id);


    //More complex operations

    void addRoleToUser(Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId,String email);



}

