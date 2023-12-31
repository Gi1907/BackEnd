package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Hospede;
import com.projetojpa.services.HospedeService;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {
    
    private final HospedeService hospedeService;
    
    @Autowired
    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> getHospedeById(@PathVariable Long codigo) {
    	Hospede hospede = hospedeService.getHospedeById(codigo);
        if (hospede != null) {
            return ResponseEntity.ok(hospede);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Hospede>> getAllHospede() {
        List<Hospede> hospedes = hospedeService.getAllHospede();
        return ResponseEntity.ok(hospedes);
    }

    @PostMapping("/")
    public ResponseEntity<Hospede> criarHospede(@RequestBody Hospede hospede) {
    	Hospede criarHospede = hospedeService.salvarHospede(hospede);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarHospede);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Hospede> updateHospede(@PathVariable Long codigo, @RequestBody Hospede hospede) {
    	Hospede updatedHospede = hospedeService.updateHospede(codigo, hospede);
        if (updatedHospede != null) {
            return ResponseEntity.ok(updatedHospede);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospede(@PathVariable Long codigo) {
        boolean deleted = hospedeService.deleteHospede(codigo);
        if (deleted) {
        	 return ResponseEntity.ok().body("O funcionario foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
    
}