package dev.service;

import dev.domain.CompanyEntity;
import dev.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public void createCompany(CompanyEntity companyEntity) {
        companyRepository.createCompany(companyEntity);
    }

    public void updateCompany(CompanyEntity companyEntity) {
        companyRepository.updateCompany(companyEntity);
    }

    public void deleteCompany(Long companyId) {
        companyRepository.deleteCompany(companyId);
    }

    public CompanyEntity getCompany(Long companyId) {
        return companyRepository.getCompany(companyId);
    }

    public CompanyEntity findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
}
