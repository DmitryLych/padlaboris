package com.instinctools.padlaboris.application.controller;

import com.instinctools.padlaboris.application.dto.DiseaseDto;
import com.instinctools.padlaboris.domain.model.Disease;
import com.instinctools.padlaboris.domain.model.Patient;
import com.instinctools.padlaboris.domain.model.Recipe;
import com.instinctools.padlaboris.domain.service.DiseaseService;
import com.instinctools.padlaboris.domain.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for {@link com.instinctools.padlaboris.domain.model.Disease}.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiseaseController {

    private final PatientService patientService;

    private final DiseaseService diseaseService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Fetches diseases of a user.
     * @param patientId id of a user
     * @return a list of diseases
     */
    @GetMapping("patients/{id}/diseases")
    public ResponseEntity getPatientDiseases(@PathVariable("id") final Integer patientId) {
        final Patient patient = patientService.fetch(patientId);
        final List<DiseaseDto> diseaseDtos = new ArrayList<>();

        if (patient != null) {
            patient.getDiseases().forEach(disease ->
                    diseaseDtos.add(dozerBeanMapper.map(disease, DiseaseDto.class)));
        }

        return ResponseEntity.ok(diseaseDtos);
    }

    /**
     * Fetches specific disease.
     * @param diseaseId id of a disease
     * @return a disease
     */
    @GetMapping("diseases/{id}")
    public ResponseEntity getDisease(@PathVariable("id") final Integer diseaseId) {
        final Disease disease = diseaseService.findById(diseaseId);
        final DiseaseDto diseaseDto = dozerBeanMapper.map(disease, DiseaseDto.class);

        return ResponseEntity.ok(diseaseDto);
    }

    /**
     * Creates a disease for a patient.
     * @param diseaseDto a disease body
     * @param patientId id of a patient
     * @return saved disease
     */
    @PostMapping("patients/{id}/diseases")
    public ResponseEntity addDisease(@RequestBody final DiseaseDto diseaseDto,
                                     @PathVariable("id") final Integer patientId) {
        final Disease disease = dozerBeanMapper.map(diseaseDto, Disease.class);
        final Patient patient = patientService.fetch(patientId);

        disease.setPatient(patient);

        final Disease saved = diseaseService.save(disease);
        final DiseaseDto savedDto = dozerBeanMapper.map(saved, DiseaseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    /**
     * Updates a disease.
     * @param diseaseDto disease to update
     * @param patientId id of a patient
     * @return saved disease
     */
    @PutMapping("patients/{id}/diseases")
    public ResponseEntity updateDisease(@RequestBody final DiseaseDto diseaseDto,
                                        @PathVariable("id") final Integer patientId) {
        final Disease disease = dozerBeanMapper.map(diseaseDto, Disease.class);
        final List<Recipe> recipes = diseaseService.findById(diseaseDto.getId()).getRecipes();
        final Patient patient = patientService.fetch(patientId);

        disease.setPatient(patient);
        disease.setRecipes(recipes);

        final Disease saved = diseaseService.save(disease);
        final DiseaseDto savedDto = dozerBeanMapper.map(saved, DiseaseDto.class);

        return ResponseEntity.ok(savedDto);
    }

    /**
     * Deletes a disease.
     * @param patientId id of a patient
     * @param diseaseId id of a disease
     * @return 204
     */
    @DeleteMapping("patients/{patientId}/diseases/{diseaseId}")
    public ResponseEntity deleteDisease(@PathVariable("patientId") final Integer patientId,
                                        @PathVariable("diseaseId") final Integer diseaseId) {
        final Patient patient = patientService.fetch(patientId);
        final Disease disease = diseaseService.findById(diseaseId);

        patient.getDiseases().remove(disease);

        patientService.update(patient);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
