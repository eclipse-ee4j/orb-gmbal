/*
 * Copyright (c) 2001, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.typelib;

import org.glassfish.pfl.basic.contain.ObjectSet;

/**
 *
 * @author ken
 */
public abstract class EvaluatedArrayTypeBase extends EvaluatedTypeBase 
    implements EvaluatedArrayType {
        
    void makeRepresentation( StringBuilder sb, ObjectSet set ) {
        ((EvaluatedTypeBase)componentType()).makeRepresentation( sb, set ) ;
        sb.append( "[]" ) ;
    }
   
    boolean myEquals( Object obj, ObjectSet set ) {
        EvaluatedArrayType other = (EvaluatedArrayType)obj ;
        return ((EvaluatedTypeBase)componentType()).myEquals(
            (EvaluatedTypeBase)other.componentType(), set ) ;
    }
    
    public int hashCode( ObjectSet set ) {
        return ((EvaluatedTypeBase)componentType()).hashCode( set ) * 37 ;
    }

    @Override
    public <R> R accept( Visitor<R> visitor ) {
        return visitor.visitEvaluatedArrayType(this) ;
    }
}
