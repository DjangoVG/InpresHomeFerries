/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanSymetricKey;

import javax.crypto.SecretKey;

/**
 *
 * @author Regis
 */
public class BeanHMACKey 
{
    private SecretKey cléHMAC;

    public void setCléHMAC(SecretKey cléHMAC) {
        this.cléHMAC = cléHMAC;
    }

    public SecretKey getCléHMAC() {
        return cléHMAC;
    }
}
