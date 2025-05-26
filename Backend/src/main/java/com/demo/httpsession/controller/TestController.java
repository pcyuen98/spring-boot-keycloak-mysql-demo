package com.demo.httpsession.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.httpsession.exceptions.DemoAppException;
import com.demo.httpsession.utility_classes.ResponseEntityUtil;

@RestController
@RequestMapping("/demo/test/v1")
public class TestController {

	private final ApplicationContext applicationContext;

	public TestController(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@GetMapping("/login/client")
	public ResponseEntity<Map<String, Object>> login() {
		String keycloakUrl = "http://localhost:8080/realms/master/protocol/openid-connect/token";
		String clientId = "angularfront";
		String username = "matabot";
		String password = "password";
		String scope = "openid profile email";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "password");
		map.add("username", username);
		map.add("password", password);
		map.add("client_id", clientId);
		map.add("scope", scope);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response;
		try {
			response = restTemplate.postForEntity(keycloakUrl, request, Map.class);
		} catch (Exception e) {
			return ResponseEntityUtil.getResponseEntity("Login failed", HttpStatus.UNAUTHORIZED, e);
		}

		if (response.getStatusCode().is2xxSuccessful()) {
			Map<String, Object> responseBody = response.getBody();
			String accessToken = (String) responseBody.get("access_token");
			if (accessToken != null) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "Login successful");
				result.put("access_token", accessToken);

				return ResponseEntityUtil.getResponseEntity(result, HttpStatus.OK, null);
			} else {
				return ResponseEntityUtil.getResponseEntity("Access token not found", HttpStatus.BAD_REQUEST,
						new DemoAppException("Access token not found"));
			}
		} else {
			return ResponseEntityUtil.getResponseEntity("Login Successful", HttpStatus.OK, null);
		}
	}

	@PreAuthorize("isAuthenticated() and hasRole('keycloak')")
	@GetMapping("/role/redis")
	public ResponseEntity<Object> isRoleRedis() {
		return ResponseEntity.ok(Collections.singletonMap("message", "Verified from BE. This user has a redis role"));
	}

	//@PreAuthorize("isAuthenticated() and hasRole('KEYCLOAK')")
	@GetMapping("/role/keycloak")
	public ResponseEntity<Object> isRoleUser() {
		return ResponseEntity
				.ok(Collections.singletonMap("message", "Verified from BE. This user has a keycloak role"));
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/test")
	public ResponseEntity<Object> test() {
		return ResponseEntity.ok(Collections.singletonMap("message", "Verified from BE. Token is valid"));
	}


	@GetMapping("/security/context")
	public ResponseEntity<Object> getSecurityContext() {

		return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@GetMapping("/context/info")
	public ResponseEntity<Object> getContextInfo() {
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		List<String> beanLists = new ArrayList<String>();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            if (bean.getClass().getPackageName().startsWith("com.demo")) {
            	beanLists.add(beanName);
            }
        }
		return ResponseEntity.ok(beanLists);
	}

	@GetMapping("/context/beans")
	public ResponseEntity<Map<String, Object>> getFilteredBeanNames() {
		String[] allBeans = applicationContext.getBeanDefinitionNames();

		List<String> filteredBeans = Arrays.stream(allBeans).filter(name -> name.startsWith("com.wcc")) // Only beans
																										// from your
																										// package
				.limit(100) // Limit the output to 100 entries
				.collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("filteredBeanNames", filteredBeans);
		response.put("totalFiltered", filteredBeans.size());

		return ResponseEntity.ok(response);
	}

	@GetMapping("/error/test")
	public ResponseEntity<Map<String, Object>> testGeneralError() {
		throw new DemoAppException("Just some Error Testing");
	}
}
