package com.Nil.MongoDemo.Repoitories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Nil.MongoDemo.Model.Student;


public interface StudentRepository extends MongoRepository<Student, String> {

	Student findByStudentNumber(long studentNumber);

	Student findByEmail(String email);

	List<Student> findAllByOrderByGpa();

}
