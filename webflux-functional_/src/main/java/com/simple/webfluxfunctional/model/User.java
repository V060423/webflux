package com.simple.webfluxfunctional.model;

/**
 * @author wangbowen
 * @Description TODO
 * @Date 2018/4/19 13:44
 */
public class User {
    private Long id;

    private String name;

    private Long age;

    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User() {
    }

    public User(Long id, String name, String sex, Long age) {
        this.id = id;
        this.name =name;
        this.sex = sex;
        this.age = age;
    }
}
