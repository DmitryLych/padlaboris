package com.instinctools.padlaboris.domain.service;

import com.instinctools.padlaboris.domain.model.Patient;
import com.instinctools.padlaboris.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class that implements the Patient's work and databases.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultPatientService implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient create(final Patient patient) {

        log.info("Patient added to database.");

        return patientRepository.save(patient);
    }

    @Override
    public Patient fetch(final Integer id) {

        log.info("Patient displayed.");

        return patientRepository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Patient deleted from database.");

        patientRepository.delete(id);
    }

    @Override
    public List<Patient> findByLastName(final String lastName) {

        log.info("Patients were find by last name and displayed.");

        return patientRepository.findByLastName(lastName);
    }

    @Override
    public List<Patient> findByGender(final String gender) {

        log.info("Patients were find by gender and displayed");

        return patientRepository.findByGender(gender);
    }

    @Override
    public List<Patient> listPatients() {

        log.info("All patients were displayed");

        return patientRepository.findAll();
    }

    @Override
    @SuppressWarnings({
            "PMD.NPathComplexity",
            "PMD.CyclomaticComplexity",
            "PMD.ModifiedCyclomaticComplexity",
            "PMD.StdCyclomaticComplexity"
    })
    public Patient update(final Patient patient) {

        log.info("Patient was updated.");

        final Patient saved = patientRepository.findOne(patient.getId());

        patient.setDetails(patient.getDetails() == null ? saved.getDetails() : patient.getDetails());
        patient.setGender(patient.getGender() == null ? saved.getGender() : patient.getGender());
        patient.setFirstName(patient.getFirstName() == null ? saved.getFirstName() : patient.getFirstName());
        patient.setLastName(patient.getLastName() == null ? saved.getLastName() : patient.getLastName());
        patient.setDeathDate(patient.getDeathDate() == null ? saved.getDeathDate() : patient.getDeathDate());
        patient.setBirthDate(patient.getBirthDate() == null ? saved.getBirthDate() : patient.getBirthDate());
        patient.setMobileNumber(patient.getMobileNumber() == null ? saved.getMobileNumber() : patient.getMobileNumber());
        patient.setHomeNumber(patient.getHomeNumber() == null ? saved.getHomeNumber() : patient.getHomeNumber());
        patient.setDiseases(patient.getDiseases() == null ? saved.getDiseases() : patient.getDiseases());
        patient.setProcedures(patient.getProcedures() == null ? saved.getProcedures() : patient.getProcedures());

        return patientRepository.save(patient);
    }
}
