package com.hospital.management.system.HMSRestfulAPI.controller;

import com.hospital.management.system.HMSRestfulAPI.model.Patient;
import com.hospital.management.system.HMSRestfulAPI.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Endpoint 1: GET /api/patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving patients: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint 2: GET /api/patients/{id}
    @GetMapping("/{customId}")
    public ResponseEntity<Patient> getPatientByCustomId(@PathVariable("customId") String customId) {
        Optional<Patient> patientData = patientService.getPatientByCustomId(customId);

        if (patientData.isPresent()) {
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint 3: POST /api/patients
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            Patient _patient = patientService.createPatient(patient);
            return new ResponseEntity<>(_patient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error creating patient: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Error creating patient: " + e.getMessage());
            // Check for specific error message for duplicate entry for MySQL
            if (e.getMessage() != null && e.getMessage().contains("could not execute statement")) { // Generic JPA error for unique constraint violation
                // More specific check for MySQL unique constraint violation error messages if needed
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint 4: PUT /api/patients/{id}
    @PutMapping("/{customId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("customId") String customId, @RequestBody Patient patient) {
        try {
            Patient updatedPatient = patientService.updatePatient(customId, patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (RuntimeException e) { // Catches "Patient not found" from service layer
            System.err.println("Error updating patient: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error updating patient: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
