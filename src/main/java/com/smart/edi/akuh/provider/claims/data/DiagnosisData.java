/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh.provider.claims.data;

import lombok.Data;

/**
 * @author Kennedy.ikatanyi
 */
@Data
public class DiagnosisData {
    
    private String codingStandard;
    private String code;
    private String isPrimary;
    private String name;
}
