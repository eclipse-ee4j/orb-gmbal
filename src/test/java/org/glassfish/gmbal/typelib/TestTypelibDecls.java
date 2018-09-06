/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

/*
 * @test
 * @summary Tests com.sun.beans.TypeResolver
 * @author Eamonn McManus
 * @author Ken Cavanaugh
 */

package org.glassfish.gmbal.typelib ;

import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestTypelibDecls {
    public static class Prototypes<T extends List<T>> {
        private Prototypes() {}

        List<Integer> getListInteger() { return null ; }
        public static final EvaluatedType LIST_INTEGER =
            getMethod( "getListInteger" ).returnType() ;

        List<Object> getListObject() { return null ; }
        public static final EvaluatedType LIST_OBJECT =
            getMethod( "getListObject" ).returnType() ;

        List<String> getListString() { return null ; }
        public static final EvaluatedType LIST_STRING =
            getMethod( "getListString" ).returnType() ;

        List<List<String>> getListListString() { return null ; }
        public static final EvaluatedType LIST_LIST_STRING =
            getMethod( "getListListString" ).returnType() ;

        List<List<Object>> getListListObject() { return null ; }
        public static final EvaluatedType LIST_LIST_OBJECT =
            getMethod( "getListListObject" ).returnType() ;

        Map<Object,Object> getMapObjectObject() { return null ; }
        public static final EvaluatedType MAP_OBJECT_OBJECT =
            getMethod( "getMapObjectObject" ).returnType() ;

        Map<String,Integer> getMapStringInteger() { return null ; }
        public static final EvaluatedType MAP_STRING_INTEGER =
            getMethod( "getMapStringInteger" ).returnType() ;

        List<Map<String,List<String>>> getListMapStringListString() {
            return null ;
        }
        public static final EvaluatedType LIST_MAP_STRING_LIST_STRING =
            getMethod( "getListMapStringListString" ).returnType() ;

        enum Color { RED, GREEN, BLUE } 

        Color getColor() { return Color.RED ; }
        public static final EvaluatedType COLOR =
            getMethod( "getColor" ).returnType() ;

        List<T> getRecursiveType()  { return null ; }
        public static final EvaluatedType RECURSIVE_TYPE =
            getMethod( "getRecursiveType" ).returnType() ;
    }

    public static class Prototypes2 {
        ConcurrentHashMap<String,String> getConcurrentHashMap() { return null ; }
        public static final EvaluatedType CONCURRENT_HASH_MAP_TYPE = 
            getMethod( "getConcurrentHashMap" ).returnType() ;
    }

    public static EvaluatedType getCHM() {
        EvaluatedType chm = TypeEvaluator.getEvaluatedType( Prototypes2.class ) ;
        return getMethod( (EvaluatedClassDeclaration)chm, 
            "getConcurrentHashMap" ).returnType() ;
    }

    public static EvaluatedMethodDeclaration getMethod(
        EvaluatedClassDeclaration cdecl, String name )  {

        // First check in cdecl
        for (EvaluatedMethodDeclaration mdecl : cdecl.methods() ) {
            if (mdecl.name().equals( name) ) {
                return mdecl ;
            }
        }

        // If not found, try the inherited EvaluatedClassDeclarations
        for (EvaluatedClassDeclaration ecd : cdecl.inheritance()) {
            EvaluatedMethodDeclaration emd = getMethod( ecd, name ) ;
            if (emd != null) {
                return emd ;
            }
        }

        return null ;
    }

    private static EvaluatedClassDeclaration proto =
        (EvaluatedClassDeclaration) TypeEvaluator.getEvaluatedType(
            Prototypes.class ) ;

    private static EvaluatedMethodDeclaration getMethod( String name ) {
        return getMethod( proto, name ) ;
    }
}
