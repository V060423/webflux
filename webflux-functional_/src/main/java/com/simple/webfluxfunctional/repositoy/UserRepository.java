package com.simple.webfluxfunctional.repositoy;

import com.simple.webfluxfunctional.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangbowen
 * @Description TODO
 * @Date 2018/4/19 14:26
 */
public interface UserRepository {
     Mono<User> getUserById(Long id);

     Flux<User> getAllUsers();

     Mono<Void> saveUser(Mono<User> user);

     Mono<User> putUser(Long id, Mono<User> user);

     Mono<String> deleteUser(Long id);
}
