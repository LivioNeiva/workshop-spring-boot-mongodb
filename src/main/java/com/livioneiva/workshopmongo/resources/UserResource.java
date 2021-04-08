package com.livioneiva.workshopmongo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.workshopmongo.domain.User;
import com.livioneiva.workshopmongo.dto.UserDTO;
import com.livioneiva.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	/*
	 * auto dependencia com a classe UserRepository quando instanciamos uma auto
	 * dependencia, como @AutoWired o proprio Spring vai procurar uma definiçao para
	 * o objeto e vai instanciar o objeto
	 */
	@Autowired // é o mecanismo de ingeção de dependencia automatica do spring boot
	private UserService service;

	// @GetMapping // ou posso usar a opção abaixo
	@RequestMapping(method = RequestMethod.GET) // para dizer esse metodo vai ser um edpoint rest no caminho /users
	public ResponseEntity<List<UserDTO>> findAll() {

		// List<User> list = service.findAll(); // ou exemplo abaixo
		List<User> list = new ArrayList<User>();
		list.addAll(service.findAll());
		// estou adicionando as informações do obj list do tipo User para obj UserDTO
		// usando map
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	//@GetMapping(value = "/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){//@PathVariable = atraves dessa anotations q recebemos o id do @GetMapping
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
}
/*
 * List é uma interface, para instanciar uma interface temos q instaciar uma
 * implementção no caso do List usamos o implementação ArraysList
 * 
 * ResponseEntity = encapsular todo auma estrutura nescessaria para retornamos
 * reposta http ja com possiveis cabeçalhos, possiveis erros... .ok() = vai
 * instanciar o ResponseEntity ja com codigo de resposta http, resposta foi com
 * sucesso. status 200 ok .body() = vai definir o corpo da resposta
 */