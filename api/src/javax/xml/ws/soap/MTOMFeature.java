/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws.soap;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.spi.Provider;


/**
 * This feature represents the use of MTOM with a 
 * web service.
 *
 * <p>
 * The following describes the affects of this feature with respect
 * to being enabled or disabled:
 * <ul>
 *  <li> ENABLED: In this Mode, MTOM will be enabled.
 *  <li> DISABLED: In this Mode, MTON will be disabled
 * </ul>
 * <p>
 * The @link{#setThreshold) method can be used to set the threshold 
 * value used to determine when binary data should be XOP encoded.
 *
 * @since JAX-WS 2.1
 */       
public class MTOMFeature extends WebServiceFeature {
    /** 
     * Constant value identifying the MTOMFeature
     */
    public static final String ID = "http://www.w3.org/2004/08/soap/features/http-optimization";
    
    /**
     * Constant ID for the THRESHOLD Feature parameter
     */
    public static final String THRESHOLD = "MTOM_THRESHOLD";
    
    
    /**
     * Property for MTOM threshold value. When MTOM is enabled, binary data above this 
     * size in bytes will be XOP encoded or sent as attachment. The value of this property 
     * MUST always be >= 0.      
     */
    protected int threshold = 0;
    

    /**
     * Creates an MTOMFeature
     * 
     * @param enabled specifies if this feature should be enabled or not
     */
    public MTOMFeature(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Creates an MTOMFeature
     * 
     * @param enabled specifies if this feature should be enabled or not
     * @param threshold the size in bytes that binary data must be before
     * being XOP encoded.
     *
     * @throws WebServiceException if threshold is < 0
     */
    public MTOMFeature(boolean enabled, int threshold) {
        this.enabled = enabled;
        this.threshold = threshold;
    }    
    
    /**
     * {@inheritDoc}
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Gets the threshold value used to determine when binary data 
     * should be XOP encoded.
     *
     * @return the current threshold size in bytes
     */
    public int getThreshold() {
        return threshold;
    }
    
    /**
     * Set the threshold value used to determine when binary data should be
     * XOP encoded. 
     *
     * @param threshold the size in bytes that binary data must be before
     * being XOP encoded.
     *
     * @throws WebServiceException if threshold is < 0
     */ 
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
