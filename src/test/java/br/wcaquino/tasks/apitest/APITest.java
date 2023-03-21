package br.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}
	
	@Test
	public void deveRetornarSucesso200() {
		RestAssured
		.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
			.and().log().all()	
		;
		
	}
	@Test
	public void deveAdicionarNovo201() {
		RestAssured
		.given().body("{\"task\":\"API na adicao 03\",\"dueDate\":\"2023-10-03\"}")
		.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log().all()
			.statusCode(201)
			
				
		;
	}
		@Test
		public void naoDeveAdicionarInvalido() {
			RestAssured
			.given().body("{\"task\":\"API na adicao 03\",\"dueDate\":\"2020-10-03\"}")
			.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.log().all()
				.statusCode(400)
				.body("message", CoreMatchers.containsString("Due date must not be in past"))
				
					
			;
				
	}

}

