package musta.antlr4;

import musta.LogsLexer;
import musta.LogsParser;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class Tests {
	@Test
	public void whenLogContainsOneErrorLogEntryThenOneErrorIsReturned()
			throws Exception {
		ParseTreeWalker walker = new ParseTreeWalker();
		String logLine ="2018-05-05 14:20:24 ERROR Bad thing happened\n";
		String logLine_ ="2018-05-05 14:20:24 ERROR Bad thing happened\n";
		
		// instantiate the lexer, the parser, and the walker
		LogsLexer logsLexer = new LogsLexer(CharStreams.fromString(logLine+logLine_));
		LogsParser logsParser = new LogsParser(new BufferedTokenStream(logsLexer));
		
		LogListener listener = new LogListener();
		walker.walk(listener, logsParser.log());
		LogEntry entry = listener.getEntries().get(0);
		
		assertThat(entry.getLevel(), is(LogLevel.ERROR));
		assertThat(entry.getMessage(), is("Bad thing happened"));
		assertThat(entry.getTimestamp(), is(LocalDateTime.of(2018,5,5,14,20,24)));
	}
}
