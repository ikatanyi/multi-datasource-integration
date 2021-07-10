/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smart.edi.akuh;

import com.smart.edi.akuh.*;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author kelvin
 */
@Profile("qa")
public interface EDIConstantQA {
 
//# Secondary DataSource configuration
//datasource.smart.jdbc-url=jdbc:oracle:thin:@//192.180.3.14:1528/abacus
//datasource.smart.username=smartinternal
//datasource.smart.password=SMInt#OCt2016
//datasource.smart.driver-class-name=oracle.jdbc.OracleDriver
//datasource.smart.connection-test-query=SELECT 1 FROM DUAL

    public static String CLIENT_DB_HOST = "192.180.3.14";
    public static String CLIENT_DB_PORT = "1515";
    public static String CLIENT_DB_SERVICE_NAME = "carepro";
    public static String CLIENT_DB_USERNAME = "smart";
    public static String CLIENT_DB_PASSWORD = "smart";

//    Data Source=(DESCRIPTION =(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.180.3.14)(PORT=5090)))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=abacus_ke)));User Id =smartinternal; Password =SMInt#OCt2016;";
    public static String SMART_HOST = "192.180.4.5";
    public static String SMART_PORT = "1521";
    public static String SMART_SERVICE_NAME = "abacus";
    public static String SMART_USERNAME = "smartinternal";
    public static String SMART_PASSWORD = "SMInt#OCt2016";

    public static String CURRENCY = "KES";

//    public static String KEY = "uXPNfq8ULRtmcEi+4C0eUebRiA0aujGTRksLYv+Nta9U/BoE8fQ5o7renOlL4M7q";
//    public static String TOKEN = "O+xbmTePC4UdLU6JcA9X1jyZSCN9bVV3ckx5QW0oLizYJuHHIc/XVo+F/2yI/d9R";
//    public static String USERID = "20100";
//    public static String ID1 = "1";
//    public static String ID2 = "61002156";
//    public static String ID3 = "1";
////    public static String URL = "https://data.smartapplicationsgroup.com:8087/sandbox/edi/claim";
//    public static String URL = "https://192.180.3.14:5091/abacus/edi/claim";
//    public static String URL = "http://192.180.4.62:8090/abacus/edi/claim";
    
    public static String URL = "http://192.180.3.14:5091/abacus/edi/claim";
    public static String DIAGNOSIS_URL="http://192.180.3.14:5091/abacus/edi/claim/diagnosis";
    
//    public static String URL = "http://192.180.4.62:8090/abacus/edi/claim";
//    public static String DIAGNOSIS_URL="http://192.180.4.62:8090/abacus/edi/claim/diagnosis";
    
//     public static String URL = "http://192.168.1.90:8080/abacus/edi/claim";
//    public static String DIAGNOSIS_URL="http://192.168.1.90:8080/abacus/edi/claim/diagnosis";
   
    
    public static String USERID =  "2152";
    public static String KEY = "azvphYfyqq60Uh+5tvgygJCPs2f8URc5hacZuMy6TDytOaMnYFIbcBsrF0BJjrSP";
    public static String TOKEN = "Af4GQIOihIgD8xFXHveJHKugf1aOVxw/8+IXGLdwIS2YV35Jpb+/+v/c7hDcoqTY";
    public static String ID1 = "1";
    public static String ID2 = "14350";
    public static String ID3 = "1";

}
