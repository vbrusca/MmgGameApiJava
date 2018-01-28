package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class template for handling update events. Created on June 1, 2005, 10:57 PM
 * by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public interface MmgUpdateHandler {

    /**
     * Handle the incoming update event.
     *
     * @param obj The update event object.
     */
    public void MmgHandleUpdate(Object obj);

}
