package com.Nil.MongoDemo.Repoitories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.Nil.MongoDemo.Model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
	
	 Optional<Hotel> findById(String id);

}
