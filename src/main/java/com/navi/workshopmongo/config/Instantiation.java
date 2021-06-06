package com.navi.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.navi.workshopmongo.domain.Post;
import com.navi.workshopmongo.domain.User;
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
		
		Post p1 = new Post(null, ester,"Partiu viagem","Vou viajar para São Paulo. Abraços!",sdf.parse("2020/06/20"));
		Post p2 = new Post(null, ester,"Bom dia","Acordei feliz hoje!", sdf.parse("2020/06/24"));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		userRepository.saveAll(Arrays.asList(maria, ester, ivan));
	}

}
