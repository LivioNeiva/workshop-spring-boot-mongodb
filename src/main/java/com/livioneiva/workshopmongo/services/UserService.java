package com.livioneiva.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.workshopmongo.domain.User;
import com.livioneiva.workshopmongo.repository.UserRepository;
import com.livioneiva.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	/*
	 * auto dependencia com a classe UserRepository quando instanciamos uma auto
	 * dependencia, como @AutoWired o proprio Spring vai procurar uma definiçao para
	 * o objeto e vai instanciar o objeto
	 */
	@Autowired // é o mecanismo de ingeção de dependencia automatica do spring boot
	UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		
		/*A função "Optional" encapsula o retorno de algum metodo, 
		avaliando se é nulo ou não.... podendo tratar os null pointer exception */
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
		
		/*
		 findOne = vc passa id como argumento, ele retorna o obj, esse metodo aparti de 
		 versão 2 do string foi substituido  pelo findById()
		 User user = repository.findOne(id); 
		 */
	}
}
