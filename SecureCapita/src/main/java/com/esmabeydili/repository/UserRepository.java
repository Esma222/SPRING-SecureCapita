package com.esmabeydili.repository;

import com.esmabeydili.domain.User;

import java.util.Collection;

public interface UserRepository <T extends User>{
    //basic CRUD Operation

    T create(T data);

    Collection<T> list(int page, int pageSize);

    T get(Long id);

    T update(T data);

    void delete(Long id);


    //More complex operations




}
