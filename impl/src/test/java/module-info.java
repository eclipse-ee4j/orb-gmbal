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
    requires org.glassfish.external.management.api;

    requires transitive junit;
    requires transitive org.glassfish.gmbal.impl;

    exports org.glassfish.gmbal.test to junit;
    exports org.glassfish.gmbal.test.impl to junit;
    exports org.glassfish.gmbal.test.typelib to junit;

    opens org.glassfish.gmbal.test to org.glassfish.pfl.basic, org.glassfish.gmbal.impl;
    opens org.glassfish.gmbal.test.impl to org.glassfish.pfl.basic, org.glassfish.gmbal.impl;
}
