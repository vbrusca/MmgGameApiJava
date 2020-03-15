package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import net.middlemind.MmgGameApiJava.MmgBase.*;

/**
 * A class to control the background story of the game. Renders the story in a
 * box with a detected number of lines drawn. Created on August 1, 2015, 10:57
 * PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgTextBlock extends MmgObj {

    /**
     * The array where each word is represented as a text object, MmgFont.
     */
    private ArrayList<MmgFont> txt = null;

    /**
     * The font object, MmgFont, to use for rendering text.
     */
    private Font paint = null;

    /**
     * The color object, MmgColor, to use for rendering text.
     */
    private MmgColor cl = null;

    /**
     * The Y axis padding to use for rendering text.
     */
    private int paddingY = 4;

    /**
     * The X axis padding to used for rendering text.
     */
    private int paddingX = 4;

    /**
     * The line height to use for rendering text.
     */
    private int lineHeight = 16;

    /**
     * The height to use for rendering the background story.
     */
    private int height = 100;

    /**
     * The width to use for rendering the background story.
     */
    private int width = 100;

    /**
     * The number of pages found in parsing the background story.
     */
    private int pages = 0;

    /**
     * The number of words found in parsing the background story.
     */
    private int words = 0;

    /**
     * The starting size of the line array.
     */
    private int STARTING_LINE_COUNT = 20;

    /**
     * The starting size of the text array.
     */
    private int STARTING_TXT_COUNT = 100;

    /**
     * A text object, MmgFont, representation of each display line on the
     * current page.
     */
    private ArrayList<MmgFont> lines = null;

    /**
     * Rendering variable.
     */
    private int dLen = 0;

    /**
     * Rendering variable.
     */
    private int dI = 0;

    /**
     * Rendering variable.
     */
    private MmgFont dTmp = null;

    public static String NEW_LINE = "[b]";
    public static boolean SHOW_CONTROL_BGROUND_STORY_BOUNDING_BOX = false;

    /**
     * Generic constructor. Sets the color and creates a clean lines, and text
     * data object.
     */
    @SuppressWarnings({"OverridableMethodCallInConstructor", "Convert2Diamond"})
    public MmgTextBlock() {
        lines = new ArrayList<MmgFont>(STARTING_LINE_COUNT);
        txt = new ArrayList<MmgFont>(STARTING_TXT_COUNT);
        SetColor(MmgColor.GetBlack());
    }

    public void Reset() {
        lines = new ArrayList<MmgFont>(STARTING_LINE_COUNT);
        txt = new ArrayList<MmgFont>(STARTING_TXT_COUNT);
    }
    
    /**
     * Gets the number of pages the story takes up. The number of lines is
     * determined after parsing the story text.
     *
     * @return The number of pages the story takes up.
     */
    public int GetPages() {
        return pages;
    }

    /**
     * Sets the number of pages the story takes up. This method should not be
     * used with the default behavior. The number of lines is determined after
     * parsing the story text.
     *
     * @param p The number of pages in the story.
     */
    public void SetPages(int p) {
        pages = p;
    }

    /**
     * Gets the text object, MmgFont, at the given index of the array.
     *
     * @param i The index of the entry in the text array.
     * @return Returns the text object, MmgFont, at the given index.
     */
    public MmgFont GetText(int i) {
        if (i < txt.size()) {
            return txt.get(i);
        } else {
            return null;
        }
    }

    /**
     * Sets the text object, MmgFont, at the given index of the array. This
     * method should not be used with the default behavior.
     *
     * @param i The index of the entry to replace in the text array.
     * @param f The text object, MmgFont, to set in the text array.
     */
    public void SetText(int i, MmgFont f) {
        if (i < txt.size()) {
            txt.set(i, f);
        }
    }

    /**
     * Gets the Font object used to render the background story.
     *
     * @return The font object used to render the story.
     */
    public Font GetSpriteFont() {
        return paint;
    }

    /**
     * Sets the Font object used to render the background story. You may have to
     * re-parse the story text if you change the font used to render it.
     *
     * @param p Sets the Font object used to render the story.
     */
    public void SetSpriteFont(Font p) {
        paint = p;
        int len = txt.size();
        for (int i = 0; i < len; i++) {
            //txt.get(i).SetSpriteFont(paint);
            txt.get(i).SetFont(paint);
        }
    }

    /**
     * Sets the color object, MmgColor, of the entire background story text.
     *
     * @param Cl The color object, MmGColor, to apply to the story.
     */
    public void SetColor(MmgColor Cl) {
        cl = Cl;
        int len = txt.size();
        for (int i = 0; i < len; i++) {
            txt.get(i).SetMmgColor(cl);
        }
    }

    /**
     * Gets the color object, MmgColor, of the background story.
     *
     * @return
     */
    public MmgColor GetColor() {
        return cl;
    }

    /**
     * Gets the Y axis padding value;
     *
     * @return The Y axis padding value.
     */
    public int GetPaddingY() {
        return paddingY;
    }

    /**
     * Sets the Y axis padding value.
     *
     * @param p The Y axis padding value.
     */
    public void SetPaddingY(int p) {
        paddingY = p;
    }

    /**
     * Gets the X axis padding value.
     *
     * @return The X axis padding value.
     */
    public int GetPaddingX() {
        return paddingX;
    }

    /**
     * Sets the X axis padding value.
     *
     * @param p The X axis padding value.
     */
    public void SetPaddingX(int p) {
        paddingX = p;
    }

    /**
     * Gets the line height in pixels used in the display calculation of the
     * background story.
     *
     * @return The line height in pixels.
     */
    public int GetLineHeight() {
        return lineHeight;
    }

    /**
     * Sets the line height in pixels used in the display calculation of the
     * background story.
     *
     * @param l The line height in pixels.
     */
    public void SetLineHeight(int l) {
        lineHeight = l;
    }

    /**
     * Gets the height of the background story object.
     *
     * @return The height of the background story object.
     */
    @Override
    public int GetHeight() {
        return height;
    }

    /**
     * Sets the height of the background story object. You may have to re-parse
     * the background story if you change the display dimensions.
     *
     * @param h The height of the background story object.
     */
    @Override
    public void SetHeight(int h) {
        height = h;
    }

    /**
     * Gets the width of the background story object.
     *
     * @return The width of the background story object.
     */
    @Override
    public int GetWidth() {
        return width;
    }

    /**
     * Sets the width of the background story object. You may have to re-parse
     * the background story if you change the display dimensions.
     *
     * @param w The width of the background story object.
     */
    @Override
    public void SetWidth(int w) {
        width = w;
    }

    /**
     * Returns the number of lines that can be displayed in the background story
     * box.
     *
     * @return The number of lines that can be displayed in the display box.
     */
    public int GetLinesInBox() {
        return (GetHeight() / (GetLineHeight()));
    }

    /**
     * Prepares the lines that represent the display box with blank text
     * objects, MmgFont.
     *
     * @param len The number of line objects to prep for text display, usually
     * is the number of line in a page.
     */
    public void PrepLinesInBox(int len) {
        for (int i = 0; i < len; i++) {
            txt.add(new MmgFont());
        }
    }

    /**
     * Sets the position of this object in its owner's display space.
     *
     * @param vec A position coordinate, MmgVector2.
     */
    @Override
    public void SetPosition(MmgVector2 vec) {
        super.SetPosition(vec.Clone());
        int len = txt.size();
        for (int i = 0; i < len; i++) {
            MmgFont tmp = txt.get(i);
            tmp.GetPosition().SetX(vec.GetX() + paddingX);
            tmp.GetPosition().SetY(vec.GetY() + (lineHeight / 2) + paddingY + (lineHeight * i));
            txt.set(i, tmp);
        }
    }

    /**
     * Gets the number of pages needed to display the background story text.
     *
     * @return The number of display pages.
     */
    public int GetPageCount() {
        return pages;
    }

    /**
     * Gets the number of text lines that are displayed on each page.
     *
     * @return The number of lines that are displayed on each page.
     */
    public int GetLineCount() {
        return txt.size();
    }

    public int GetUsedLineCount() {
        int ret = 0;
        int len = txt.size();
        for (int i = 0; i < len; i++) {
            if (txt.get(i) != null && txt.get(i).GetText().trim().equals("") == false) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * Gets the number of words that are in the background story.
     *
     * @return The number of words in the background story.
     */
    public int GetWordCount() {
        return words;
    }

    /**
     * Prepares the text for rendering for the given page index.
     *
     * @param page The page index to render text for.
     */
    @SuppressWarnings("UnusedAssignment")
    public void PrepPage(int page) {
        int len = GetLineCount();

        if (page >= pages) {
            //do nothing
            for (int i = 0; i < len; i++) {
                txt.get(i).SetText("");
            }

        } else {
            if (lines != null) {
                int i = 0;
                MmgFont tmp = null;

                for (int j = 0; j < len; j++) {
                    i = (page * GetLineCount()) + j;
                    if (i >= 0 && i < lines.size()) {
                        tmp = (MmgFont) lines.get(i);
                        if (tmp != null) {
                            txt.set(j, (MmgFont) tmp.Clone());

                        } else {
                            txt.get(j).SetText("");

                        }
                    } else {
                        txt.get(j).SetText("");

                    }
                }
            }
        }
    }

    /**
     * Parses and prepares the text for display in a paged view.
     *
     * @param text The text to parse as the background story.
     * @param typeFace The Font to use to render the text.
     * @param fontSize The size of the font used to parse the text.
     * @param width The width to use as the maximum width for one line.
     */
    @SuppressWarnings("UnusedAssignment")
    public void PrepTextSplit(String text, Font typeFace, int fontSize, int width) {
        text = text.replace(" " + MmgTextBlock.NEW_LINE, MmgTextBlock.NEW_LINE);
        text = text.replace("  " + MmgTextBlock.NEW_LINE, MmgTextBlock.NEW_LINE);
        text = text.replace(MmgTextBlock.NEW_LINE, " " + MmgTextBlock.NEW_LINE);
        String[] tokens = text.split(" ");
        int tokenPos = 0;
        int prevTokenPos = 0;
        String str = "";
        String prevStr = "";
        MmgFont desc = null;
        boolean added = false;
        lines = new ArrayList(STARTING_LINE_COUNT);

        while (tokenPos < tokens.length) {
            desc = null;
            desc = new MmgFont(typeFace);
            desc.SetFontSize(fontSize);
            desc.SetText("");
            str = "";
            prevStr = "";
            added = false;

            while (desc.GetWidth() < width && tokenPos < tokens.length) {                                
                if ((tokens[tokenPos] + "").equals(MmgTextBlock.NEW_LINE) == true) {
                    //consume and move to the next line
                    desc.SetText(str);
                    lines.add(desc);
                    tokenPos++;
                    added = true;
                    break;
                } else {
                    prevStr = str;
                    str += tokens[tokenPos] + " ";

                    prevTokenPos = tokenPos;
                    tokenPos += 1;
                    desc.SetText(str);

                    if (desc.GetWidth() > width) {
                        tokenPos = prevTokenPos;
                        str = prevStr;
                        desc.SetText(str);
                        lines.add(desc);
                        added = true;
                        break;
                    }
                }
            }
            
            if(added == false) {
                lines.add(desc);
            }
        }

        pages = (lines.size() / GetLineCount());
        if (lines.size() % GetLineCount() != 0) {
            pages++;
        }
        words = tokens.length;
    }

    /**
     * Draws this object using the given pen, MmgPen.
     *
     * @param p The pen to use to draw this object, MmgPen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            if (MmgTextBlock.SHOW_CONTROL_BGROUND_STORY_BOUNDING_BOX == true) {
                Color c = p.GetGraphics().getColor();
                p.GetGraphics().setColor(Color.red);
                p.DrawRect(this);
                p.GetGraphics().setColor(c);
            }

            dLen = GetLineCount();
            //TyreDatGameUtils.wr("Text Lines: " + dLen);
            for (dI = 0; dI < dLen; dI++) {
                dTmp = txt.get(dI);
                if (dTmp != null && dTmp.GetIsVisible() == true) {
                    p.DrawText(dTmp);
                    //TyreDatGameUtils.wr(dI + ": " + dTmp.GetText() + ", " + dTmp.GetPosition().ToString());
                }
            }

        } else {
            //do nothing

        }
    }
}
