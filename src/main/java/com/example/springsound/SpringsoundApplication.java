package com.example.springsound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springsound.principal.Principal;
import com.example.springsound.repository.ArtistaRepository;
import com.example.springsound.repository.MusicaRepository;

@SpringBootApplication
public class SpringsoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repository;

	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringsoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, musicaRepository);
		principal.exibeMenu();
	}

}
