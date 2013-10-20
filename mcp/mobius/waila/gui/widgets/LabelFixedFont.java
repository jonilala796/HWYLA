package mcp.mobius.waila.gui.widgets;

import mcp.mobius.waila.gui.events.Signal;
import mcp.mobius.waila.gui.events.Slot;
import mcp.mobius.waila.gui.interfaces.IWidget;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.util.Point;

public class LabelFixedFont extends WidgetBase {

	protected String text = "";
	protected int    color;

	public LabelFixedFont(IWidget parent, String text){
		super(parent);
		this.setText(text);
		this.color = 0xFFFFFF;
	}		
	
	public LabelFixedFont(IWidget parent, String text, int color){
		super(parent);
		this.setText(text);
		this.color = color;
	}	
	
	@Override
	public void setGeometry(WidgetGeometry geom){
		this.geom = geom;
		this.updateGeometry();
	}
	
	private void setText(String text){
		this.text = text;
		this.updateGeometry();
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	private void updateGeometry(){
		if (this.geom == null)
			this.geom = new WidgetGeometry(0,0,50,50, false, false);
		
		this.geom = new WidgetGeometry(this.geom.x, this.geom.y, this.mc.fontRenderer.getStringWidth(this.text), 8, this.geom.fracPosX, this.geom.fracPosY, false, false, this.geom.alignX, this.geom.alignY);
	}
	
	@Override
	public void draw(Point pos) {
		this.mc.fontRenderer.drawString(this.text, pos.getX(), pos.getY(), this.color);
	}

	@Override
	public void onWidgetEvent(IWidget srcwidget, Signal signal,	Slot slot, Object... params) {
		switch(slot){
		case SET_VALUE:
			this.setText(String.valueOf(params[0]));
			break;
		default:
			break;
		}
	}		
}