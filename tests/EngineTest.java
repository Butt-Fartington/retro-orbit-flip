import com.retro.t9.*;import java.io.*;import java.util.*;
public class EngineTest {
 static void check(boolean ok,String message){if(!ok)throw new AssertionError(message);}
 public static void main(String[] args)throws Exception{Lexicon lex=new Lexicon();lex.load(new FileInputStream(args[0]));Engine e=new Engine();e.lexicon=lex;
 check(Lexicon.digits("hello").equals("43556"),"hello signature");check(Lexicon.digits("don't").equals("3668"),"apostrophe signature");
 for(char c:"43556".toCharArray())e.digit(c-'0',1000);check(e.candidates.contains("hello"),"hello candidate");int old=e.choice;e.alternative(-1);check(e.choice==e.candidates.size()-1,"reverse wrap");e.alternative(1);check(e.choice==old,"forward wrap");
 e.delete();check(e.code.equals("4355"),"delete one");e.reset();e.mode=1;e.digit(2,1000);e.digit(2,1100);check(e.display().equals("b"),"multi tap b");e.digit(2,2100);check(e.display().equals("ba"),"multi tap timeout");e.delete();check(e.display().equals("b"),"multi tap delete");
 e.reset();e.punctuation();check(e.display().equals("."),"punctuation first");e.punctuation();check(e.display().equals(","),"punctuation cycle");e.alternative(-1);check(e.display().equals("."),"punctuation reverse");
 e.reset();e.mode=0;e.learned.put("gello",10);for(char c:"43556".toCharArray())e.digit(c-'0',0);check(e.candidates.get(0).equals("gello"),"personal priority");
 for(int len=1;len<25;len++){e.reset();for(int n=0;n<len;n++)e.digit(9,n);check(!e.candidates.isEmpty(),"fallback candidates");for(int n=0;n<len;n++)e.delete();check(!e.composing(),"delete to empty");}
 e.reset();e.mode=1;e.caseMode=2;e.digit(2,0);check(e.display().equals("A"),"uppercase");System.out.println("PASS: dictionary, collisions, candidate wrap, deletion, multi-tap timing, punctuation, personal ranking, fallback and casing");}
}
