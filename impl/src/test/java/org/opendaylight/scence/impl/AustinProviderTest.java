/*
 * Copyright (c) 2015 asiainfo, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.scence.impl;

import org.junit.Test;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;

import static org.mockito.Mockito.mock;

public class AustinProviderTest {
    @Test
    public void testOnSessionInitiated() {
        AustinProvider provider = new AustinProvider();

        // ensure no exceptions
        // currently this method is empty
        provider.onSessionInitiated(mock(BindingAwareBroker.ProviderContext.class));
    }

    @Test
    public void testClose() throws Exception {
        AustinProvider provider = new AustinProvider();

        // ensure no exceptions
        // currently this method is empty
        provider.close();
    }
}
