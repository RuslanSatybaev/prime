package com.example.demo.repository;

import com.example.demo.entity.Request;
import com.example.demo.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByStatus(Status status);
}
