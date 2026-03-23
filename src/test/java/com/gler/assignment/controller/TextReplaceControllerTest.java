package com.gler.assignment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TextReplaceController.class)
class TextReplaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn400_whenTextLengthLessThan2() throws Exception {
        mockMvc.perform(get("/replace").param("text", "a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400_whenTextIsEmpty() throws Exception {
        mockMvc.perform(get("/replace").param("text", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnEmpty_whenTextLengthIs2() throws Exception {
        mockMvc.perform(get("/replace").param("text", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldTransformText_whenLengthGreaterThan2() throws Exception {
        mockMvc.perform(get("/replace").param("text", "abc"))
                .andExpect(status().isOk())
                .andExpect(content().string("*b$"));
    }

    @Test
    void shouldHandleSpecialCharacters() throws Exception {
        mockMvc.perform(get("/replace").param("text", "abc#20xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("*bc#20xy$"));
    }
}