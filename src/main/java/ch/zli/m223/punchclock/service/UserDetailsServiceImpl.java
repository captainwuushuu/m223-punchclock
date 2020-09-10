package ch.zli.m223.punchclock.service;
import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    public ApplicationUser save(ApplicationUser applicationUser) {
        return applicationUserRepository.saveAndFlush(applicationUser);
    }

    public void deleteUser(long id) {
        applicationUserRepository.deleteById(id);
    }

    public List<ApplicationUser> findAll() {
        return applicationUserRepository.findAll();
    }

    public ApplicationUser updateUser(ApplicationUser applicationUser) {
        if (applicationUserRepository.existsById(applicationUser.getId())) {
            applicationUserRepository.saveAndFlush(applicationUser);
            return applicationUser;
        } else {
            throw new UsernameNotFoundException(applicationUser.getUsername());
        }
    }
}