package org.wso2.sample.handlers.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;

/**
 * @scr.component name="sample.key.validation.handler"
 * immediate="true"
 */

public class SampleKeyValidationHandlerComponent {

    private static Log log = LogFactory.getLog(SampleKeyValidationHandlerComponent.class);

    protected void activate(ComponentContext ctxt) {

        log.info("SampleKeyValidationHandlerComponent activated successfully.");

    }

    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("SampleKeyValidationHandlerComponent is deactivated successfully");
        }
    }


}
