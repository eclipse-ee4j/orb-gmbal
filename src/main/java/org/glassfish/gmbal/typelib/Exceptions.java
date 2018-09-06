/*
 * Copyright (c) 2008, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.typelib;

import java.lang.reflect.ParameterizedType;
import org.glassfish.pfl.basic.logex.Chain;
import org.glassfish.pfl.basic.logex.ExceptionWrapper;
import org.glassfish.pfl.basic.logex.Log;
import org.glassfish.pfl.basic.logex.LogLevel;
import org.glassfish.pfl.basic.logex.Message;
import org.glassfish.pfl.basic.logex.WrapperGenerator;

/**
 *
 * @author ken
 */
@ExceptionWrapper( idPrefix="GMBALTLIB",
    resourceBundle = "org.glassfish.gmbal.logex.LogStrings" )
public interface Exceptions {
    static final Exceptions self = WrapperGenerator.makeWrapper(
        Exceptions.class ) ;

    // Allow 100 exceptions per class
    static final int EXCEPTIONS_PER_CLASS = 100 ;

// TypeEvaluator
    static final int TYPE_EVALUATOR_START = 1 ;

    @Message( "Internal error in TypeEvaluator" )
    @Log( id=TYPE_EVALUATOR_START + 0 )
    IllegalStateException internalTypeEvaluatorError( @Chain Exception exc ) ;

    @Message( "evaluateType should not be called with a Method ({0})" )
    @Log( id=TYPE_EVALUATOR_START + 1 )
    IllegalArgumentException evaluateTypeCalledWithMethod( Object type ) ;

    @Message( "evaluateType should not be called with an unknown type ({0})" )
    @Log( id=TYPE_EVALUATOR_START + 2 )
    IllegalArgumentException evaluateTypeCalledWithUnknownType( Object type ) ;

    @Message( "Multiple upper bounds not supported on {0}" )
    @Log( id=TYPE_EVALUATOR_START + 3 )
    UnsupportedOperationException multipleUpperBoundsNotSupported(
        Object type ) ;

    @Message( "Type list and TypeVariable list are not the same length for {0}" )
    @Log( id=TYPE_EVALUATOR_START + 4 )
    IllegalArgumentException listsNotTheSameLengthInParamType(
        ParameterizedType pt ) ;

    @Message( "Error thrown from getEvaluatedType for class {0}")
    @Log( id=TYPE_EVALUATOR_START + 5, level=LogLevel.SEVERE )
    IllegalStateException errorInTypeEval( Class cls, @Chain Error err);
}
