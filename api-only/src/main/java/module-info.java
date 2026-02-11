/*
 * Copyright (c) 2025, 2026 Contributors to the Eclipse Foundation
 * Copyright (c) 2019, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

module org.glassfish.gmbal.api {
    requires java.logging;
    requires transitive java.management;

    requires org.glassfish.external.management.api;
    requires org.glassfish.pfl.tf;

    exports org.glassfish.gmbal;

    uses org.glassfish.gmbal.ManagedObjectManagerService;
}
