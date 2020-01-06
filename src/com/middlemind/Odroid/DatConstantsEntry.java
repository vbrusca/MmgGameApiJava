package com.middlemind.Odroid;

/**
 *
 * @author Victor G. Brusca
 */
public class DatConstantsEntry {
    public String key;
    public String val;
    public String type; //int, float, double, short, string, bool
    public String from;
    
    public DatConstantsEntry(String k, String v, String t) {
        key = k;
        val = v;
        type = t;
    }
    
    public DatConstantsEntry(String k, String v, String t, String f) {
        key = k;
        val = v;
        type = t;
        from = f;
    }
    
    public DatConstantsEntry(String k, String v) {
        key = k;
        val = v;
        type = "int";
        from = "DatConstants";
    }
}
