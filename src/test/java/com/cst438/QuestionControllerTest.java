package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.domain.Question;
import com.cst438.DTO.QuestionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllQuestions() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/questions")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Question[] questions = fromJsonString(response.getContentAsString(), Question[].class);
        assertNotNull(questions);
    }

    @Test
    public void testAddQuestion() throws Exception {
        MockHttpServletResponse response;

        Question question = new Question("What is 2+2?", "3", "4", "5", "6", "4");

        response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(question))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Question result = fromJsonString(response.getContentAsString(), Question.class);
        assertNotNull(result);
        assertNotNull(result.getQuestion_id());
    }
    
//    @Test
//    public void testUpdateQuestion() throws Exception {
//        MockHttpServletResponse response;
//
//        Long questionId = 1L;
//        QuestionDTO questionDTO = new QuestionDTO("Updated Question Text", "Option A", "Option B", "Option C", "Option D", 'A');
//
//        response = mvc.perform(
//                MockMvcRequestBuilders
//                        .put("/questions/" + questionId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(questionDTO))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertEquals(200, response.getStatus());
//
//    }




    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T fromJsonString(String str, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(str, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
