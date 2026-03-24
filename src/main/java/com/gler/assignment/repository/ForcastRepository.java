package com.gler.assignment.repository;

import com.gler.assignment.entity.Forcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForcastRepository extends JpaRepository<Forcast, Long> {
}