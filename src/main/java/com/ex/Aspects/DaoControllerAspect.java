package com.ex.Aspects;

import java.util.logging.Logger;

/***
 * DaoAspect used to advice the dao methods
 */
public class DaoControllerAspect {

    private static final Logger logger = Logger.getLogger(DaoControllerAspect.class.getName());

    /***
     * Advice before method executes
     */
    public void beforeAnyMethod() {
        logger.info("Querying Database...");
    }

    /***
     * Advice before method executes
     */
    public void afterAnyMethod() {
        logger.info("Query Complete");
    }

    /***
     * Advice before any controller method
     */
    public void beforeAnyControllerMethod() {
        logger.info("Controller is being called...");
    }

    /***
     * Advice before any controller method
     */
    public void afterAnyControllerMethod() {
        logger.info("Controller returned information...");
    }

    /***
     * Advice in case a dao method returns null
     * @param ret
     */
    public void afterReturningNull(Object ret) {
        logger.warning("The method returned " + ret);
    }

    /***
     * Advice in case a dao method throws an exception
     * @param e
     */
    public void afterThrowing(Exception e) {
        logger.severe(e.getMessage());
    }
}
