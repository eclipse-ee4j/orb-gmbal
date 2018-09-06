/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.impl ;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.management.Descriptor;
import javax.management.modelmbean.DescriptorSupport;

public class DescriptorUtility {
    private DescriptorUtility() {}

    public static final Descriptor EMPTY_DESCRIPTOR =
        makeDescriptor( new HashMap<String,Object>() );

    public static Descriptor makeDescriptor( Map<String, ?> fields ) {
        if (fields == null) {
            throw Exceptions.self.nullMap() ;
        }
        SortedMap<String, Object> map =
            new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry<String, ?> entry : fields.entrySet()) {
            String name = entry.getKey();
            if (name == null || name.equals("")) {
                throw Exceptions.self.badFieldName() ;
            }
            if (map.containsKey(name)) {
                throw Exceptions.self.duplicateFieldName( name ) ;
            }
            map.put(name, entry.getValue());
        }
        int size = map.size();
        String[] names = map.keySet().toArray(new String[size]);
        Object[] values = map.values().toArray(new Object[size]);
        return new DescriptorSupport( names, values ) ;
    }

    // If descriptors contain the same names, later descriptors in the
    // sequence override the earlier ones.
    public static Descriptor union(Descriptor... descriptors) {
        Map<String, Object> map =
            new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
        for (Descriptor d : descriptors) {
            if (d != null) {
                String[] names = d.getFieldNames();
                for (String n : names) {
                    Object v = d.getFieldValue(n);
                    map.put(n, v);
                }
            }
        }

        return makeDescriptor(map);
    }
}
