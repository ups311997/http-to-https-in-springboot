package com.stackroute.cache.springredisexample;

import com.stackroute.cache.springredisexample.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;//to interact with redis server

    private HashOperations hashOperations;


    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    // to save the user details
    public void save(User user) {
        hashOperations.put("USER", user.getId(), user);
    }

    @Override
    // to show the details of all the users
    public Map<String, User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    // to find the user details by userid
    public User findById(String id) {
        return (User) hashOperations.get("USER", id);
    }

    @Override
    // to update the user details
    public void update(User user) {
        save(user);
    }

    @Override
    // to delete the details of the user by userid
    public void delete(String id) {

        hashOperations.delete("USER", id);
    }
}
