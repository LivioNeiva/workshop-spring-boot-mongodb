package com.livioneiva.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.workshopmongo.domain.User;
import com.livioneiva.workshopmongo.dto.UserDTO;
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
	/*
	obs. como o repository retorna um objeto, temos manter o padrao de nao usar void
	e sim o tipo do metodo, nesse caso o tipo é use.
	 */
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public User fromUserDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
}

/*
o que é o metodo fromDTO, é um metodo que vai pegar um TDO, e instanciar um usuario


*/
