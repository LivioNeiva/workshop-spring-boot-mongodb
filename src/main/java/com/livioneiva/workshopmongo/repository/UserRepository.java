package com.livioneiva.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.livioneiva.workshopmongo.domain.User;

//ja temos acesso ao banco de dados MongoDB
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
}
