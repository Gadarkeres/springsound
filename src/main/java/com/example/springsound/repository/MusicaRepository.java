package com.example.springsound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsound.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

}
