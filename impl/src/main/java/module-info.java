/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 * Copyright (c) 2019, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

module org.glassfish.gmbal.impl {
    requires java.logging;
    requires java.management;

    requires org.glassfish.external.management.api;

    // transitive - return types make it mandatory
    requires transitive org.glassfish.gmbal.api;
    requires transitive org.glassfish.pfl.basic;
    requires transitive org.glassfish.pfl.tf;

    exports org.glassfish.gmbal.impl;
    exports org.glassfish.gmbal.typelib;

    provides org.glassfish.gmbal.ManagedObjectManagerService with org.glassfish.gmbal.impl.ManagedObjectManagerServiceImpl;
}
