package com.hospital.management.system.HMSRestfulAPI.model;

import jakarta.persistence.*; // JPA annotations import karanawa
import lombok.Data; // Lombok's @Data annotation ekata
import lombok.NoArgsConstructor; // Lombok's @NoArgsConstructor annotation ekata
import lombok.AllArgsConstructor; // Lombok's @AllArgsConstructor annotation ekata

// JPA Entity ekak widihata specify karanawa ha table name eka "patients" kiyala set karanawa
@Entity
@Table(name = "patients")
@Data // Automatically generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a constructor with no arguments
@AllArgsConstructor // Generates a constructor with all arguments
public class Patient {

    @Id // Primary key eka specify karanawa
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database eken ID eka auto-increment wenna salassanawa
    private Long id; // JPA waladi Long type ID ekak use karana eka hodayi

    @Column(unique = true, nullable = false) // customId field eka unique ha not nullable karanawa
    private String customId; // Oyage 'PS/2021/XXX' format id ekata.

    @Column(nullable = false) // Not nullable karanawa
    private String name;

    @Column(nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String sex;
}
