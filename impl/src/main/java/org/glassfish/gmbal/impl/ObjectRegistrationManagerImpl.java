/*
 * Copyright (c) 2001, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.impl;

import org.glassfish.gmbal.ManagedObjectManager;
import org.glassfish.pfl.tf.timer.spi.Named;
import org.glassfish.pfl.tf.timer.spi.ObjectRegistrationManager;

/**
 *
 * @author ken_admin
 */
public class ObjectRegistrationManagerImpl implements ObjectRegistrationManager {
    private final ManagedObjectManager mom ;

    public ObjectRegistrationManagerImpl( ManagedObjectManager mom ) {
        this.mom = mom ;
    }

    @Override
    public void manage( Named obj ) {
        // Note that no extra parameters are needed here, because Named.getName
        // is an ObjectNameKey.
        if (mom != null) {
            // System.out.println( "Registering " + obj ) ;
            mom.registerAtRoot( obj ) ;
        }
    }

    public void manage( Named parent, Named obj ) {
        // Note that no extra parameters are needed here, because Named.getName
        // is an ObjectNameKey.
        if (mom != null) {
            // System.out.println( "Registering " + obj ) ;
            mom.register( parent, obj ) ;
        }
    }

    public void unmanage( Named obj ) {
        if (mom != null) {
            mom.unregister( obj ) ;
        }
    }


}
