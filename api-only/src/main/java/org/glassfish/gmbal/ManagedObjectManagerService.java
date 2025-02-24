/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.gmbal;

import javax.management.ObjectName;

/**
 * Service used to create ManagedObjectManager instances.
 */
public interface ManagedObjectManagerService {

    /**
     * Create a new ManagedObjectManager. All objectnames created will share
     * the domain value passed on this call. This ManagedObjectManager is
     * at the top of the containment hierarchy: the parent of the root is null.
     *
     * @param domain The domain to use for all ObjectNames created when
     *            MBeans are registered.
     * @return A new ManagedObjectManager.
     */
    ManagedObjectManager create(final String domain);

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
    ManagedObjectManager create(final ObjectName rootParentName);
}
