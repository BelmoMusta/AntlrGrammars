package musta.antlr4;

import musta.SrtBaseListener;
import musta.SrtParser;

public class SrtListenerImpl extends SrtBaseListener {
	@Override
	public void enterSequence(SrtParser.SequenceContext ctx) {
	
	}
	
	@Override
	public void enterSrt(SrtParser.SrtContext ctx) {
		System.out.println(ctx.getText());
		
	}
}
