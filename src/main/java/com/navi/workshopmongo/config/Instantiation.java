package com.navi.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.navi.workshopmongo.domain.Post;
import com.navi.workshopmongo.domain.User;
import com.navi.workshopmongo.dto.AuthorDTO;
import com.navi.workshopmongo.dto.CommentDTO;
import com.navi.workshopmongo.repository.PostRepository;
import com.navi.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("BRT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria de Lurdes", "maria@gmail.com");
		User ester = new User(null, "Ester Ferreira", "ester.ferreira@gmail.com");
		User ivan = new User(null, "Ivan Júnior", "ivanjunior@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, ester, ivan));
		
		Post p1 = new Post(null, new AuthorDTO(ester),"Partiu viagem","Vou viajar para São Paulo. Abraços!",sdf.parse("2020/06/20"));
		Post p2 = new Post(null, new AuthorDTO(ester),"Bom dia","Acordei feliz hoje!", sdf.parse("2020/06/24"));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, amor", sdf.parse("2020/06/20"), new AuthorDTO(ivan));
		CommentDTO c2 = new CommentDTO("Deus acompanhe", sdf.parse("2020/06/20"), new AuthorDTO(maria));
		CommentDTO c3 = new CommentDTO("Vai voltar quando?", sdf.parse("2020/06/20"), new AuthorDTO(maria));
		
		CommentDTO c4 = new CommentDTO("Boa dia amor, que bom que acordou feliz", sdf.parse("2020/06/24"), new AuthorDTO(ivan));
		CommentDTO c5 = new CommentDTO("Boa dia Ester, tenha um otimo dia", sdf.parse("2020/06/24"), new AuthorDTO(maria));
		
		p1.getComments().addAll(Arrays.asList(c1, c2, c3));
		p2.getComments().addAll(Arrays.asList(c4, c5));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		ester.getPosts().addAll(Arrays.asList(p1, p2));
		
		userRepository.save(ester);

	}

}
