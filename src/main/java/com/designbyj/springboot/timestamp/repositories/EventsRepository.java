package com.designbyj.springboot.timestamp.repositories;

import com.designbyj.springboot.timestamp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {

}
