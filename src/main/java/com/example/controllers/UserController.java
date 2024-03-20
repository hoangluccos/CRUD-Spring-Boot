package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.User;
import com.example.models.UserDAO;
import com.example.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	User _userBean;

	@GetMapping("/register")
	public String addOrEdit(ModelMap model) {
		User u = new User();
//		u.setUsername("");
		model.addAttribute("USER", u);
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
//		UserDAO dao = new UserDAO();
//		dao.save(user);
//		System.out.println("Thanh cong");
		userService.save(user);
		return "register-user";
	}

	@RequestMapping("/list") // nghia la nhap duong dan hoac tuong tac thi vao van dc
	public String list(ModelMap model, HttpSession session) {
//		UserDAO dao = new UserDAO();
//		model.addAttribute("USERS", dao.getAll());
		if(session.getAttribute("username") != null)
		{
			model.addAttribute("USERS", userService.findAll());
			return "view-user";
		}
		return "login";
		
	}

	@RequestMapping("/edit/{username}")
	public String edit(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
//		User u = dao.findByUsername(username);
//		model.addAttribute("USER", u);
		Optional<User> u = userService.findById(username);
		if (u.isPresent()) {
			model.addAttribute("USER", u);
		} else {
			model.addAttribute("USER", new User());
		}
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}

	@RequestMapping("/delete/{username}")
	public String delete(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
//		User u = dao.findByUsername(username);
//		dao.delete(u);
//		model.addAttribute("ACTION",  "/saveOrUpdate");
		Optional<User> u = userService.findById(username);
		if (u.isPresent()) {
			userService.deleteById(username);
		}
		model.addAttribute("USERS", userService.findAll());
		return "view-user";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
//try with @pathvariable
	@PostMapping("/checklogin")
	public String checkLogin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password , HttpSession session)  {
		if (userService.checkLogin(username, password)) {
//			System.out.println("login thanh cong");
			session.setAttribute("username", username);
			model.addAttribute("USERS", userService.findAll());
			return "view-user";
		}
		else {
			System.out.println("login that bai");
			model.addAttribute("ERROR", "Username or Password not exist");
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "login";
	}
}
