package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
//	@RequestMapping("/challenge")
//	public String challenge(@RequestParam(value = "name") String name, Model model) {
//		model.addAttribute("name", name);
//		return "challenge";
//	}
	
	@RequestMapping(value = {"/challenge","/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());			
		} else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(
			@RequestParam(value = "a", required = false, defaultValue = "0") String a,
			@RequestParam(value = "b", required = false, defaultValue = "0") String b,
			Model model) {
		String resp = "";
		if (a.equalsIgnoreCase("0") || a.equalsIgnoreCase("1")) {
			resp += "hm";
		} else {
			resp += "h";
			for (int i = 0; i < Integer.parseInt(a); i++) {
				resp += "m";
			}
		}
		if (Integer.parseInt(b) > 1) {
			String newResp = "";
			for (int i = 0; i < Integer.parseInt(b); i++) {
				newResp += resp;
				newResp += " ";
			}
			resp = newResp;
		}
		model.addAttribute("resp", resp);
		model.addAttribute("aValue", a);
		model.addAttribute("bValue", b);
		return "viralGenerator";
	}
}
