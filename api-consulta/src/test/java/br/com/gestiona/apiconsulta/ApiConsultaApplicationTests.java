package br.com.gestiona.apiconsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ApiConsultaApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("resource")
    static PostgreSQLContainer<?> postgresContainerTest = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("creditosdb")
            .withUsername("admin")
            .withPassword("admin");

    @SuppressWarnings({ "deprecation", "resource" })
    static KafkaContainer kafkaContainerTest = new KafkaContainer(
		DockerImageName.parse("confluentinc/cp-kafka:latest")
				.asCompatibleSubstituteFor("apache/kafka"))
		.withStartupTimeout(Duration.ofMinutes(3));

    @SuppressWarnings("deprecation")
    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainerTest::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainerTest::getUsername);
        registry.add("spring.datasource.password", postgresContainerTest::getPassword);
        registry.add("spring.kafka.bootstrap-servers", kafkaContainerTest::getBootstrapServers);
        registry.add("spring.flyway.url", postgresContainerTest::getJdbcUrl);
        registry.add("spring.flyway.user", postgresContainerTest::getUsername);
        registry.add("spring.flyway.password", postgresContainerTest::getPassword);
		registry.add("spring.flyway.schemas", () -> "creditos");
    	registry.add("spring.flyway.default-schema", () -> "creditos");
    }

	static {
		postgresContainerTest.start();
		kafkaContainerTest.start();
	}

	@BeforeAll
	static void setup(@Autowired Flyway flyway) {
		flyway.migrate();
	}
    
	@Test
    void contextLoads() {
        assertTrue(postgresContainerTest.isRunning());
        assertTrue(kafkaContainerTest.isRunning());
    }
    
	@Test
    void shouldFindByNFSeOk() {
        // Exemplo simples de teste integrado aqui.
        ResponseEntity<String> response = restTemplate.getForEntity("/api/creditos/7891011", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	@Test
    void shouldFindByCreditNumberOk() {
        // Exemplo simples de teste integrado aqui.
        ResponseEntity<String> response = restTemplate.getForEntity("/api/creditos/credito/789012", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotFoundOnSearchByNFSe() {
        // Exemplo simples de teste integrado aqui.
        ResponseEntity<String> response = restTemplate.getForEntity("/api/creditos/123456789", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

	@Test
    void shouldNotFindByCreditNumber() {
        // Exemplo simples de teste integrado aqui.
        ResponseEntity<String> response = restTemplate.getForEntity("/api/creditos/credito/789012123435", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

