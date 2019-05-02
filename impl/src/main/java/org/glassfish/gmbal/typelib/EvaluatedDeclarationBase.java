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

/**
 *
 * @author ken
 */
public abstract class EvaluatedDeclarationBase extends EvaluatedTypeBase
    implements EvaluatedDeclaration {
    
    @Override
    public <R> R accept( Visitor<R> visitor ) {
        return visitor.visitEvaluatedDeclaration(this);
    }
}
