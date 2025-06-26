package com.hospital.management.system.HMSRestfulAPI.service;

import com.hospital.management.system.HMSRestfulAPI.model.Patient;
import java.util.List;
import java.util.Optional;

// Patient Management Logic eka define karana interface eka
public interface PatientService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientByCustomId(String customId);
    Patient createPatient(Patient patient);
    Patient updatePatient(String customId, Patient patient);
    // Void deletePatient(String customId); // Delete functionality add karanawanam
}