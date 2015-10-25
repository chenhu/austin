/*
 * Copyright (c) 2015 asiainfo, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.scence.impl;

import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AustinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AustinProvider implements BindingAwareProvider, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(AustinProvider.class);
    private RpcRegistration<AustinService> austinService;

    @Override
    public void onSessionInitiated(ProviderContext session) {
        LOG.info("AustinProvider Session Initiated");
        austinService = session.addRpcImplementation(AustinService.class, new AddFlowServiceImpl());
    }

    @Override
    public void close() throws Exception {
        LOG.info("AustinProvider Closed");
        if (austinService != null) {
            austinService.close();
        }
    }

}
