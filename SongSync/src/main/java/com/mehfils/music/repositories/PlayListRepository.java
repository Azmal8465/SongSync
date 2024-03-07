package com.mehfils.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehfils.music.entities.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer>{

}
