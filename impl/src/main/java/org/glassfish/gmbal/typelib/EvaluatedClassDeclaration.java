/*
 * Copyright (c) 2001, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.typelib;

import java.util.List;

public interface EvaluatedClassDeclaration extends EvaluatedDeclaration {
    boolean simpleClass() ;

    void freeze() ;

    List<EvaluatedFieldDeclaration> fields() ;

    void fields( List<EvaluatedFieldDeclaration> arg ) ;

    List<EvaluatedType> instantiations() ;

    void instantiations( List<EvaluatedType> arg ) ;

    List<EvaluatedMethodDeclaration> methods() ;
    
    void methods( List<EvaluatedMethodDeclaration> meths ) ;

    List<EvaluatedClassDeclaration> inheritance() ;

    void inheritance( List<EvaluatedClassDeclaration> inh ) ;
        
    Class cls() ;
}
