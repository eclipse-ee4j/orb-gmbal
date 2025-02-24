/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 * Copyright (c) 2007, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal;

import java.util.ServiceLoader;

import javax.management.ObjectName;

/**
 * Factory used to create ManagedObjectManager instances.
 */
public final class ManagedObjectManagerFactory {

    static final ManagedObjectManagerService factory;
    static {
        ServiceLoader<ManagedObjectManagerService> loader = ServiceLoader.load(ManagedObjectManagerService.class);
        factory = loader.findFirst()
            .orElseThrow(() -> new IllegalStateException("No ManagedObjectManagerFactory found"));
    }

    private ManagedObjectManagerFactory() {
        // utility class
    }


    /**
     * Create a new ManagedObjectManager. All objectnames created will share
     * the domain value passed on this call. This ManagedObjectManager is
     * at the top of the containment hierarchy: the parent of the root is null.
     *
     * @param domain The domain to use for all ObjectNames created when
     *            MBeans are registered.
     * @return A new ManagedObjectManager.
     */
    public static ManagedObjectManager createStandalone(final String domain) {
        return factory.create(domain);
    }


    /**
     * Alternative form of the create method to be used when the
     * rootName is not needed explicitly. If the root name is available
     * from an @ObjectNameKey annotation, it is used; otherwise the
     * type is used as the name, since the root is a singleton.
     *
     * @param rootParentName The JMX ObjectName of the parent of the root.
     *            The parent is outside of the control of this ManagedObjectManager.
     *            The ManagedObjectManager root is a child of the MBean identified
     *            by the rootParentName.
     * @return The ManagedObjectManager.
     */
    public static ManagedObjectManager createFederated(final ObjectName rootParentName) {
        return factory.create(rootParentName);
    }


    /**
     * Return a ManagedObjectManager that performs no operations. Useful to
     * allow the same code to run with or without creating MBeans through
     * gmbal.
     *
     * @return ManagedObjectManager that performs no operations.
     */
    public static ManagedObjectManager createNOOP() {
        return ManagedObjectManagerNOPImpl.self;
    }
}
