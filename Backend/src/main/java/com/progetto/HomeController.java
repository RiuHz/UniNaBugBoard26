package com.progetto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 
L' annotazione controller serve per indicare che questa classe viene usate come web controller per ricevere richieste web 
Controller utilizzato principalmente per restituire pagine HTML e simili
*/

@Controller
public class HomeController {
    @RequestMapping("/") // mappa la richiesta alla radice del sito (es: /issues/allegato, e cosi via)
    public String index(){
        return "index.html";
    }
}
