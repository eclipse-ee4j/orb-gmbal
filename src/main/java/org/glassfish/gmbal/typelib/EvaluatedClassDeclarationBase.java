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

import java.util.List;
import org.glassfish.pfl.basic.contain.ObjectSet;

/**
 *
 * @author ken
 */
public abstract class EvaluatedClassDeclarationBase extends EvaluatedDeclarationBase
    implements EvaluatedClassDeclaration {
    
    void makeRepresentation( StringBuilder sb, ObjectSet set ) {
        sb.append( name() ) ;
        if (instantiations() != null && !set.contains( this)) {
            set.add( this) ;
            handleList( sb, "<", 
                castList( instantiations(), EvaluatedTypeBase.class ),
                ",", ">", set ) ;
        }
    }
                    
    boolean myEquals( Object obj, ObjectSet set ) {
        EvaluatedClassDeclaration other = (EvaluatedClassDeclaration)obj ;
        if (!name().equals(other.name())) {
            return false ;
        }

        return equalList( instantiations(), other.instantiations(), set ) ;
    }

    public int hashCode( ObjectSet set ) {
        set.add( this ) ;
        int result = name().hashCode() ;
        List<EvaluatedType> list = instantiations() ;
        if (list == null) {
            return result ;
        } else {
            for (EvaluatedType et : list) {
                EvaluatedTypeBase etb = (EvaluatedTypeBase)et ;
                if (!set.contains( et )) {
                    result = 31*result + etb.hashCode( set ) ; 
                }
            }

            return result ;
        }
    }

    @Override
    public <R> R accept( Visitor<R> visitor ) {
        return visitor.visitEvaluatedClassDeclaration( this ) ;
    }
}
