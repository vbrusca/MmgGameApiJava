package net.middlemind.MmgGameApiJava.MmgCore;

/**
 * An interface class for handling generic events. Generic events are events
 * that are not necessarily driven by user interaction. Created on August 1,
 * 2015, 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public interface GenericEventHandler {

    /**
     * Method for handling generic events.
     *
     * @param obj A generic event message used to determine what to do in the
     * actual implementation of this method.
     */
    public void HandleGenericEvent(GenericEventMessage obj);

}
