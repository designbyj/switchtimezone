package com.designbyj.springboot.timestamp.controllers;

import com.designbyj.springboot.timestamp.models.Event;
import com.designbyj.springboot.timestamp.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("events", eventsService.findAll());
        return "events/index";
    }

    @GetMapping("/{id}/{timezone}")
    public String showTimezone(@PathVariable("id") int id, @PathVariable("timezone") String timezone, Model model) {
        model.addAttribute("before", eventsService.findOne(id));
        model.addAttribute("after", eventsService.changeTime(id, timezone));
        return "events/timezone";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", eventsService.findOne(id));
        return "events/show";
    }

    @GetMapping("/new")
    public String newEvent(@ModelAttribute("event") Event event) {
        return "events/new";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute("event") @Valid Event event,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "events/new";

        eventsService.save(event);
        return "events/new";
        //return "redirect:/events";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        eventsService.delete(id);
        return "redirect:/events";
    }
}
