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

module org.glassfish.gmbal.impl.test {
    requires java.logging;

    requires org.glassfish.gmbal.api;
    requires org.glassfish.gmbal.impl;

    requires org.glassfish.pfl.basic;
    requires org.glassfish.pfl.tf;

    requires junit;

    exports org.glassfish.gmbal.test;
    exports org.glassfish.gmbal.test.impl;
    exports org.glassfish.gmbal.test.typelib;

    opens org.glassfish.gmbal.test to org.glassfish.pfl.basic, org.glassfish.gmbal.impl;
    opens org.glassfish.gmbal.test.impl to org.glassfish.pfl.basic, org.glassfish.gmbal.impl;
}
