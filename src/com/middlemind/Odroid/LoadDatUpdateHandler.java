package com.middlemind.Odroid;

/**
 * Class to define the LoadDatUpdateHandler interface.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public interface LoadDatUpdateHandler {
    
    /**
     * The message handling method to override.
     * 
     * @param obj       A LoadDatUpdateMessage.
     */
    public void HandleUpdate(LoadDatUpdateMessage obj);
    
}
