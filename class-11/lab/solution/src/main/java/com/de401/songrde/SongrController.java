package com.de401.songrde;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SongrController {
    @GetMapping("/hello")
    public String hello() {
     return "hello";
 }

    @GetMapping("/capitalize/{text}")
    public String capitalize(Model model, @PathVariable String text) {
        model.addAttribute("capitalizedText", text.toUpperCase());
        return "capitalize";
    }

    @GetMapping("/")
    public String splash() {
        return "splash";
    }

    @GetMapping("/albums")
    public String albums(Model m) {
        Album[] albums = {
             new Album("ASTROWORLD", "Travis Scott", 17, 3522,"/images/ASTROWORLD.png"),
                new Album("The Incredible True Story", "Logic", 18,3600,"/images/swimming.png"),
                new Album("Swimming", "Mac Miller", 13,3519,"/images/The_Incredible_True_Story.png")

        };
        m.addAttribute("albums", albums);
        return "albums";
    }
}
