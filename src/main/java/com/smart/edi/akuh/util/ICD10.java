/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.util;
  
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author Kelsas
 */ 
public class ICD10 {

    private String code = "Z00.0";
    private String name = "Examination and encounter for administrative purposes";
    @JsonIgnore
    private String synonymy;

    public ICD10(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ICD10(String code, String name, String synonymy) {
        this.code = code;
        this.name = name;
        this.synonymy = synonymy;
    }

    public ICD10() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynonymy() {
        return synonymy;
    }

    public void setSynonymy(String synonymy) {
        this.synonymy = synonymy;
    }

    @Override
    public String toString() {
        return code + " " + name;
    }

}
