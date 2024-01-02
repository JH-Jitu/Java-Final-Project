package dev.service;

import dev.domain.UsersEntity;
import dev.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void saveUsersEntity(UsersEntity usersEntity) {
        usersRepository.save(usersEntity);
    }

    public UsersEntity findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
