package com.grengof.restfulspringpgapp.message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}