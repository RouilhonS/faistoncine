package org.sid.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.sid.InterfaceDao.UtilisateurRepository;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.net.SyslogOutputStream;
import rita.RiMarkov;

@CrossOrigin("*")
@RestController
public class UtilisateursServices {
    @Autowired
    private UtilisateurRepository  utilisateurRepository;

    @RequestMapping(value="/utilisateurs", method= RequestMethod.POST)
    public Utilisateur SaveUtilisateur(@Valid @RequestBody Utilisateur i ){
        return ideeRepository.save(i);
    }

    @RequestMapping(value="/utilisateurs", method=RequestMethod.GET)
    public List<Utilisateur> getUtilisateurs(){
        return ideeRepository.findAll();
    }

    @RequestMapping(value="/utilisateurs/{id}", method=RequestMethod.GET)
    public Utilisateur getUtilisateur(@PathVariable Long id){
        return ideeRepository.findById(id).orElse(null);
    }

    @RequestMapping(value="/utilisateurs/{id}", method=RequestMethod.PUT)
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur i){
        return ideeRepository.save(i);
    }
}