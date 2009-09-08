/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.ws;

import java.io.Serializable;

/**
 * Holds a value of type <code>T</code>.
 *
 * @since JAX-WS 2.0
 */
public final class Holder<T> implements Serializable {

    private static final long serialVersionUID = 2623699057546497185L;

    /**
     * The value contained in the holder.
     */
    public T value;

    /**
     * Creates a new holder with a <code>null</code> value.
     */
    public Holder() {
    }

    /**
     * Create a new holder with the specified value.
     *
     * @param value The value to be stored in the holder.
     */
    public Holder(T value) {
        this.value = value;
    }
}
