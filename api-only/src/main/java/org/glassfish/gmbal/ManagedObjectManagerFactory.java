/*
 * Copyright (c) 2025, 2026 Contributors to the Eclipse Foundation
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

import static java.lang.System.Logger.Level.DEBUG;

/**
 * Factory used to create ManagedObjectManager instances.
 * <p>
 * Note that the first access to this class sparks the search of the {@link ManagedObjectManagerService}
 * using {@link ServiceLoader} and a class loader of the current thread.
 */
public final class ManagedObjectManagerFactory {

    private static final ManagedObjectManagerService MOM_SERVICE;
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ServiceLoader<ManagedObjectManagerService> loader = ServiceLoader.load(ManagedObjectManagerService.class, classLoader);
        MOM_SERVICE = loader.findFirst().orElse(null);
        if (MOM_SERVICE == null) {
            // Some class loaders have nice toString, but even when not, the hash and class name can be useful.
            System.getLogger(ManagedObjectManagerFactory.class.getName()).log(DEBUG,
                "The service loader {0} did not find any implementation of {1} using thread context classloader {2}.",
                loader, ManagedObjectManagerService.class, classLoader);
        }
    }

    private ManagedObjectManagerFactory() {
        // utility class
    }


    /**
     * Create a new ManagedObjectManager.
     * All object names created will share the domain value passed on this call.
     * This ManagedObjectManager is at the top of the containment hierarchy - the parent of the root
     * is null.
     *
     * @param domain The domain to use for all ObjectNames created when MBeans are registered.
     * @return {@link ManagedObjectManager}
     */
    public static ManagedObjectManager createStandalone(final String domain) {
        return provideMomService().create(domain);
    }

    /**
     * Create a new ManagedObjectManager.
     * Alternative form of the create method to be used when the rootName is not needed explicitly.
     * If the root name is available from an <code>@ObjectNameKey</code> annotation, it is used;
     * otherwise the type is used as the name, since the root is a singleton.
     *
     * @param rootParentName The JMX ObjectName of the parent of the root.
     *            The parent is outside of the control of this ManagedObjectManager.
     *            The ManagedObjectManager root is a child of the MBean identified
     *            by the rootParentName.
     * @return {@link ManagedObjectManager}.
     */
    public static ManagedObjectManager createFederated(final ObjectName rootParentName) {
        return provideMomService().create(rootParentName);
    }

    /**
     * Useful to allow the same code to run without creating MBeans through gmbal.
     *
     * @return {@link ManagedObjectManager} that performs no operations.
     */
    public static ManagedObjectManager createNOOP() {
        return ManagedObjectManagerNOPImpl.self;
    }


    /**
     * Checks if the service was detected and is available.
     * If not, methods {@link #createFederated(ObjectName)} and {@link #createStandalone(String)}
     * will throw {@link IllegalStateException}.
     *
     * @return true if the service loader was able to find a factory.
     */
    public static boolean isAvailable() {
        return MOM_SERVICE != null;
    }

    private static ManagedObjectManagerService provideMomService() {
        if (isAvailable()) {
            return MOM_SERVICE;
        }
        throw new IllegalStateException("No ManagedObjectManagerService found using the service loader.");
    }
}
