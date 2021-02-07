package musta.antlr4;

import musta.LogsBaseListener;
import musta.LogsParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogListener extends LogsBaseListener {
	private List<LogEntry> entries = new ArrayList<>();
	private LogEntry current;
	
	@Override
	public void enterEntry(LogsParser.EntryContext ctx) {
		this.current = new LogEntry();
	}
	
	@Override
	public void enterTimestamp(LogsParser.TimestampContext ctx) {
		this.current.setTimestamp(
				LocalDateTime.parse(ctx.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
	
	@Override
	public void enterMessage(LogsParser.MessageContext ctx) {
		this.current.setMessage(ctx.getText());
	}
	
	@Override
	public void enterLevel(LogsParser.LevelContext ctx) {
		this.current.setLevel(LogLevel.valueOf(ctx.getText()));
	}
	
	@Override
	public void exitEntry(LogsParser.EntryContext ctx) {
		this.entries.add(this.current);
	}
	
	public List<LogEntry> getEntries() {
		return entries;
	}
	
	public LogEntry getCurrent() {
		return current;
	}
}
