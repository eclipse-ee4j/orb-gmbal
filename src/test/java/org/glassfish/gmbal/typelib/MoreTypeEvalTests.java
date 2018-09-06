/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.typelib;

import junit.framework.TestCase;

/**
 * @author ken
 */
public class MoreTypeEvalTests extends TestCase {
    private static int evalClassMapSize = 0 ;

    public static int additionalClasses() {
        //int oldSize = evalClassMapSize ;
        return TypeEvaluator.evalClassMapSize() ;
        //return evalClassMapSize - oldSize  ;
    }
    public MoreTypeEvalTests(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println( "initial evalClassMap size = "
            + additionalClasses() ) ;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println( "final evalClassMap size = "
            + additionalClasses() ) ;
    }

    public void testListInteger() {
        System.out.println( "testListInteger" ) ;
        EvaluatedType type = TestTypelibDecls.Prototypes.LIST_INTEGER ;
        assertEquals( "java.util.List<java.lang.Integer>", type.toString() );
    }

    public void testListObject() {
        System.out.println( "testListObject" ) ;
        EvaluatedType type = TestTypelibDecls.Prototypes.LIST_OBJECT ;
        assertEquals( "java.util.List<java.lang.Object>", type.toString() );
    }

    public void testListString() {
        System.out.println( "testListString" ) ;
        EvaluatedType type = TestTypelibDecls.Prototypes.LIST_STRING ;
        assertEquals( "java.util.List<java.lang.String>", type.toString() );
    }

    public void testListListString() {
        System.out.println( "testListListString") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.LIST_LIST_STRING ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals( "java.util.List<java.util.List<java.lang.String>>",
            type.toString() ) ;
    }

    public void testListListObject() {
        System.out.println( "testListListObject") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.LIST_LIST_OBJECT ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals( "java.util.List<java.util.List<java.lang.Object>>",
            type.toString() ) ;
    }

    public void testMapObjectObject() {
        System.out.println( "testMapObjectObject") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.MAP_OBJECT_OBJECT ;
        assertEquals( "java.util.Map<java.lang.Object,java.lang.Object>",
            type.toString() ) ;
    }

    public void testMapStringInteger() {
        System.out.println( "testMapStringInteger") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.MAP_STRING_INTEGER ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals( "java.util.Map<java.lang.String,java.lang.Integer>",
            type.toString() ) ;
    }

    public void testListMapStringListString() {
        System.out.println( "testListMapStringListString") ;
        EvaluatedType type =
            TestTypelibDecls.Prototypes.LIST_MAP_STRING_LIST_STRING ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals(
            "java.util.List<java.util.Map<java.lang.String,java.util.List<java.lang.String>>>",
            type.toString() ) ;
    }

    public void testRecursiveType() {
        System.out.println( "testRecursiveType") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.RECURSIVE_TYPE ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals( "java.util.List<java.util.List>", type.toString() ) ;
    }

    public void testColor() {
        System.out.println( "testColor") ;
        EvaluatedType type = TestTypelibDecls.Prototypes.COLOR ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals(
            "org.glassfish.gmbal.typelib.TestTypelibDecls$Prototypes$Color",
            type.toString() ) ;
    }

    public void testConcurrentHashMap() {
        System.out.println( "testConcurrentHashMap") ;
        EvaluatedType type = TestTypelibDecls.getCHM() ;
        // System.out.println( "toString() = " + type.toString() ) ;
        assertEquals(
            "java.util.concurrent.ConcurrentHashMap<java.lang.String,java.lang.String>",
            type.toString() ) ;
    }

    public void testComplexType() {
        try {
            System.out.println( "testComplexType1") ;
            EvaluatedType etype = TypeEvaluator.getEvaluatedType( ComplexType1.CT21.class) ;
            fail( "Expected an exception" ) ;
        } catch (StackOverflowError ignored) {
        } catch (IllegalStateException ignored) {
        }
    }
}
