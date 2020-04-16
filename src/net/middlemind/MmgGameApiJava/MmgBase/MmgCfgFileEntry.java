package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class used to store rows of information from a class config text file.
 * Created by Middlemind Games 03/15/2020
 * 
 * @author Victor G. Brusca
 */
public class MmgCfgFileEntry {
    
    /**
     * An enumeration that specifies the type of data the CfgEntryType holds.
     */
    public enum CfgEntryType {
        TYPE_DOUBLE,
        TYPE_STRING,
        NONE
    }
    
    /**
     * The type of data this MmgCfgFileEntry holds.
     */
    public CfgEntryType cfgType = CfgEntryType.NONE;
    
    /**
     * The double value this MmgCfgFileEntry holds, if it holds numeric data.
     */
    public Double number;
    
    /**
     * The string value this MmgCfgFileEntry holds, if it holds string data.
     */
    public String string;
    
    /**
     * The name or key of this MmgCfgFileEntry.
     */
    public String name;
    
    /**
     * A standard method that prints the contents of this MmgCfgFileEntry.
     * 
     * @return      A string representation of this class.
     */
    public String ToString() {
        String ret = "";

        if(name != null && name.equals("") == false) {
            if(cfgType == CfgEntryType.TYPE_DOUBLE) {
                ret = name.trim() + "=" + number.toString();

            } else if(cfgType == CfgEntryType.TYPE_STRING) {
                ret = name.trim() + "=" + string;

            }
        }
        
        return ret;
    }
}
