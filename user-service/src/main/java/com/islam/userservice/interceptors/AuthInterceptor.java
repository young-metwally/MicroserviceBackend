package com.islam.userservice.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.exceptions.SuspendedUserException;
import com.islam.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;


@Component
public class AuthInterceptor implements HandlerInterceptor {


	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		UserDTO authUser = new UserDTO();
		authUser.setEnabled((short) 1);

		try {
			Optional<UserDTO> authUserOpt = Optional.ofNullable(userService.getCurrentUser());
			if (authUserOpt.isPresent()) {
				authUser = authUserOpt.get();
			}
		} catch (Exception exc) {

		}

		if (authUser.getEnabled() == 0) {
			Error error = new Error("Account suspended");
			throw new SuspendedUserException(error);
		}

		return true;
	}

}