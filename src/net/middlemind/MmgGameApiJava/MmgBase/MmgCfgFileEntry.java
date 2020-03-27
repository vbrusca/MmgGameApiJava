package net.middlemind.MmgGameApiJava.MmgBase;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 03/15/2020
 */
public class MmgCfgFileEntry {
    
    public enum CfgEntryType {
        TYPE_DOUBLE,
        TYPE_STRING,
        NONE
    }
    
    public CfgEntryType cfgType = CfgEntryType.NONE;
    public Double number;
    public String string;
    public String name;
    
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
