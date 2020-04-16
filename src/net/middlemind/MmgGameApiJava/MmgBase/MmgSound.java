package net.middlemind.MmgGameApiJava.MmgBase;

import javax.sound.sampled.Clip;

/**
 * Class that wraps the underlying sound object. 
 * Created by Middlemind Games 06/01/2005
 *
 * @author Victor G. Brusca
 */
public class MmgSound {

    /**
     * Centralized, unique sound id.
     */
    private static int ID_SRC = 0;

    /**
     * Volume for all sounds.
     */
    public static float volume = 0.65f;

    /**
     * Unique sound id, integer form.
     */
    private int id;

    /**
     * Unique sound id string form.
     */
    private String idStr;

    /**
     * The lower level sound object.
     */
    private Clip sound;

    /**
     * Constructor that sets the sound Clip value.
     *
     * @param se        The sound clip for this sounds object.
     * @see             Clip
     */
    public MmgSound(Clip se) {
        sound = se;
        SetId();
    }

    /**
     * Constructor that sets the value of this class based on the attributes of
     * the given argument.
     *
     * @param se    The sound object to use as a basis for a new sound object.
     */
    public MmgSound(MmgSound se) {
        sound = se.GetSound();
        SetId();
    }

    /**
     * Sets the volume of the sound system.
     *
     * @param f     The volume to set for all sounds.
     * @return      The current volume.
     */
    public static float SetVolume(float f) {
        volume = f;
        if (volume > 1.3f) {
            volume = 1.0f;
        }

        if (volume < 0.1f) {
            volume = 0f;
        }
        return volume;
    }

    /**
     * Gets a string version of the id.
     *
     * @return      A string version of the id.
     */
    public String GetIdStr() {
        return idStr;
    }

    /**
     * Gets an integer version of the id.
     *
     * @return      An integer version of the id.
     */
    public int GetId() {
        return id;
    }

    /**
     * Sets the unique sound id.
     */
    private void SetId() {
        id = MmgSound.ID_SRC;
        idStr = (id + "");
        MmgSound.ID_SRC++;
    }

    /**
     * Creates a basic clone of this class.
     *
     * @return      A clone of this object.
     */
    public MmgSound Clone() {
        return new MmgSound(sound);
    }

    /**
     * Gets the low level Clip object.
     *
     * @return      The sound this class represents.
     * @see         Clip
     */
    public Clip GetSound() {
        return sound;
    }

    /**
     * Starts playing this sound.
     */
    public void Play() {
        if(sound.isRunning()) {
            sound.stop();
        }
        
        sound.setFramePosition(0);        
        sound.start();
    }

    /**
     * Starts playing this sound with the given loop and rate values.
     *
     * @param loop  The loop variable used when playing this sound.
     * @param rate  The rate variable used when playing this sound.
     */
    public void Play(int loop, float rate) {
        if(sound.isRunning()) {
            sound.stop();
        }
        
        sound.setFramePosition(0); 
        sound.loop(loop);
        sound.start();        
    }

    /**
     * Stops playing this sound.
     */
    public void Stop() {
        sound.stop();
    }
}
