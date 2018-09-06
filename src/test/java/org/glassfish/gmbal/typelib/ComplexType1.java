/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.typelib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ken
 */
@ForceTypelibError
public class ComplexType1 {
    public static class CT2 extends ArrayList<ComplexType1> {
        public static class CT3 extends ArrayList<CT2> {
            public static class CT4 extends ArrayList<CT3> {
                public static class CT5 extends ArrayList<CT4> {
                    public static class CT6 extends ArrayList<CT5> {
                        public static class CT7 extends ArrayList<CT6> {
                            public static class CT8 extends ArrayList<CT7> {
                                public static class CT9 extends ArrayList<CT8> {
                                    public static class CT10 extends ArrayList<CT9> {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class CT11 extends CT2.CT3.
        CT4.CT5.CT6.CT7.CT8.CT9.CT10 {

        public static class CT12 extends ArrayList<CT11> {
            public static class CT13 extends ArrayList<CT12> {
                public static class CT14 extends ArrayList<CT13> {
                    public static class CT15 extends ArrayList<CT14> {
                        public static class CT16 extends ArrayList<CT15> {
                            public static class CT17 extends ArrayList<CT16> {
                                public static class CT18 extends ArrayList<CT17> {
                                    public static class CT19 extends ArrayList<CT18> {
                                        public static class CT20 extends ArrayList<CT19> {
                                            @ForceTypelibError
                                            CT20 m1() {
                                                return null ;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class CT21 extends CT11.CT12.CT13.
        CT14.CT15.CT16.CT17.CT18.CT19.CT20 {
    }
}
