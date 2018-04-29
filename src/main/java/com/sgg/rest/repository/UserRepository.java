package com.sgg.rest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sgg.rest.model.Account;
import com.sgg.rest.model.ApplicationUser;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends JpaRepository<ApplicationUser, Integer>,JpaSpecificationExecutor<ApplicationUser>  {

    Long countByName(String name);

    @Transactional
    Long deleteByName(String name);

    @Transactional
    List<ApplicationUser> removeByName(String name);
    
    @Transactional
    ApplicationUser findByName(String name);
    
    @Query(value="select * from application_user", nativeQuery=true )
//    @Modifying
    public List<ApplicationUser> findallAccounts();
    
//    @Modifying
//    @Query("UPDATE User c SET c.name = :name WHERE c.id = :id")
//    Integer setNameForId(@Param("name"); String name, @Param("id")
//    @Query(value="select * from application_user where name=?1", nativeQuery=true)
//    Page<ApplicationUser> findStudent(String name, Pageable pageable);
}
