package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Font;

/**
 * Class that helps with font information. 
 * Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgFontData {

    /**
     * The default font family.
     */
    public static String DEFAULT_FONT_FAMILY = Font.SERIF;
    
    /**
     * The default font type.
     */
    public static int DEFAULT_FONT_TYPE = Font.PLAIN;

    /**
     * Current font size.
     */
    private static int fontSize = 18;

    /**
     * The target pixel height of the font.
     */
    private static int targetPixelHeight = 22;

    /**
     * The target pixel height scaled.
     */
    private static int targetPixelHeightScaled = 22;

    /**
     * The normal font to use.
     */
    private static Font fontNorm = new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, MmgFontData.fontSize);

    /**
     * The bold font to use.
     */
    private static Font fontBold = new Font(DEFAULT_FONT_FAMILY, Font.BOLD, MmgFontData.fontSize);

    /**
     * The italic font to use.
     */
    private static Font fontItalic = new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, MmgFontData.fontSize);

    /**
     * An MmgFont class that wraps the normal font.
     */
    private static MmgFont mmgFontNorm = new MmgFont(MmgFontData.fontNorm);

    /**
     * An MmgFont class that wraps the bold font.
     */
    private static MmgFont mmgFontBold = new MmgFont(MmgFontData.fontBold);

    /**
     * An MmgFont class that wraps the italic font.
     */
    private static MmgFont mmgFontItalic = new MmgFont(MmgFontData.fontItalic);

    /**
     * Constructor for this class.
     */
    public MmgFontData() {
        MmgFontData.CalculateScale();
    }

    /**
     * Calculates scale for the given target pixel height.
     */
    public static void CalculateScale() {
        MmgFont fnt = new MmgFont(fontBold);
        int fntSize = 12;
        fnt.SetFontSize(fntSize);
        fnt.SetText("Font Test");
        int max = 50;
        int count = 0;
        int target = MmgHelper.ScaleValue(targetPixelHeight);
        MmgFontData.targetPixelHeightScaled = target;

        if (fnt.GetHeight() < target) {
            while (fnt.GetHeight() < target) {
                count++;
                fntSize++;
                fnt.SetFontSize(fntSize);
                fnt.SetText("Font Test");

                if (count >= max) {
                    fntSize = 12;
                    break;
                }
            }
        } else {
            while (fnt.GetHeight() > target) {
                count++;
                fntSize--;
                fnt.SetFontSize(fntSize);
                fnt.SetText("Font Test");

                if (count >= max) {
                    fntSize = 12;
                    break;
                }
            }
        }

        if (fntSize % 2 != 0) {
            fntSize = (fntSize + 1);
        }

        fontSize = fntSize;
        fontNorm = new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, MmgFontData.fontSize);
        fontBold = new Font(DEFAULT_FONT_FAMILY, Font.BOLD, MmgFontData.fontSize);
        fontItalic = new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, MmgFontData.fontSize);
        mmgFontNorm = new MmgFont(MmgFontData.fontNorm);
        mmgFontBold = new MmgFont(MmgFontData.fontBold);
        mmgFontItalic = new MmgFont(MmgFontData.fontItalic);
    }

    /**
     * String representation of this font.
     *
     * @return      Returns a string representation of the font data.
     */
    public static String ToString() {
        String ret = "";
        ret += "Font Size: " + MmgFontData.GetFontSize() + System.lineSeparator();
        ret += "Target Pixel Height (Unscaled): " + MmgFontData.GetTargetPixelHeight() + System.lineSeparator();
        ret += "Target Pixel Height (Scaled): " + MmgHelper.ScaleValue(MmgFontData.GetTargetPixelHeight()) + System.lineSeparator();
        return ret;
    }

    /**
     * A static method that creates a default font.
     * 
     * @param sz    The target font size to use with the default font.
     * @return      A new Font instance that is of the default font family, default font type, and specified size.
     */
    public static Font CreateDefaultFont(int sz) {
        return new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_TYPE, sz);
    }

    /**
     * A static method that creates a default normal font.
     * 
     * @param sz    The target font size to use with the default normal font.
     * @return      A new Font instance that is of the default font family, plain font type, and specified size.
     */
    public static Font CreateDefaultNormalFont(int sz) {
        return new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, sz);
    }

    /**
     * A static method that creates a default bold font.
     * 
     * @param sz    The target font size to use with the default bold font.
     * @return      A new Font instance that is of the default font family, bold font type, and specified size.
     */
    public static Font CreateDefaultBoldFont(int sz) {
        return new Font(DEFAULT_FONT_FAMILY, Font.BOLD, sz);
    }

    /**
     * A static method that creates a default italic font.
     * 
     * @param sz    The target font size to use with the default italic font.
     * @return      A new Font instance that is of the default font family, italic font type, and specified size.
     */
    public static Font CreateDefaultItalicFont(int sz) {
        return new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, sz);
    }

    /**
     * A static method that creates a new default MmgFont instance.
     * 
     * @param sz    The target font size to use with the default font.
     * @return      A new MmgFont instance that is of the default font family, plain font type, and specified size.
     */
    public static MmgFont CreateDefaultMmgFont(int sz) {
        return new MmgFont(MmgFontData.CreateDefaultFont(sz));
    }

    /**
     * A static method that creates a new default normal MmgFont instance.
     * 
     * @param sz    The target font size to use with the default normal font.
     * @return      A new MmgFont instance that is of the default font family, normal font, and specified size.
     */
    public static MmgFont CreateDefaultNormalMmgFont(int sz) {
        return new MmgFont(MmgFontData.CreateDefaultNormalFont(sz));
    }

    /**
     * A static method that creates a new default bold MmgFont instance.
     * 
     * @param sz    The target font size to use with the default bold font.
     * @return      A new MmgFont instance that is of the default font family, bold font, and specified size.
     */
    public static MmgFont CreateDefaultBoldMmgFont(int sz) {
        return new MmgFont(MmgFontData.CreateDefaultBoldFont(sz));
    }

    /**
     * A static method that creates a new default italic MmgFont instance.
     * 
     * @param sz    The target font size to use with the default italic font.
     * @return      A new MmgFont instance that is of the default font family, italic font, and specified size.
     */
    public static MmgFont CreateDefaultItalicMmgFont(int sz) {
        return new MmgFont(MmgFontData.CreateDefaultItalicFont(sz));
    }

    /**
     * A static method that creates a new default small font.
     * 
     * @return      A new MmgFont instance that is of the default font family, normal font, and a small font size.
     */
    public static Font CreateDefaultFontSm() {
        return new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_TYPE, fontSize - 2);
    }

    /**
     * A static method that creates a new default small font.
     * 
     * @return      A new Font instance that is of the default font family, normal font, and a small font size.
     */
    public static Font CreateDefaultNormalFontSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, fontSize - 2);
    }

    /**
     * A static method that creates a new default, bold, small font.
     * 
     * @return      A new MmgFont instance that is of the default font family, bold font, and a small font size.
     */
    public static Font CreateDefaultBoldFontSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.BOLD, fontSize - 2);
    }

    /**
     * A static method that creates a new default, italic, small font.
     * 
     * @return      A new Font instance that is of the default font family, italic font, and a small font size.
     */
    public static Font CreateDefaultItalicFontSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, fontSize - 2);
    }

    /**
     * A static method that creates a new default, normal, extra small font.
     * 
     * @return      A new MmgFont instance that is of the default font family, normal font, and an extra small font size.
     */
    public static Font CreateDefaultFontExtraSm() {
        return new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_TYPE, fontSize - 4);
    }

    /**
     * A static method that creates a new default, normal, extra small font.
     * 
     * @return      A new MmgFont instance that is of the default font family, normal font, and an extra small font size.
     */
    public static Font CreateDefaultNormalFontExtraSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, fontSize - 4);
    }

    /**
     * A static method that creates a new default, bold, extra small font.
     * 
     * @return      A new Font instance that is of the default font family, bold font, and an extra small font size.
     */
    public static Font CreateDefaultBoldFontExtraSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.BOLD, fontSize - 4);
    }

    /**
     * A static method that creates a new default, italic, extra small font.
     * 
     * @return      A new Font instance that is of the default font family, italic font, and an extra small font size.
     */
    public static Font CreateDefaultItalicFontExtraSm() {
        return new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, fontSize - 4);
    }

    /**
     * A static method that creates a new default, normal, small font.
     * 
     * @return      A new MmgFont instance based on a Font from CreateDefaultFontSm.
     */
    public static MmgFont CreateDefaultMmgFontSm() {
        return new MmgFont(MmgFontData.CreateDefaultFontSm());
    }

    /**
     * A static method that creates a new default, normal, small font.
     * 
     * @return      A new MmgFont instance based on a Font from CreateDefaultNormalFontSm.
     */
    public static MmgFont CreateDefaultNormalMmgFontSm() {
        return new MmgFont(MmgFontData.CreateDefaultNormalFontSm());
    }

    /**
     * A static method that creates a new default, bold, small font.
     * 
     * @return      A new MmgFont instance based on a Font from CreateDefaultBoldFontSm.
     */
    public static MmgFont CreateDefaultBoldMmgFontSm() {
        return new MmgFont(MmgFontData.CreateDefaultBoldFontSm());
    }

    /**
     * A static method that creates a new default, italic, small font.
     * 
     * @return      A new MmgFont instance based on a Font from CreateDefaultItalicFontSm.
     */
    public static MmgFont CreateDefaultItalicMmgFontSm() {
        return new MmgFont(MmgFontData.CreateDefaultItalicFontSm());
    }

    /**
     * A static method that creates a new default, normal, large font.
     * 
     * @return      A new Font instance based on the default font family, default font type, large font size.
     */
    public static Font CreateDefaultFontLg() {
        return new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_TYPE, fontSize + 2);
    }

    /**
     * A static method that creates a new default, normal, large font.
     * 
     * @return      A new Font instance based on the default font family, normal font type, large font size.
     */
    public static Font CreateDefaultNormalFontLg() {
        return new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, fontSize + 2);
    }

    /**
     * A static method that creates a new default, bold, large font.
     * 
     * @return      A new Font instance based on the default font family, bold font type, large font size.
     */
    public static Font CreateDefaultBoldFontLg() {
        return new Font(DEFAULT_FONT_FAMILY, Font.BOLD, fontSize + 2);
    }

    /**
     * A static method that creates a new default, italic, large font.
     * 
     * @return      A new Font instance based on the default font family, italic font type, large font size.
     */
    public static Font CreateDefaultItalicFontLg() {
        return new Font(DEFAULT_FONT_FAMILY, Font.ITALIC, fontSize + 2);
    }

    /**
     * A static method that creates a new default, normal, large font.
     * 
     * @return      A new MmgFont instance based on the default font family, default font type, large font size.
     */
    public static MmgFont CreateDefaultMmgFontLg() {
        return new MmgFont(MmgFontData.CreateDefaultFontLg());
    }

    /**
     * A static method that creates a new default, normal, large font.
     * 
     * @return      A new MmgFont instance based on the default font family, normal font type, large font size.
     */
    public static MmgFont CreateDefaultNormalMmgFontLg() {
        return new MmgFont(MmgFontData.CreateDefaultNormalFontLg());
    }

    /**
     * A static method that creates a new default, bold, large font.
     * 
     * @return      A new MmgFont instance based on the default font family, bold font type, large font size.
     */
    public static MmgFont CreateDefaultBoldMmgFontLg() {
        return new MmgFont(MmgFontData.CreateDefaultBoldFontLg());
    }

    /**
     * A static method that creates a new default, italic, large font.
     * 
     * @return      A new MmgFont instance based on the default font family, italic font type, large font size.
     */
    public static MmgFont CreateDefaultItalicMmgFontLg() {
        return new MmgFont(MmgFontData.CreateDefaultItalicFontLg());
    }

    /**
     * Gets the font size.
     *
     * @return      The font size.
     */
    public static int GetFontSize() {
        return fontSize;
    }

    /**
     * Sets the font size.
     *
     * @param fontSize  The font size.
     */
    public static void SetFontSize(int fontSize) {
        MmgFontData.fontSize = fontSize;
    }

    /**
     * Gets the target pixel height.
     *
     * @return      The target pixel height.
     */
    public static int GetTargetPixelHeight() {
        return targetPixelHeight;
    }

    /**
     * Sets the target pixel height.
     *
     * @param targetPixelHeight     The target pixel height.
     */
    public static void SetTargetPixelHeight(int targetPixelHeight) {
        MmgFontData.targetPixelHeight = targetPixelHeight;
    }

    /**
     * Gets the target pixel height scaled.
     *
     * @return      The target height scaled.
     */
    public static int GetTargetPixelHeightScaled() {
        return MmgFontData.targetPixelHeightScaled;
    }

    /**
     * Sets the target pixel height scaled.
     *
     * @param t     The target height scaled.
     */
    public static void SetTargetPixelHeightScaled(int t) {
        MmgFontData.targetPixelHeightScaled = t;
    }

    /**
     * Gets the normal font.
     *
     * @return      The normal font.
     */
    public static Font GetFontNorm() {
        return fontNorm;
    }

    /**
     * Sets the normal font.
     *
     * @param tfNorm    The normal font.
     */
    public static void SetFontNorm(Font tfNorm) {
        MmgFontData.fontNorm = tfNorm;
    }

    /**
     * Gets the bold font.
     *
     * @return      The bold font.
     */
    public static Font GetFontBold() {
        return fontBold;
    }

    /**
     * Sets the bold font.
     *
     * @param tfBold    The bold font.
     */
    public static void SetFontBold(Font tfBold) {
        MmgFontData.fontBold = tfBold;
    }

    /**
     * Gets the italic font.
     *
     * @return      The italic font.
     */
    public static Font GetFontItalic() {
        return fontItalic;
    }

    /**
     * Sets the italic font.
     *
     * @param tfItac    The italic font.
     */
    public static void SetFontItalic(Font tfItac) {
        MmgFontData.fontItalic = tfItac;
    }

    /**
     * Gets the MmgFont for normal text.
     *
     * @return      The MmgFont for normal text.
     */
    public static MmgFont GetMmgFontNorm() {
        return mmgFontNorm.CloneTyped();
    }

    /**
     * Sets the MmgFont for normal text.
     *
     * @param mmgFontNorm       The MmgFont for normal text.
     */
    public static void SetMmgFontNorm(MmgFont mmgFontNorm) {
        MmgFontData.mmgFontNorm = mmgFontNorm;
    }

    /**
     * Gets the MmgFont for bold text.
     *
     * @return      The MmgFont for bold text.
     */
    public static MmgFont GetMmgFontBold() {
        return mmgFontBold.CloneTyped();
    }

    /**
     * Sets the MmgFont for bold text.
     *
     * @param mmgFontBold       The MmgFont for bold text.
     */
    public static void SetMmgFontBold(MmgFont mmgFontBold) {
        MmgFontData.mmgFontBold = mmgFontBold;
    }

    /**
     * Gets the MmgFont for italic text.
     *
     * @return      The MmgFont for italic text.
     */
    public static MmgFont GetMmgFontItalic() {
        return mmgFontItalic.CloneTyped();
    }

    /**
     * Sets the MmgFont for italic text.
     *
     * @param mmgFontItalic     The MmgFont to use for italic text.
     */
    public static void SetMmgFontItalic(MmgFont mmgFontItalic) {
        MmgFontData.mmgFontItalic = mmgFontItalic;
    }
}
