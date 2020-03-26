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
public class BeanSessionKey 
{
    private SecretKey clédeSession;

    public void setClédeSession(SecretKey clédeSession) {
        this.clédeSession = clédeSession;
    }

    public SecretKey getClédeSession() {
        return clédeSession;
    }
}
