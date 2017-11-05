package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * class that implements the Patient's work and databases.
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultPatientService implements PatientService {

    private final PatientRepository patientRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public Patient update(final Patient patient) {

        log.info("Patient updated.");

        if (patient.isNew()) {
            return entityManager.merge(patient);
        }

        return patientRepository.save(patient);
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
    public Patient updateById(Integer id, String firstName) {

        return patientRepository.updateById(id,firstName);
    }
}
