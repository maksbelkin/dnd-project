package com.example.WebService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String dndPage() {
        return "dnd/DND";
    }

    @GetMapping("/dnd-information")
    public String information() {
        return "dndInformationForge/dndInformationForge";
    }

    @GetMapping("/dnd-instruction")
    public String instruction() {
        return "dndInstructionForge/dndInstructionForge";
    }

    @GetMapping("/dnd-help")
    public String help() {
        return "dndHelpForge/dndHelpForge";
    }

}
