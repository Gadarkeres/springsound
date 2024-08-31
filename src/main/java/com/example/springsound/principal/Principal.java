package com.example.springsound.principal;

import java.util.Scanner;

import com.example.springsound.model.Artista;
import com.example.springsound.model.Musica;
import com.example.springsound.model.TipoArtista;
import com.example.springsound.repository.ArtistaRepository;
import com.example.springsound.repository.MusicaRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ArtistaRepository repository;
    private MusicaRepository musicaRepository;

    public Principal(ArtistaRepository repository, MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 9) {
            var menu = """
                    *** Screen Sound Músicas ***

                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas

                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musicas de qual artista? ");
        var artista = leitura.nextLine();
        List<Musica> musicas = repository.buscaMusicasPorArtista(artista);
        musicas.forEach(System.out::println);

    }

    private void listarMusicas() {
        List<Musica> musicas = musicaRepository.findAll();
        musicas.forEach(System.out::println);

    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "Y";

        while (cadastrarNovo.equalsIgnoreCase("Y")) {
            System.out.println("Informe o nome do artista: ");
            var nome = leitura.nextLine();

            System.out.println("Selecione o tipo de artista: (solo, dupla ou banda) ");
            var tipo = leitura.nextLine();

            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repository.save(artista);
            System.out.println("quer cadastrar mais um artista? (Y/N)");
            cadastrarNovo = leitura.nextLine();
        }

    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar musica de qual artista? ");
        var artista = leitura.nextLine();
        Optional<Artista> artistaEncontrado = repository.findByNomeContainingIgnoreCase(artista);
        if (artistaEncontrado.isEmpty())
            return;

        System.out.println("Escreva o nome da música: ");
        var nome = leitura.nextLine();
        Musica musica = new Musica();
        musica.setArtista(artistaEncontrado.get());
        musica.setTitulo(nome);

        musicaRepository.save(musica);

    }

}