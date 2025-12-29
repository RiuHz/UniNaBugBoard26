package com.progetto;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.dto.Issue;

/* 
L' annotazione RestController è utilizzata per RESTful APIs che sono utulizzate per lavorare con dati come JSON 

@GetMapping un'annotazione composta che funge da scorciatoia per @RequestMapping(method = RequestMethod.GET).

@GetMapping è l'annotazione più recente. 

*/

@RestController
public class IssueController {
    @GetMapping("/test-json")
    public Issue getIssue(){
        Issue i = new Issue();
        i.setId(1);
        i.setUserId("23rf-t34trf-23r2gfef");
        i.setTitolo("Bug sul progetto numero 30");
        i.setDescrizione("Bug grafico sul front-end da risolvere entro martedì");
        i.setTipo(com.progetto.enums.Tipo.Bug);
        i.setPriorita(com.progetto.enums.Priorita.Bassa);
        i.setStato(com.progetto.enums.Stato.ToDo);
        i.setAllegato("https://bucket-allegati.amazonaws.com/8.jpg");
        return i;
    }

}
