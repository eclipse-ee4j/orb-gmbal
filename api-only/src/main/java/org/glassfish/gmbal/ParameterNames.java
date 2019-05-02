/*
 * Copyright (c) 2007, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal ;

import java.lang.annotation.Documented ;
import java.lang.annotation.Target ;
import java.lang.annotation.ElementType ;
import java.lang.annotation.Retention ;
import java.lang.annotation.RetentionPolicy ;

/** This annotation is applied to a method that represents an MBean operation.
 * It defines the names of the parameters of the operation.  It must contain 
 * as many arguments as there are parameters in the annotated method.
 */
@Documented 
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
/**
 *
 * @author ken
 */
public @interface ParameterNames {
    String[] value() default {} ;
}
