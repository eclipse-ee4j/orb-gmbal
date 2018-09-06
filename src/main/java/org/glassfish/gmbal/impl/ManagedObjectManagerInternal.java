/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.impl ;

import org.glassfish.gmbal.typelib.EvaluatedClassAnalyzer;

import org.glassfish.gmbal.ManagedObjectManager ;
import org.glassfish.gmbal.InheritedAttribute ;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.management.ObjectName;
import org.glassfish.gmbal.AMXMetadata;
import org.glassfish.gmbal.typelib.EvaluatedClassDeclaration;
import org.glassfish.gmbal.typelib.EvaluatedDeclaration;
import org.glassfish.gmbal.typelib.EvaluatedType;
import org.glassfish.pfl.basic.contain.Pair;
import org.glassfish.pfl.basic.facet.FacetAccessor;
import org.glassfish.pfl.basic.func.UnaryPredicate;

/** The internal interface to the ManagedObjectManager that is used in the
 * gmbal implementation.  The methods defined here are not for use by
 * gmbal clients.
 * 
 * @author ken
 */
public interface ManagedObjectManagerInternal extends ManagedObjectManager {
    /** Construct or lookup the TypeConverter for the given type.
     * 
     * @param type The type for which we need a TypeConverter.
     * @return The type converter.
     */
    TypeConverter getTypeConverter( EvaluatedType type ) ;
    
    String getDescription( EvaluatedDeclaration element ) ;
        
    <T extends Annotation> T getAnnotation( AnnotatedElement element,
        Class<T> type ) ;

    /* Return the list of all annotations on this declaration and its
     * related declarations.
     * <p> "related" has different meanings for the various kinds of
     * EvaluatedDeclarations.
     * <ul>
     * <li>For EvaluatedFieldDeclaration, fullAnnotations() is the same
     * as annotations().</li>
     * <li> For EvaluatedClassDeclaration, fullAnnotations includes annotations()
     * from the class and all of its super classes/interfaces, with only the
     * most derived instance of any annotation included.</li>
     * <li>For EvaluatedMethodDeclaration, fullAnnotations includes
     * annotations() on the method, and on all overridden methods in derivation
     * order, with only the most derived annotations included.
     *
     * @return full list of annotations.
     */
     Collection<Annotation> getAnnotations( AnnotatedElement element ) ;

    /** Find the superclass or superinterface of cls (which may be cls itself) 
     * that has the given annotationClass as an annotation.  If the annotated 
     * Class has an IncludeSubclass annotation, add those classes into the 
     * EvaluatedClassAnalyzer for the annotated class.
     * @param cls The class for which we need a EvaluatedClassAnalyzer.
     * @param annotationClass The annotation that must be present on cls or
     * a superclass or superinterface.
     * @return A Pair of the parent class of cls, and the EvaluatedClassAnalyzer.
     */
    Pair<EvaluatedClassDeclaration,EvaluatedClassAnalyzer> getClassAnalyzer( 
        EvaluatedClassDeclaration cls, Class<? extends Annotation> annotationClass ) ;
    
    /** Get the inherited attributes from the EvaluatedClassAnalyzer.
     * @param ca The ClassAnalyzer to check for InheritedAttribute(s).
     * @return The inherited attributes.
     */
    List<InheritedAttribute> getInheritedAttributes( EvaluatedClassAnalyzer ca ) ;

    /** Used in getAttributes to indicate type of Attribute being considered.
     * This matters because JMX (unforunately) defines different rules for
     * converting method names to attribute ids in MBeans and in CompositeData:
     * for MBeans, an initial get or set is stripped, leaving an ID with an
     * initial upper case letter; for CompositeData, JMX follows the JavaBeans
     * conventions, and converts the first letter of the ID to lower case.
     * Since getAttributes is used in both cases, it needs to know the difference.
     */
    public enum AttributeDescriptorType { MBEAN_ATTR, COMPOSITE_DATA_ATTR }

    Pair<Map<String,AttributeDescriptor>,Map<String,AttributeDescriptor>>
        getAttributes( EvaluatedClassAnalyzer ca, AttributeDescriptorType adt ) ;
    
    <K,V> void putIfNotPresent( final Map<K,V> map,
        final K key, final V value ) ;
        
    String getTypeName( Class<?> cls, String fieldName,
        String nameFromAnnotation ) ;
    
    <T extends EvaluatedDeclaration> UnaryPredicate<T> forAnnotation(
        Class<? extends Annotation> annotation,
        Class<T> elemType ) ;
    
    FacetAccessor getFacetAccessor( Object obj ) ;
    
    MBeanImpl constructMBean( MBeanImpl parentEntity, Object obj, String name ) ;

    ObjectName getRootParentName() ;
    
    boolean registrationDebug() ;
    
    boolean registrationFineDebug() ;
    
    boolean jmxRegistrationDebug() ;

    boolean runtimeDebug() ;

    AMXMetadata getDefaultAMXMetadata() ;

    <T extends Annotation> T getFirstAnnotationOnClass(
        EvaluatedClassDeclaration element, Class<T> type ) ;

    boolean isAMXAttributeName( String name ) ;
}
