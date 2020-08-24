package com.ex.Aspects;

import java.util.logging.Logger;

public class DaoAspect {

    private static final Logger logger = Logger.getLogger(DaoAspect.class.getName());

    // demonstrates advice before method exec
    public void beforeAnyMethod() {
        logger.info("Querying Database...");
    }

    // demonstrates advice before method exec
    public void afterAnyMethod() {
        logger.info("Query Complete");
    }

    // demonstrates advice before any controller method
    public void beforeAnyControllerMethod() {
        logger.info("Controller is being called...");
    }

    public void afterAnyControllerMethod() {
        logger.info("Controller returned information...");
    }

    // demonstrates advice for intercepting exceptions
    public void afterReturningNull(Object ret) {
        logger.warning("The method returned " + ret);
    }

}
