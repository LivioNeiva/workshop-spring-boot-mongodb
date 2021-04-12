package com.livioneiva.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.livioneiva.workshopmongo.domain.User;

//ja temos acesso ao banco de dados MongoDB
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
}
/*
o MongoRepository já responde o próprio nome, é feito para MongoDB, um banco não 
relacional. Repository, CrudRepository, JpaRepository são é uma abstração para uso em bancos
 relacionais conhecidos, como Oracle, MySql, SQL Server, DB2, H2, etc.
*/