package com.livioneiva.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	// operação basica do CRUD - Consulta
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

	//CRiação basica CRUD - Consulta por ID
	//@GetMapping(value = "/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){//@PathVariable = atraves dessa anotations q recebemos o id do @GetMapping
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	//Criação basica CRUD - Insersão
	//@PostMapping //ou annotation a baixo
	@RequestMapping(method = RequestMethod.POST) //a requisição vinda URL é uma inserção de dados
	ResponseEntity<Void> insert(@RequestBody UserDTO userDto){ //o Void retorna um obj vazio
		User obj = service.fromUserDTO(userDto);//inserindo as informações da reguisição no obj user
		obj = service.insert(obj); //insersão na base de dados, convertendo o DTO para user
		//vamos colocar no cabecalho da requisiçao a url com novo recurso criado, o codigo a baixo é especifico do spring, faz exaamente isso
		//o o codigo está abaixo vai pegar o novo endereco foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();//created retorna codigo 201 CODIGO DE STATUS é de criação
		/*
		 created(uri) = codigo de status http quando criamos um novo recurso, no arugumento created inseerimos o caminho é a uri
		 ResponseEntity.created(uri).build() = retorna uma resposta vazia(Void) com codigo 201
		 e com o cabecalho contendo a a localização(uri) com novo recurso criado
		 */
		
	}
	
	//Criaçao basica CRUD - deleção
	//@DeleteMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//@PutMapping(value = "/{id}")//na requisição tem vim o id como o corpo da requisição
	@RequestMapping(value = "/{id}",  method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id){
		User obj = service.fromUserDTO(userDto);//instanciando o obj com dados q vem da requisição
		obj.setId(id);//o obj vai receber o id do obj da requisição, garante q o obj vai ter o id da requisição
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
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