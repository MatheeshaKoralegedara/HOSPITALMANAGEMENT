package com.hospital.management.system.HMSRestfulAPI.service.impl;

import com.hospital.management.system.HMSRestfulAPI.model.Patient;
import com.hospital.management.system.HMSRestfulAPI.repository.PatientRepository;
import com.hospital.management.system.HMSRestfulAPI.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service // Me class eka Spring Service Component ekak kiyala specify karanawa
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Simple counter for generating custom patient IDs (for demonstration)
    // Real application ekaka meka database sequence nattan UUID wage magin generate karanna one.
    // Ensure this counter is thread-safe. For a truly unique ID, consider a database sequence or UUID.
    private final AtomicLong patientIdCounter = new AtomicLong(1000);

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientByCustomId(String customId) {
        return patientRepository.findByCustomId(customId);
    }

    @Override
    public Patient createPatient(Patient patient) {
        // Basic validation for required fields
        if (patient.getName() == null || patient.getName().isEmpty() ||
                patient.getPhoneNo() == null || patient.getPhoneNo().isEmpty() ||
                patient.getAddress() == null || patient.getAddress().isEmpty() ||
                patient.getAge() <= 0 ||
                patient.getSex() == null || patient.getSex().isEmpty()) {
            throw new IllegalArgumentException("All fields (name, phoneNo, address, age, sex) are required.");
        }

        // Check if a patient with the same customId already exists
        if (patient.getCustomId() != null && patientRepository.findByCustomId(patient.getCustomId()).isPresent()) {
            throw new IllegalArgumentException("Patient with custom ID " + patient.getCustomId() + " already exists.");
        }


        // Generate a simple unique custom ID for the patient if not provided
        if (patient.getCustomId() == null || patient.getCustomId().isEmpty()) {
            String newCustomId = "PS/" + Year.now().getValue() + "/" + patientIdCounter.getAndIncrement();
            patient.setCustomId(newCustomId);
        } else {
            // If customId is provided, ensure it's not a duplicate before saving
            if (patientRepository.findByCustomId(patient.getCustomId()).isPresent()) {
                throw new IllegalArgumentException("Patient with custom ID " + patient.getCustomId() + " already exists.");
            }
        }


        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(String customId, Patient patientDetails) {
        Optional<Patient> patientData = patientRepository.findByCustomId(customId);

        if (patientData.isPresent()) {
            Patient existingPatient = patientData.get();
            // Update only the fields that are provided in the request body
            if (patientDetails.getName() != null) {
                existingPatient.setName(patientDetails.getName());
            }
            if (patientDetails.getPhoneNo() != null) {
                existingPatient.setPhoneNo(patientDetails.getPhoneNo());
            }
            if (patientDetails.getAddress() != null) {
                existingPatient.setAddress(patientDetails.getAddress());
            }
            if (patientDetails.getAge() > 0) { // Only update if age is valid
                existingPatient.setAge(patientDetails.getAge());
            }
            if (patientDetails.getSex() != null) {
                existingPatient.setSex(patientDetails.getSex());
            }
            // customId update karanna nodanna (usually IDs are immutable once set)
            // if (patientDetails.getCustomId() != null && !patientDetails.getCustomId().equals(existingPatient.getCustomId())) {
            //     // You might want to add logic here to handle customId change or prevent it
            //     // For now, we assume customId is immutable after creation
            // }

            return patientRepository.save(existingPatient);
        } else {
            // Patient not found error handling
            throw new RuntimeException("Patient with custom ID " + customId + " not found.");
        }
    }
}
