package com.example.demo;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    public static long idCounter = 0;
    ArrayList<Guest> guestList = new ArrayList<>();

    @RequestMapping("/")
    public String loadHomePage(){
        return "index";
    }

    @GetMapping("/add")
    public String loadAddPage(Model model){
        Guest guest = new Guest();
        idSetter(guest);
        model.addAttribute("guest", guest);
        return "add";
    }

    @PostMapping("/displayentry")
    public String loadGuestPage(@Valid Guest guest, BindingResult result){
        if (result.hasErrors()){
            return "add";
        }
        else {
            guestList.add(guest);
            return "displayentry";
        }
    }

    @RequestMapping("/displaylist")
    public String loadGuestList(Model model){
        model.addAttribute("guestList", guestList);
        return "displaylist";
    }

    static void idSetter(Guest guest){
        idCounter += 1;
        guest.setId(idCounter);
    }
}
