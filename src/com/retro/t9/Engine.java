package com.retro.t9;
import java.util.*;
public final class Engine {
 public int mode=0,caseMode=0,choice=0;public String code="",literal="",punct="";public List<String> candidates=new ArrayList<>();public final Map<String,Integer> learned=new HashMap<>();public Lexicon lexicon=new Lexicon();int lastDigit=-1,tapIndex;long lastTime;
 public static final String[] KEYS={" ",".,?!'\"-@:;()/","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
 public boolean composing(){return !code.isEmpty()||!literal.isEmpty()||!punct.isEmpty();}
 public void reset(){code="";literal="";punct="";choice=0;lastDigit=-1;lastTime=0;candidates.clear();}
 public void digit(int digit,long time){if(mode==2)return;if(mode==0){code+=digit;refresh();}else{String chars=KEYS[digit];if(lastDigit==digit&&time-lastTime<800&&!literal.isEmpty()){tapIndex=(tapIndex+1)%chars.length();literal=literal.substring(0,literal.length()-1)+chars.charAt(tapIndex);}else{tapIndex=0;literal+=chars.charAt(0);}lastDigit=digit;lastTime=time;candidates=new ArrayList<>(Collections.singletonList(literal));choice=0;}}
 public void refresh(){choice=0;candidates=code.isEmpty()?new ArrayList<>():lexicon.suggest(code,learned);}
 public void alternative(int delta){if(!punct.isEmpty()){String chars=KEYS[1];int n=chars.indexOf(punct);punct=String.valueOf(chars.charAt(Math.floorMod(n+delta,chars.length())));}else if(!candidates.isEmpty())choice=Math.floorMod(choice+delta,candidates.size());}
 public void punctuation(){String chars=KEYS[1];punct=String.valueOf(chars.charAt(punct.isEmpty()?0:(chars.indexOf(punct)+1)%chars.length()));}
 public String raw(){if(!punct.isEmpty())return punct;return candidates.isEmpty()?literal:candidates.get(Math.min(choice,candidates.size()-1));}
 public String display(){String s=raw();if(s.isEmpty()||!punct.isEmpty())return s;return caseMode==2?s.toUpperCase(Locale.ROOT):caseMode==1?Character.toUpperCase(s.charAt(0))+s.substring(1):s.equals("i")?"I":s;}
 public void delete(){if(!punct.isEmpty()){punct="";return;}if(mode==0&&!code.isEmpty()){code=code.substring(0,code.length()-1);refresh();}else if(!literal.isEmpty()){literal=literal.substring(0,literal.length()-1);candidates=new ArrayList<>(Collections.singletonList(literal));choice=0;lastDigit=-1;}}
}
