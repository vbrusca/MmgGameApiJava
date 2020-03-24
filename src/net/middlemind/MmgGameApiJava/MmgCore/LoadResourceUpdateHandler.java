package net.middlemind.MmgGameApiJava.MmgCore;

/**
 * Class to define the LoadDatUpdateHandler interface.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public interface LoadResourceUpdateHandler {
    
    /**
     * The message handling method to override.
     * 
     * @param obj       A LoadDatUpdateMessage.
     */
    public void HandleUpdate(LoadResourceUpdateMessage obj);   
}
