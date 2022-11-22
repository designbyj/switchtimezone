package com.designbyj.springboot.timestamp.services;

import com.designbyj.springboot.timestamp.models.Event;
import com.designbyj.springboot.timestamp.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class EventsService {

    private final EventsRepository eventsRepository;

    @Autowired
    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<Event> findAll() {
        return eventsRepository.findAll();
    }

    public Object findOne(int id) {
        Optional<Event> foundPerson = eventsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(@Valid Event event) {
        event.setCreatedAt(new Date());
        eventsRepository.save(event);
    }


    public String changeTime(int id, String timezone) {
        Event event = (Event)findOne(id);
        return event.change(timezone, event);
    }


    @Transactional
    public void delete(int id) {
        eventsRepository.deleteById(id);
    }
}
