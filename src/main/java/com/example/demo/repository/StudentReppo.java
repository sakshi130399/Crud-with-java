package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Students;

public interface StudentReppo extends MongoRepository<Students, Integer> {

}
