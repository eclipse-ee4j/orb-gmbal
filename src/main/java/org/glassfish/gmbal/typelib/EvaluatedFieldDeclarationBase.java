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
public abstract class EvaluatedFieldDeclarationBase 
    extends EvaluatedDeclarationBase
    implements EvaluatedFieldDeclaration {
    
    @Override
    public <R> R accept( Visitor<R> visitor ) {
        return visitor.visitEvaluatedFieldDeclaration(this) ;
    }        

    public void containingClass( EvaluatedClassDeclaration cdecl ) {
        throw new IllegalArgumentException( "Operation not permitted" ) ;
    }

    void makeRepresentation( StringBuilder sb, ObjectSet set ) {
        handleModifier( sb, modifiers() ) ;
        sb.append( " " ) ;
        sb.append( fieldType().toString() ) ;
        sb.append( " " ) ;
        sb.append( name() ) ;
    }
            
    public boolean myEquals( Object obj, ObjectSet set ) {
        EvaluatedFieldDeclaration other = (EvaluatedFieldDeclaration)obj ;
        if (!((EvaluatedTypeBase)fieldType()).myEquals( other.fieldType(), set )
            || !name().equals( other.name() ) )  {

            return false ;
        }
        
        return true ;
    }
    
    @Override
    public int hashCode( ObjectSet set ) {
        return fieldType().hashCode() ^
            name().hashCode() ;
    }
}
