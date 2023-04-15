package com.munro.api;

import com.munro.api.controller.MunroController;
import com.munro.api.data.TestData;
import com.munro.api.service.MunroService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MunroController.class)
@RunWith(SpringRunner.class)
class ApiApplicationTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private MunroService munroService;

	@Test
	@SneakyThrows
	void returnMunros(){
		var userId = 1L;
		var munroCollection = TestData.getMunros();

		given(munroService.getMunros(userId)).willReturn(munroCollection);
		munroService.getMunros(userId);

		mvc.perform(get("/api/v1/munros/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("test")));
	}
}
