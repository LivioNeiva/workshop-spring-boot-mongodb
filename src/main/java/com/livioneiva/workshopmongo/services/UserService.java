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
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	/*
	esse obj tipo user é os dados q vai vim na requisição, esses dados nao tem vinculum com dba
	temos q buscar o obj origina q esta no banco de dados, alterar o obj do banco de dados
	com obj user q vem da requisição e salvar o obj com as novas alterações
	 */
	public User update(User obj) {
		User newObj = findById(obj.getId());//estamos buscando o no banco de dados, vobj.getId() = esse é o id q pertence ao obj q veio na requisição
		updateData(newObj,obj);//esse metodo é responsavel para copiar os dados da requisição(obj), para o novo obj(newObj)
		return repository.save(newObj);//salva as alterações no dba
	}
	
	public void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
	}
	
	public User fromUserDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}

/*
o que é o metodo fromDTO, é um metodo que vai pegar um TDO, e instanciar um usuario


*/
