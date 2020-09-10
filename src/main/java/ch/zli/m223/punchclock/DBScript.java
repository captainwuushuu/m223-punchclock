package ch.zli.m223.punchclock;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Departement;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.CategoryRepository;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.repository.DepartementRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DBScript implements CommandLineRunner {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    EntryRepository entryRepository;
    @Autowired
    DepartementRepository departementRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        // Create users
        generateUser("admin", "admin");
        generateUser("controlling", "controlling");
        // Create categories
        generateCategory("Backend");
        generateCategory("Frontend");
        // Create departements
        generateDepartement("Zurich");
        generateDepartement("Winterthur");
        // Create entries
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String checkIn1 = "2020-09-04 18:55";
        String checkIn2 = "2020-09-04 13:00";
        String checkOut1 = "2020-09-04 20:55";
        String checkOut2 = "2020-09-04 15:55";
        Category cat1 = categoryRepository.findById((long)1).get();
        Departement dep1 = departementRepository.findById((long)1).get();
        generateEntry(LocalDateTime.parse(checkIn1, formatter), LocalDateTime.parse(checkOut1, formatter), cat1, dep1 ,"admin");
        generateEntry(LocalDateTime.parse(checkIn2, formatter), LocalDateTime.parse(checkOut2, formatter), cat1, dep1 ,"controlling");
    }

    private void generateUser(String username, String password) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setPassword(bCryptPasswordEncoder.encode(password));
        applicationUserRepository.save(applicationUser);
    }

    private void generateCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }

    private void generateDepartement(String departementName) {
        Departement departement = new Departement();
        departement.setDepartementName(departementName);
        departementRepository.save(departement);
    }

    private void generateEntry(LocalDateTime checkIn, LocalDateTime checkOut, Category category, Departement departement, String username) {
        Entry entry = new Entry();
        entry.setCheckIn(checkIn);
        entry.setCheckOut(checkOut);
        entry.setCategory(category);
        entry.setDepartement(departement);
        entry.setUser(applicationUserRepository.findByUsername(username));
    }
}
