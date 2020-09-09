package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Departement;
import ch.zli.m223.punchclock.repository.DepartementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartementService {
    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public Departement createDepartement(Departement departement) {
        return departementRepository.saveAndFlush(departement);
    }

    public List<Departement> findAll() {
        return departementRepository.findAll();
    }

    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }

    public Departement updateDepartement(Departement departement) {
        if (departementRepository.existsById(departement.getId())) {
            departementRepository.saveAndFlush(departement);
            return departement;
        } else {
            return createDepartement(departement);
        }
    }
}
