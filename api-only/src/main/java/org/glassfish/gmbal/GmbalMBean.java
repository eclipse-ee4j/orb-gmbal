/*
 * Copyright (c) 1997, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal;

import javax.management.DynamicMBean;
import javax.management.NotificationEmitter;

/** Type returned from ManagedObjectManager createRoot and register methods.
 * Used because all Gmbal MBeans are dynamic MBeans that support attribute
 * change notification.
 *
 * @author ken
 */
public interface GmbalMBean extends DynamicMBean, NotificationEmitter {
}
