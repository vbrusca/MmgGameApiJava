package net.middlemind.MmgGameApiJava.MmgBase;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/16/2020
 */
public class MmgScrollVert extends MmgObj {
    private MmgObj viewPort = null;
    private MmgRect viewPortRect = null;    
    private MmgObj scrollPane = null;
    private MmgRect scrollPaneRect = null;
        
    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
    }

    public MmgObj GetViewPort() {
        return viewPort;
    }

    public void SetViewPort(MmgObj ViewPort) {
        viewPort = ViewPort;
    }

    public MmgObj GetScrollPane() {
        return scrollPane;
    }

    public void SetScrollPane(MmgObj ScrollPane) {
        scrollPane = ScrollPane;
    }

    public MmgRect GetViewPortRect() {
        return viewPortRect;
    }

    public void SetViewPortRect(MmgRect ViewPortRect) {
        viewPortRect = ViewPortRect;
    }

    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    public void SetScrollPaneRect(MmgRect ScrollPaneRect) {
        scrollPaneRect = ScrollPaneRect;
    }
    
    
}
