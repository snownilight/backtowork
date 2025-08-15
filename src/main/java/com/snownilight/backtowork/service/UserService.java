package com.snownilight.backtowork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snownilight.backtowork.mapper.UsersMapper;
import com.snownilight.backtowork.model.po.Users;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UsersMapper usersMapper;

    public UserService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    public List<Users> getAllUsers() {
        return usersMapper.findAll();
    }

    public Users getUserById(Long id) {
        return usersMapper.findById(id);
    }

    public Users createUser(Users user) {
        usersMapper.insert(user);
        Long id = user.getId();
        return usersMapper.findById(id);
    }

    public Optional<Users> updateUser(Users user) {
        boolean isSuccess = usersMapper.update(user);
        if (isSuccess) {
            return Optional.of(usersMapper.findById(user.getId()));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return usersMapper.delete(id);
    }

}