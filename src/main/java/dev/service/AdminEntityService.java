package dev.service;

import dev.domain.AdminEntity;
import dev.repository.AdminEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("rawtypes")
@Service
@Transactional
public class AdminEntityService {

    private final AdminEntityRepository adminEntityRepository;

    @Autowired
    public AdminEntityService(AdminEntityRepository adminEntityRepository) {
        this.adminEntityRepository = adminEntityRepository;
    }

    public List getAllAdminEntities() {
        return adminEntityRepository.getAllAdminEntities();
    }

    public void createAdminEntity(AdminEntity adminEntity) {
        adminEntityRepository.createAdminEntity(adminEntity);
    }

    public void updateAdminEntity(AdminEntity adminEntity) {
        adminEntityRepository.updateAdminEntity(adminEntity);
    }

    public void deleteAdminEntity(Long adminEntityId) {
        adminEntityRepository.deleteAdminEntity(adminEntityId);
    }

    public AdminEntity findByEmail(String email) {
        return adminEntityRepository.findByEmail(email);
    }
}
