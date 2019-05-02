/*
 * Copyright (c) 2008, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.gmbal.typelib;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
 * @author ken
 */
public class TypeEvaluatorTest extends TestCase {
    
    public TypeEvaluatorTest(String testName) {
        super(testName);
    }

    private static boolean firstTime = true ;

    @Override
    protected void setUp() throws Exception {
        if (firstTime) {
            System.out.println( "****************** TypeEvaluatorTest **********************" ) ;
            firstTime = false ;
        }
        super.setUp();
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public static Test suite() {
        Test suite = TestTypelib.suite() ;
        return suite;
    }
}
