package com.designbyj.springboot.timestamp;

import com.designbyj.springboot.timestamp.models.Event;
import com.designbyj.springboot.timestamp.repositories.EventsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EventsRepository repository;

    @Test
    public void givenEvent_whenAdd_thenStatus201andEventReturned() throws Exception {
        Event event = new Event();
        Mockito.when(repository.save(Mockito.any())).thenReturn(event);
        mockMvc.perform(
                post("/events")
                        .content(objectMapper.writeValueAsString(event))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
    }
}
