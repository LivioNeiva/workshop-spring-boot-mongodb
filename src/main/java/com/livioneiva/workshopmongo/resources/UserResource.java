package com.livioneiva.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	//@GetMapping // ou posso usar a opção abaixo
	@RequestMapping(method = RequestMethod.GET)//para dizer esse metodo vai ser um edpoint rest no caminho /users
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = new ArrayList<>();
		
		User maria = new User("1001", "Maria Brown", "maria@gmail.com");
		User alex = new User("1002", "Alex Green", "alex@gmail.com");
		
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
		
	}
}
/*
List é uma interface, para instanciar uma interface temos q instaciar uma implementção
no caso do List usamos o implementação ArraysList

ResponseEntity = encapsular todo auma estrutura nescessaria para retornamos reposta http
ja com possiveis cabeçalhos, possiveis erros...
.ok() = vai instanciar o ResponseEntity ja com codigo de resposta http, resposta foi com sucesso. status 200 ok
.body() = vai definir o corpo da resposta
*/