package dev.service;

import dev.domain.*;
import dev.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public Object loginUser(String email, String password) {
        // Find user by email and password in the users table
        UsersEntity user = authRepository.findUserByEmailAndPassword(email, password);

//        System.out.println("running1!");
        if (user == null) {
//            return false; // User not found
            System.out.println("user not found!");
        }
//        System.out.println("running1!");
//
//        System.out.println(user.getEmail());

        // Check which table the user belongs to and verify the password
        AdminEntity admin = authRepository.findAdminByEmailAndPassword(email, password);
        RecruiterEntity recruiter = authRepository.findRecruiterByEmailAndPassword(email, password);
        ProfileEntity programmer = authRepository.findProgrammerByEmailAndPassword(email, password);
        CompanyEntity company = authRepository.findCompanyByEmailAndPassword(email, password);

        // You can handle the login logic here based on the user's role

        if (admin != null) {
            return new LoginResponse(user, admin);
        } else if (recruiter != null) {
            return new LoginResponse(user, recruiter);
        } else if (programmer != null) {
            return new LoginResponse(user, programmer);
        } else if (company != null) {
            return new LoginResponse(user, company);
        } else {
            return null;
        }
//        return admin != null || recruiter != null || programmer != null || company != null;
    }
}
