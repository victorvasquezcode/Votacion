package com.votacion.votacionsegura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.votacion.votacionsegura.service.ContractService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/createElection")
    public ResponseEntity<String> createElection(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            String startDate = request.get("startDate");
            String endDate = request.get("endDate");
            contractService.createElection(name, startDate, endDate);
            return ResponseEntity.ok("Election created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating election: " + e.getMessage());
        }
    }

    @PostMapping("/addCandidate")
    public ResponseEntity<String> addCandidate(@RequestBody Map<String, Object> request) {
        try {
            int electionId = (int) request.get("electionId");
            String name = (String) request.get("name");
            contractService.addCandidate(electionId, name);
            return ResponseEntity.ok("Candidate added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding candidate: " + e.getMessage());
        }
    }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody Map<String, Object> request) {
        try {
            int electionId = (int) request.get("electionId");
            int candidateId = (int) request.get("candidateId");
            contractService.vote(electionId, candidateId);
            return ResponseEntity.ok("Vote cast successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error casting vote: " + e.getMessage());
        }
    }

    @GetMapping("/getCandidate")
    public ResponseEntity<List<Object>> getCandidate(@RequestParam int electionId, @RequestParam int candidateId) {
        try {
            List<Object> candidate = contractService.getCandidate(electionId, candidateId);
            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
