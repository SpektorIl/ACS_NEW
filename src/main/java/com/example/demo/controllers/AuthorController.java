package com.example.demo.controllers;

import com.example.demo.models.Author;
import com.example.demo.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @PostMapping
    public String saveAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }
}
