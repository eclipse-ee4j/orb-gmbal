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

package org.glassfish.gmbal.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.glassfish.gmbal.ManagedObjectManagerFactory;

/**
 *
 * @author ken
 */
public class TypeConverterImplTest extends TestCase {
    ManagedObjectManagerInternal mom ;

    public TypeConverterImplTest(String testName) {
        super(testName);
    }

    private static boolean firstTime = true ;

    @Override
    protected void setUp() throws Exception {
        if (firstTime) {
            System.out.println( "****************** TypeConverterImplTest **********************" ) ;
            firstTime = false ;
        }
        super.setUp();
        mom = (ManagedObjectManagerInternal)ManagedObjectManagerFactory
            .createStandalone( "TestDomain" ) ;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mom.close();
    }

    /*
    public void testGetJavaClass_OpenType() {
        System.out.println("getJavaClass");
        OpenType ot = null;
        Class expResult = null;
        Class result = TypeConverterImpl.getJavaClass(ot);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testGetJavaClass_EvaluatedType() {
        System.out.println("getJavaClass");
        EvaluatedType type = null;
        Class expResult = null;
        Class result = TypeConverterImpl.getJavaClass(type);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
     */

    public void testData1() {
        Logger logger = Logger.getLogger( "org.glassfish.gmbal.impl") ;
        logger.setLevel(Level.OFF ) ;
        try {
            TypeConverterTestData.Data1TestData.test( mom ) ;
        } finally {
            logger.setLevel(Level.INFO ) ;
        }
    }

    public void testData2() {
        TypeConverterTestData.Data2TestData.test( mom ) ;
    }

    public void testDoubleIndexData() {
        Logger logger = Logger.getLogger( "org.glassfish.gmbal.impl") ;
        logger.setLevel(Level.OFF ) ;
        try {
            TypeConverterTestData.Data3TestData.test( mom ) ;
        } finally {
            logger.setLevel(Level.INFO ) ;
        }
    }
}
