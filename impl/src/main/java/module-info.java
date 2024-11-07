/*
 * Copyright (c) 2019, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

module gmbal {
    requires java.logging;
    requires transitive org.glassfish.external.management.api;

    exports org.glassfish.gmbal.impl;
    exports org.glassfish.gmbal.impl.trace;
}
