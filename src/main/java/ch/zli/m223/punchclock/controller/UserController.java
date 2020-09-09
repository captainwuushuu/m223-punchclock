package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailsServiceImpl.save(user);
    }

    /**
     * create and return new user
     * @param user
     * @return user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createEntry(@Valid @RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDetailsServiceImpl.save(user);
    }

    /**
     * Returns all Users
     * @return List<User>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationUser> getAllUsers() {
        return  applicationUserRepository.findAll();
    }

    /**
     * Delete User with specified id
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") long id) {
        userDetailsServiceImpl.deleteUser(id);
    }
    /**
     * Update User with specified id and data
     * @param id
     * @param user
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") long id, @Valid @RequestBody ApplicationUser applicationUser){
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        userDetailsServiceImpl.updateUser(applicationUser,id);
    }
}
