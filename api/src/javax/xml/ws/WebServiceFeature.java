/*
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;


/**
 * A WebServiceFeature is used to represent a feature that can be 
 * enabled or disabled for a web service.  
 * <p>
 * The JAX-WS specification will define some standard features and
 * JAX-WS implementors are free to define additional features if
 * necessary.  Vendor specific features may not be portable so 
 * caution should be used when using them.
 *
 * @see javax.xml.ws.soap.AddressingFeature
 * @see javax.xml.ws.soap.MTOMFeature
 * 
 * @since 2.1
 */
public abstract class WebServiceFeature {
   /**
    * Each Feature definition should define a public static String ID
    * that can be used in the Feature annotation to refer to the feature.
    */
   // public static String ID = "some feature Identifier";
    
   /**
    * Get the ID for this WebServiceFeature.
    * 
    * @return the ID for this feature.
    */
   public abstract String getID(); 
    
   /**
    * Specifies if the feature is enabled or disabled
    */    
   protected boolean enabled = false;
   

   /**
    * Returns <code>true</code> if this feature is enabled.
    *
    * @return <code>true</code> if and only if the feature is enabled .
    */
   public boolean isEnabled() {
       return enabled;
   }
   
   /**
    * Enables or disables the feature.  
    *
    * @param enabled specifies if the feature is enabled
    * or not.
    */
   public void setEnabled(boolean enabled) {
       this.enabled = enabled;
   }
}
