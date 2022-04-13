package com.ak.greatideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ak.greatideas.models.Idea;
import com.ak.greatideas.models.User;
import com.ak.greatideas.services.IdeaService;
import com.ak.greatideas.services.UserService;
import com.ak.greatideas.validators.UserValidator;

@Controller
public class MainController {

	private final UserService userService;
	private final UserValidator userValidator;
	private final IdeaService ideaService;
	public MainController(
			UserService userService, 
			UserValidator userValidator,
			IdeaService ideaService
			) {
		this.userService=userService;
		this.userValidator = userValidator;
		this.ideaService = ideaService;
	}

	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}

	@RequestMapping(value="/registration", method= RequestMethod.POST)
	public String register(
			@Valid @ModelAttribute("user") User user, 
			BindingResult result, 
			HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User u =userService.registerUser(user);
			session.setAttribute("userId", u.getId());
			return "redirect:/dashboard";
		}
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(
			@RequestParam("email") String email, 
			@RequestParam("password") String password, 
			HttpSession session,
			RedirectAttributes redirect){
		if(userService.authenticateUser(email, password)) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/dashboard";
		}else {
			redirect.addFlashAttribute("error", "Please enter valid login credentials");
			return "redirect:/";
		}
	}

	@RequestMapping("/dashboard")
	public String dashboard(
			Model model, 
			@ModelAttribute("idea") Idea idea,
			HttpSession session,
			RedirectAttributes redirect
			) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);

		List<Idea> ideas= ideaService.allIdeas();
		model.addAttribute("ideas", ideas);

		if(userId == null) {
			redirect.addFlashAttribute("login-msg", "Please register or Login.");
			return "redirect:/";
		}
		return "homepage.jsp";
	}

	//====================================

	@RequestMapping("/ideas/new")
	public String idea(
			Model model,
			HttpSession session,
			RedirectAttributes redirect
			) {
		Long userId = (Long)session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);

		List<User> users = userService.allUsers();
		model.addAttribute("users", users);

		if(userId == null) {
			redirect.addFlashAttribute("login-msg", "Please register or Login.");
			return "redirect:/";
		}

		model.addAttribute("idea", new Idea());

		return "newIdea.jsp";
	}

	@RequestMapping(value="/ideas/new", method=RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("idea") Idea idea, 
			BindingResult result,
			HttpSession session,
			Model model
			) {
		Long userId = (Long)session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		if (!result.hasErrors()) {
			ideaService.createIdea(idea);
			return "redirect:/dashboard";
		}else {
			return "newIdea.jsp";
		}
	}

	@RequestMapping("/ideas/{id}/edit")
	public String update(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session,
			RedirectAttributes redirect
			) {
		Long userId = (Long)session.getAttribute("userId");


		if(userId == null) {
			redirect.addFlashAttribute("login-msg", "Please register or Login.");
			return "redirect:/";
		}

		Idea idea = ideaService.findIdea(id);
		model.addAttribute("idea", idea);

		return "updateIdea.jsp";
	}

	@RequestMapping(value="/ideas/{id}/edit", method=RequestMethod.PUT)
	public String edit(
			@Valid @ModelAttribute("idea") Idea idea, 
			BindingResult result
			) {
		if (!result.hasErrors()) {
			ideaService.updateIdea(idea);
			return "redirect:/dashboard";
		}else {
			return "updateIdea.jsp";
		}
	}

	@RequestMapping("/idea/{id}")
	public String info(
			@PathVariable("id") Long id, 
			Model model
			) {
		Idea idea = ideaService.findIdea(id);
		model.addAttribute("idea", idea);
		return "info.jsp";

	}

	@RequestMapping(value = "/ideas/{id}/delete", method= RequestMethod.DELETE)
	public String delete(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session,
			RedirectAttributes redirect
			) {
		
			ideaService.deleteIdea(id);
			return "redirect:/dashboard";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
