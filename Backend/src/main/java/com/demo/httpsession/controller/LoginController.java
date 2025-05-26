package com.demo.httpsession.controller;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.httpsession.model.bean.UserDTO;
import com.demo.httpsession.model.service.LoginService;
import com.demo.httpsession.model.service.UserService;
import com.demo.httpsession.utility_classes.ResponseEntityUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/demo/keycloak/v1")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private final LoginService loginService;
	
	private final UserService userService;

	private final ObjectMapper objectMapper;
	/**
	 * Constructor-based dependency injection.
	 *
	 * @param loginService service to handle login logic
	 */
	public LoginController(LoginService loginService, 
			UserService userService,
			ObjectMapper objectMapper) {
		this.loginService = loginService;
		this.objectMapper = objectMapper;
		this.userService = userService;
	}
    
	@Operation(summary = "Login and retrieve access token from Keycloak using username and password.",
			   description = "Authenticates the user with Keycloak and returns an access token based on provided source and destination parameters. Should use HTTP POST for security purposes instead of GET. Demo purposes only")
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username,
	                                                 @RequestParam("password") String password) {
		return loginService.login(username, password);
	}

    @Operation(
            summary = "Controller to test isAuthenticated tag",
            description = "Token must be provided to access. For testing and demo purposes only"
        )
        @PreAuthorize("isAuthenticated()")
        @GetMapping("/test")
        public String test() throws JsonProcessingException {
    		Object token = null;
            String tokenJson = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(token);

            logger.info("Token --> {}", tokenJson);

            return """
                {
                  "message": "Token is Valid"
                }
                """;
        }
    
    @PostMapping("/login")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> login(@RequestBody UserDTO user, Locale locale) {
    	UserDTO userDTO = userService.updateUser(user);
    	return ResponseEntityUtil.getResponseEntity(userDTO, HttpStatus.OK, null);
    }
}
