package musta.antlr4;

import musta.HeaderParser;

import java.util.ArrayList;
import java.util.List;

public class MHeaderListener extends musta.HeaderBaseListener {
	private List<String> entries = new ArrayList<>();
	private LogEntry current;
 
	
	public List<String> getEntries() {
		return entries;
	}
	
	@Override
	public void enterStackTrace(HeaderParser.StackTraceContext ctx) {
		super.enterStackTrace(ctx);
	}
	
	@Override
	public void enterStackTraceLine(HeaderParser.StackTraceLineContext ctx) {
		entries.add(ctx.getText());
	}
}
