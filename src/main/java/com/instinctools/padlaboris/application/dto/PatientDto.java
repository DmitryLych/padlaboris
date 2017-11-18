package com.instinctools.padlaboris.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

/**
 * Intermediate class of Patient and controller work.
 */
@Data
@JsonTypeName(value = "patient")
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto implements Serializable {

    private static final long serialVersionUID = -2090141102525873071L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private Long birthDate;

    @JsonProperty
    private String gender;

    @JsonProperty
    private String homeNumber;

    @JsonProperty
    private String mobileNumber;

    @JsonProperty
    private Long lastChangeDate;

    @JsonProperty
    private Long deathDate;

    @JsonProperty
    private DetailDto details;
}
