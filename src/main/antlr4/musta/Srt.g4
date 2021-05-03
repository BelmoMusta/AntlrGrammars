grammar Srt;
fragment DIGIT : [0-9];
fragment TWODIGIT : DIGIT DIGIT;

NUMBER : DIGIT+;
DD : DIGIT DIGIT;
DDD : DD DIGIT;
CARRIAGE_RETURN : '\r'? '\n' | '\r';
TIME : DD':'DD':'DD','DDD;
EEEUH : '\f?' -> skip;
ARROW : ' --> ';
EMPTY_LINE: CARRIAGE_RETURN+;
STRING :  (.)+? ;

sequenceNumber:NUMBER;
startTime : TIME;
endTime : TIME;
text: (STRING|NUMBER|CARRIAGE_RETURN)+;

sequence: sequenceNumber CARRIAGE_RETURN
          startTime ARROW endTime CARRIAGE_RETURN
          text
          EMPTY_LINE?;

srt : sequence + | <EOF>;