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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

/**
 *
 * @author ken
 */
public interface EvaluatedDeclaration extends EvaluatedType {
    <T extends Annotation> T annotation( Class<T> annotationType ) ;

    /** Return the annotations on this declaration.
     * 
     * @return List of all annotations on this declaration.
     */
    List<Annotation> annotations() ;

    int modifiers() ;

    AnnotatedElement element() ;
}
