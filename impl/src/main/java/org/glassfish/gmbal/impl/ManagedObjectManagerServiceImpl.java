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

package org.glassfish.gmbal.impl;

import javax.management.ObjectName;

import org.glassfish.gmbal.ManagedObjectManager;
import org.glassfish.gmbal.ManagedObjectManagerService;

public final class ManagedObjectManagerServiceImpl implements ManagedObjectManagerService {

    @Override
    public ManagedObjectManager create(final String domain) {
        return new ManagedObjectManagerImpl(domain);
    }


    @Override
    public ManagedObjectManager create(final ObjectName rootParentName) {
        return new ManagedObjectManagerImpl(rootParentName);
    }
}
