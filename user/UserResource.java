package com.spring.restful.webservices.demos.restfulwebservices.user;

import com.spring.restful.webservices.demos.restfulwebservices.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserProducer userProducer;


    // GET /users
    // Retrieve All users

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers()
    {
        return userDaoService.findAll();
    }

    // Retreive User given his id

    @GetMapping(path = "/users/{id}")
    public User retrieveUserDetails(@PathVariable int  id)
    {
      User user = userDaoService.findOne(id);

      if(user == null)
        throw new UserNotFoundException("id-{}" + id);

      return user;
    }

    // @RequestBody maps the information passed in the body to the parameter defined as argument
    @PostMapping(path = "/users")
    public void createUser(@RequestBody User user )
    {
          User savedUser =   userDaoService.save(user);
          userProducer.sendNewUser(savedUser.getId(), savedUser.getName(), savedUser.getBirthDate());
    }


    // @RequestBody maps the information passed in the body to the parameter defined as argument and returning the uri
    // of created resource
  /*  @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }*/

    @DeleteMapping(path = "/users/{id}")
    public void  deleteUser(@PathVariable int  id)
    {
        User user = userDaoService.deleteById(id);
        if(user == null)
            throw new UserNotFoundException("id-" + id);
    }






}
