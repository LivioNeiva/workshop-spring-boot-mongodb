package com.livioneiva.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livioneiva.workshopmongo.domain.User;
import com.livioneiva.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	/*
	auto dependencia com a classe UserRepository
	quando instanciamos uma auto dependencia, como @AutoWired o proprio Spring vai
	procurar uma definiçao para o objeto e vai instanciar o objeto
	 */
	@Autowired //é o mecanismo de ingeção de dependencia automatica do spring boot
	UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
}
