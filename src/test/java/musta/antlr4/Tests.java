package musta.antlr4;

import musta.HeaderLexer;
import musta.HeaderParser;
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
		String logLine = "2018-05-05 14:20:24 ERROR Bad thing happened\n";
		String logLine_ = "2018-05-05 14:20:24 ERROR Bad thing happened\n";
		
		// instantiate the lexer, the parser, and the walker
		LogsLexer logsLexer = new LogsLexer(CharStreams.fromString(logLine + logLine_));
		LogsParser logsParser = new LogsParser(new BufferedTokenStream(logsLexer));
		
		LogListener listener = new LogListener();
		walker.walk(listener, logsParser.log());
		LogEntry entry = listener.getEntries().get(0);
		
		assertThat(entry.getLevel(), is(LogLevel.ERROR));
		assertThat(entry.getMessage(), is("Bad thing happened"));
		assertThat(entry.getTimestamp(), is(LocalDateTime.of(2018, 5, 5, 14, 20, 24)));
	}
	
	@Test
	public void testStackTrace() {
		ParseTreeWalker walker = new ParseTreeWalker();
		String logLine = "ERROR lol \n"+
				"\tat org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException" +
				"(HibernateJpaDialect.java:259)\n" +
				"\tat org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible" +
				"(HibernateJpaDialect.java:225)\n" +
				"\tat org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:540)\n" +
				"\tat org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit" +
				"(AbstractPlatformTransactionManager.java:746)\n" +
				"\tat org.springframework.transaction.support.AbstractPlatformTransactionManager.commit" +
				"(AbstractPlatformTransactionManager.java:714)\n" +
				"\tat org.springframework.transaction.interceptor.TransactionAspectSupport" +
				".commitTransactionAfterReturning(TransactionAspectSupport.java:532)\n" +
				"\tat org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction" +
				"(TransactionAspectSupport.java:304)\n" +
				"\tat org.springframework.transaction.interceptor.TransactionInterceptor.invoke" +
				"(TransactionInterceptor" +
				".java:98)\n" +
				"\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed" +
				"(ReflectiveMethodInvocation" +
				".java:185)\n" +
				"\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept" +
				"(CglibAopProxy.java:688)\n" +
				"\tat com.apicil.cosy.conformite.acte.service.impl.demande.operation" +
				".DemandeOperationConformiteServiceImpl$$EnhancerBySpringCGLIB$$9bd4a780.mettreAJourConformite" +
				"(<generated>)\n" +
				"\tat com.apicil.cosy.conformite.acte.service.impl.ConformiteActeServiceCommunImpl" +
				".mettreAJourConformite(ConformiteActeServiceCommunImpl.java:117)\n" +
				"\tat com.apicil.cosy.conformite.acte.service.impl" +
				".ConformiteActeServiceCommunImpl$$FastClassBySpringCGLIB$$5c9fa726.invoke(<generated>)\n" +
				"\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n" +
				"\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept" +
				"(CglibAopProxy.java:684)\n" +
				"\tat com.apicil.cosy.conformite.acte.service.impl" +
				".ConformiteActeServiceCommunImpl$$EnhancerBySpringCGLIB$$e4315a2e.mettreAJourConformite" +
				"(<generated>)" +
				"\n" +
				"\tat com.apicil.cosy.conformite.controller.api.conformite.internal.AbstractConformiteController" +
				".sauvgarderConformiteBrouillon(AbstractConformiteController.java:55)\n" +
				"\tat com.apicil.cosy.conformite.controller.api.conformite.internal.ConformiteOperationController" +
				".sauvgarderConformiteBrouillon(ConformiteOperationController.java:52)\n" +
				"\tat com.apicil.cosy.conformite.controller.api.conformite.internal" +
				".ConformiteOperationController$$FastClassBySpringCGLIB$$15fb1565.invoke(<generated>)\n" +
				"\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint" +
				"(CglibAopProxy.java:746)";
		
		
		// instantiate the lexer, the parser, and the walker
		HeaderLexer headerLexer = new HeaderLexer(CharStreams.fromString(logLine));
		HeaderParser headerParser = new HeaderParser(new BufferedTokenStream(headerLexer));
		
		MHeaderListener listener = new MHeaderListener();
		walker.walk(listener, headerParser.stackTrace());
		for (String entry : listener.getEntries()) {
			System.out.println(entry);
		}
	}
}
