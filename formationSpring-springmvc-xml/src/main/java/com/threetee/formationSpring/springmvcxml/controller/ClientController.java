package com.threetee.formationSpring.springmvcxml.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.threetee.formationSpring.springmvcxml.dao.ClientDao;
import com.threetee.formationSpring.springmvcxml.model.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private List<Client> clients = new ArrayList<>();

	@Autowired
	private ClientDao clientDao;

	@RequestMapping(method = RequestMethod.GET)
	private String showClients(Model model) {
		clients = clientDao.findAll();
		clients.forEach(System.out::println);
		model.addAttribute("clients", clients);
		return "index";
	}

}
