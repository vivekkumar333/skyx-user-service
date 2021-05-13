package com.skyx.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skyx.user.entity.Card;

public interface CardRepo extends JpaRepository<Card, Long>{

}
