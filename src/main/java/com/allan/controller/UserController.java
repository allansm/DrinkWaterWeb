package com.allan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.allan.entity.Cup;
import com.allan.entity.User;
import com.allan.persistence.CupDao;
import com.allan.persistence.UserDao;

@Controller
public class UserController {
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String login(HttpSession session,Model model) {
		if(session.getAttribute("id") != null) {
			try {
				List<Cup> lista = new CupDao().select((Integer)session.getAttribute("id"));
				List<Cup> lista2 = new ArrayList();
				List<String> lista3 = new ArrayList();
				Cup cup = null;
				int count = 0,count2 = 0;
				String previousDate = "";
				int id = (Integer) session.getAttribute("id");
				Cup last = new Cup();
				String history = "";
				for(Cup c:lista) {
					count2++;
					if(!c.getCupDate().equals(previousDate)) { 
						if(!previousDate.equals("")) {
							cup = new Cup();
							cup.setTimes(count);
							cup.setCupDate(previousDate);
							count = 0;
							lista2.add(cup);
							lista3.add(history);
							history = "";
						}
					}
					previousDate = c.getCupDate();
					count+=c.getTimes();
					last.setTimes(count);
					last.setCupDate(previousDate);
					history += "Quantidade:"+c.getTimes()+" Horario:"+c.getCupTime()+"<br/>";
				}
				lista2.add(last);
				lista3.add(history);
				model.addAttribute("lista2",lista2);
				model.addAttribute("login",session.getAttribute("login"));
				model.addAttribute("lista3",lista3);
				model.addAttribute("title","Historico Diario");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "home";
		}
		return "login";
	}
	
	@RequestMapping(value = "/cadastro" , method = RequestMethod.GET)
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public String login(HttpSession session,@RequestParam String login,@RequestParam String password,Model model) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		try {
			new UserDao().autenticate(session, user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/cadastrar" , method = RequestMethod.POST)
	public String cadastro(@RequestParam String login , @RequestParam String password ,@RequestParam String cpassword,Model model) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setCpass(cpassword);
		user.verifyFields();
		if(user.isVerified()) {
			try {
				new UserDao().add(user);
				model.addAttribute("msg","cadastro efetuado com sucesso");
			}catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("msg","erro ao cadastrar");
			}
		}
		return "login";
	}
}
