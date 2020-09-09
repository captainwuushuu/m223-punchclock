package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Departement;
import ch.zli.m223.punchclock.service.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    private DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Departement> getAllDepartements() {
        return departementService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departement createDepartement(@Valid @RequestBody Departement departement) {
        return departementService.createDepartement(departement);
    }

    /**
     * Deletes a departement
     *
     * @param id of the to be deleted departement
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        departementService.deleteDepartement(id);
    }
    /**
     * Updates a departement.
     *
     * @param departement to be updated
     * @return an updated departement
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Departement update(@RequestBody Departement departement) {
        return departementService.updateDepartement(departement);
    }
}
