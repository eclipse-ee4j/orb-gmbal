/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.impl ;

import javax.management.openmbean.OpenType ;
import org.glassfish.gmbal.typelib.EvaluatedType;

/** A ManagedEntity is one of the pre-defined Open MBean types: SimpleType, ObjectName, 
 * TabularData, or CompositeData.
 */
public interface TypeConverter {
    /** Java generic type of attribute in problem-domain Object.
     * @return The Java type that this TypeConverter handles.
     */
    EvaluatedType getDataType() ;

    /** Open MBeans Open Type for management domain object.
     * @return The OpenType that this TypeConverter handles.
     */
    OpenType getManagedType() ;

    /** Convert from a problem-domain Object obj to a managed entity.
     * @param obj The Java object to be converted to an open type.
     * @return The resulting open type.
     */
    Object toManagedEntity( Object obj ) ;

    /** Convert from a ManagedEntity to a problem-domain Object.
     * @param entity The managed entity to be converted to a java type.
     * @return The resulting java type.
     */
    Object fromManagedEntity( Object entity ) ;

    /** Returns true if this TypeConverter is an identity transformation.
     * @return True if this TypeConverter is an identity transformation.
     */
    boolean isIdentity() ;
}

