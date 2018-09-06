/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.gmbal.impl;

import org.glassfish.gmbal.AMXMBeanInterface;
import org.glassfish.gmbal.AMXClient;
import javax.management.MBeanException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.management.Descriptor;
import javax.management.MBeanInfo;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import org.glassfish.pfl.basic.algorithm.Algorithms;
import org.glassfish.pfl.basic.func.UnaryFunction;

/**
 *
 * @author ken
 */
public class AMXImpl implements AMXMBeanInterface {
    private MBeanImpl mbean ;

    public AMXImpl( final MBeanImpl mb ) {
        this.mbean = mb ;
    }

    public String getName() {
        return mbean.name() ;
    }

    public Map<String,?> getMeta() {
        MBeanInfo mbi = mbean.getMBeanInfo() ;
        ModelMBeanInfoSupport  mmbi = (ModelMBeanInfoSupport)mbi ;
        Descriptor desc ;
        try {
            desc = mmbi.getMBeanDescriptor();
        } catch (MBeanException ex) {
            throw Exceptions.self.excForGetMeta( ex ) ;
        }
        Map<String,Object> result = new HashMap<String,Object>() ;
        for (String key : desc.getFieldNames()) {
            result.put( key, desc.getFieldValue(key)) ;
        }
        return result ;
    }

    public AMXMBeanInterface getParent() {
        MBeanImpl parent = mbean.parent() ;
        if (parent != null) {
            return parent.facet( AMXMBeanInterface.class ) ;
        } else {
            ManagedObjectManagerInternal mom = mbean.skeleton().mom() ;
            ObjectName rpn = mom.getRootParentName() ;
            if (rpn == null) {
                return null ;
            } else {
                return new AMXClient( mom.getMBeanServer(), rpn ) ;
            }
        }
    }

    public AMXMBeanInterface[] getChildren() {
        List<AMXMBeanInterface> children = getContained( mbean.children().keySet() ) ;
        return children.toArray( new AMXMBeanInterface[children.size()] ) ;
    }

    private static UnaryFunction<MBeanImpl,AMXMBeanInterface> extract =
        new UnaryFunction<MBeanImpl,AMXMBeanInterface>() {
            @SuppressWarnings("unchecked")
            public AMXMBeanInterface evaluate( MBeanImpl mb ) {
                return mb.facet( AMXMBeanInterface.class ) ;
            }
        } ;

   private List<AMXMBeanInterface> getContained( Set<String> types ) {
        List<AMXMBeanInterface> result = new ArrayList<AMXMBeanInterface>() ;
        for (String str : types ) {
            result.addAll( Arrays.asList( getContained( str ) ) ) ;
        }
        return result ;
   }

    private AMXMBeanInterface[] getContained(String type) {
        Collection<AMXMBeanInterface> children = Algorithms.map( mbean.children().get( type ),
            extract ).values() ;
        return children.toArray( new AMXMBeanInterface[children.size()] ) ;
    }
}
