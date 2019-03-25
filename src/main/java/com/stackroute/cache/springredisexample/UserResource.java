package com.stackroute.cache.springredisexample;


import com.stackroute.cache.springredisexample.model.User;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping(value = "/rest/user")
@CrossOrigin("*")
public class UserResource {

    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // saving the user with name and id
    @PostMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {
        userRepository.save(new User(id, name, 2000L));
        return userRepository.findById(id);
    }

    //updating the user
    @PutMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id,
                       @PathVariable("name") final String name) {
        userRepository.update(new User(id, name, 1000L));
        return userRepository.findById(id);
    }

    //deleting the user
    @DeleteMapping("/delete/{id}")
    public Map<String, User> delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return all();
    }

    //getting all the users
    @GetMapping("/All")
    public Map<String, User> all() {
        return userRepository.findAll();
    }
}
