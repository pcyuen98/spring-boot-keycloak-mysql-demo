package com.demo.keycloak.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.keycloak.model.bean.FeedbackDTO;
import com.demo.keycloak.model.bean.UserDTO;
import com.demo.keycloak.model.service.FeedbackService;
import com.demo.keycloak.model.service.UserService;
import com.demo.keycloak.utility_classes.ResponseEntityUtil;

@RestController
@RequestMapping("/demo/keycloak/v1")
@PreAuthorize("isAuthenticated()")
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	private final FeedbackService feedbackService;
	
	private final UserService userService;

	public FeedbackController(FeedbackService feedbackService
			,UserService userService) {
		this.feedbackService = feedbackService;
		this.userService = userService;
	}

	@PostMapping("/feedback")
	public ResponseEntity<Map<String, Object>> updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {

		UserDTO existingUser = userService.findByUsername(feedbackDTO.getUserDTO().getUsername());
		feedbackDTO.setUserDTO(existingUser);

		FeedbackDTO feedbackSaved = feedbackService.save(feedbackDTO);
		logger.info("user feedback saved");
		return ResponseEntityUtil.getResponseEntity(feedbackSaved, HttpStatus.OK, null);
	}

}
