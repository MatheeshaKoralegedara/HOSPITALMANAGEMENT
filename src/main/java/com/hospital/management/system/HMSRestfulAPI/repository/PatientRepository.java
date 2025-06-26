package com.hospital.management.system.HMSRestfulAPI.repository;

import com.hospital.management.system.HMSRestfulAPI.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository import karanawa
import java.util.Optional;

// JpaRepository interface eka extends karala Patient object ha Long type ID eka denna.
// Meeken oyata CRUD operations automatically labenawa JPA walata anuwa.
public interface PatientRepository extends JpaRepository<Patient, Long> { // ID type eka Long wenas karanawa
    // Custom method ekak. Spring Data JPA eken method name eka balala query eka generate karanawa.
    Optional<Patient> findByCustomId(String customId);
}