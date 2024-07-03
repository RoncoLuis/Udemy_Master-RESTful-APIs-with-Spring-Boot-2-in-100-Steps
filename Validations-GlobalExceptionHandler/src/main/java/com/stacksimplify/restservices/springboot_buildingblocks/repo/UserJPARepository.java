package com.stacksimplify.restservices.springboot_buildingblocks.repo;


import com.stacksimplify.restservices.springboot_buildingblocks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User,Long> {
    //Custom queries - findByUsername
    public User findByUsername(String username);
}
