package com.an.apigw;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.matchers.Times;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MockServerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockserver.model.HttpRequest;

import static org.mockserver.model.HttpResponse.response;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class ApiGwApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiGwApplicationTests.class);
	
	@ClassRule
	 public static MockServerContainer mockServer = new MockServerContainer();

	 @Autowired
	 TestRestTemplate template;
	 
	   @BeforeClass
	    public static void init() {
	        System.setProperty("spring.cloud.gateway.httpclient.response-timeout", "5s");
	        System.setProperty("spring.cloud.gateway.routes[0].id", "savings-a");
	        System.setProperty("spring.cloud.gateway.routes[0].uri", "http://" + mockServer.getHost() + ":" + mockServer.getServerPort());
	        System.setProperty("spring.cloud.gateway.routes[0].predicates[0]", "Path=/savings/a/balance/");
	        System.setProperty("spring.cloud.gateway.routes[1].id", "savings-b");
	        System.setProperty("spring.cloud.gateway.routes[1].uri", "http://" + mockServer.getHost() + ":" + mockServer.getServerPort());
	        System.setProperty("spring.cloud.gateway.routes[1].predicates[0]", "Path=/savings/b/balance/");
	        //System.setProperty("spring.cloud.gateway.routes[0].filters[0]", "RewritePath=/account/(?<path>.*), /$\\{path}");
	        
	        MockServerClient client = new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort());
	        client.when(HttpRequest.request()
	                .withPath("/1"), Times.exactly(3))
	                .respond(response()
	                        .withStatusCode(500)
	                        .withBody("{\"errorCode\":\"5.01\"}")
	                        .withHeader("Content-Type", "application/json"));
	        client.when(HttpRequest.request() 
	                .withPath("/1"))
	                .respond(response()
	                        .withBody("{\"balance\":7000\"}")
	                        .withHeader("Content-Type", "application/json"));
	        client.when(HttpRequest.request()
	                .withPath("/2"))
	                .respond(response()
	                        .withBody("{\"balance\":7000\"}")
	                        .withDelay(TimeUnit.MILLISECONDS, 60000)
	                        .withHeader("Content-Type", "application/json"));
	    }
	   
	   @Test
	    public void testAccountService() {
	        LOGGER.info("Sending /1...");
	        ResponseEntity<String> r = template.exchange("/savings/a/balance/", HttpMethod.GET, null, String.class, 1);
	        LOGGER.info("Received: status->{}, payload->{}", r.getStatusCodeValue(), r.getBody());
	        Assert.assertEquals(200, r.getStatusCodeValue());
	    }

	    @Test
	    public void testAccountServiceFail() {
	        LOGGER.info("Sending /2...");
	        ResponseEntity<String> r = template.exchange("/savings/b/balance/", HttpMethod.GET, null, String.class, 2);
	        LOGGER.info("Received: status->{}, payload->{}", r.getStatusCodeValue(), r.getBody());
	        Assert.assertEquals(504, r.getStatusCodeValue());
	    }

}
