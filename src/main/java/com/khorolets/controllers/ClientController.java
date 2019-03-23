package com.khorolets.controllers;

import com.khorolets.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public String showClients(ModelMap modelMap) {
        modelMap.put("message", clientService.getAllClients());
        return "clients";
    }

    @PostMapping()
    public void showClients(@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String age,
                            @RequestParam String phone,
                            @RequestParam String email) {
        clientService.createClient(name, surname, Integer.parseInt(age), phone, email);
    }

    @GetMapping("/delete")
    public String deleteClients(@RequestParam String id, ModelMap modelMap) {
        clientService.deleteClient(Long.parseLong(id));
        modelMap.put("message", "Deleted Client with id = " + id);

        return "clients";
    }

    @PutMapping("/edit")
    public String editClients(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String age,
                              @RequestParam String email,
                              ModelMap modelMap) {
        clientService.editClient(Long.parseLong(id), name, Integer.parseInt(age), email);
        modelMap.put("message", clientService.getClientById(Long.parseLong(id)));

        return "clients";
    }
}
