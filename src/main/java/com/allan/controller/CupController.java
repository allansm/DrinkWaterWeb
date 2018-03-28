package com.allan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.allan.entity.Cup;
import com.allan.persistence.CupDao;

@Controller
public class CupController {
	private String cupDate;
	private String cupTime;
	@RequestMapping(value = "/historico",method = RequestMethod.GET)
	public String historico(HttpSession session,Model model) {
		model.addAttribute("title","Registros");
		try {
			List<Cup> lista = new CupDao().select((Integer) session.getAttribute("id"));
			model.addAttribute("UserHistory",lista);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(HttpSession session,Model model) {
		updateTime();
		try {
			List<Cup> lista = new CupDao().select((Integer) session.getAttribute("id"));
			Cup cup = new Cup();
			cup.setTimes(0);
			cup.setCupDate(this.cupDate);
			for(Cup c:lista) {
				if(c.getCupDate().equals(this.cupDate)) {
					cup.setTimes(cup.getTimes()+c.getTimes());
				}
			}
			model.addAttribute("todayCup", cup);
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("showForm",true);
		model.addAttribute("title","Registro");
		return "home";
	}
	@RequestMapping(value = "/registrar" , method = RequestMethod.POST)
	public String registrar(@RequestParam int quantidade,HttpSession session) {
		if(quantidade > 0) {
			updateTime();
			Cup cup = new Cup();
			cup.setCupDate(cupDate);
			cup.setCupTime(cupTime);
			cup.setUser_id((Integer)session.getAttribute("id"));
			cup.setTimes(quantidade);
			try {
				new CupDao().add(cup);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/";
	}
	public void updateTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        this.cupDate = ""+dateFormat.format(date);
        this.cupTime = ""+dateFormat2.format(date);
    }
}
