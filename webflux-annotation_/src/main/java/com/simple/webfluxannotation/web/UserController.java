package com.simple.webfluxannotation.web;

import com.simple.webfluxannotation.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangbowen
 * @Description 使用基于注解的方式实现响应式的webflux
 * @Date 2018/4/19 13:41
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 存储数据的map
     */
    public Map<Long,User> users =new HashMap<>();

    /**
     * 初始化数据
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        users.put(Long.valueOf(1), new User(1L,"Jack","男",20L));
        users.put(Long.valueOf(2), new User(2L, "Peter", "女", 25L));
    }
    /**
     * 获取所有用户
     * http://localhost:8080/api/user/all
     * @return
     */
    @GetMapping("/user/all")
    public Flux<User> getAll() {
        return Flux.fromIterable(users.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList()));
    }

    /**
     * 根据id获取单个用户 http://localhost:8080/api/user/1
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Mono<User> getCustomer(@PathVariable Long id){
        return Mono.justOrEmpty(users.get(id));
    }
    /**
     * 创建用户
     * http://localhost:8080/api/user/1
     * {
     * 	"id":3,
     * 	 "name":"Ven",
     * 	 "age":25,
     * 	 "sex":"男"
     * }
     * @param user
     * @return
     */
    @PostMapping("/user/create")
    public Mono<ResponseEntity<String>> create(@RequestBody User user) {
        if(!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            logger.info("########### create:" + user);
            return Mono.just(new ResponseEntity<>("add Successfully !", HttpStatus.CREATED));
        }
        return Mono.just(new ResponseEntity<>("user already exists！",HttpStatus.OK));
    }

    /**
     * 修改用户
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/user/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable Long id, @RequestBody User user) {
        if(users.containsKey(id)) {
            user.setId(id);
            users.put(id, user);
            System.out.println("########### update:" + user);
            return Mono.just(new ResponseEntity<>(user, HttpStatus.CREATED));
        }
        return Mono.just(new ResponseEntity<>( new User(), HttpStatus.CREATED));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {
        users.remove(id);
        return Mono.just(new ResponseEntity<>("Delete Successfully!", HttpStatus.ACCEPTED));
    }

}
