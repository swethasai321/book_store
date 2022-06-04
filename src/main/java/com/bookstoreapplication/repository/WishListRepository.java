package com.bookstoreapplication.repository;

import com.bookstoreapplication.module.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer>{

}


