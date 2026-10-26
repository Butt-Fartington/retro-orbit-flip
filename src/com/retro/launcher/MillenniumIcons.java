package com.retro.launcher;
import android.graphics.*;
/** Resolution-independent chrome-blue symbols, drawn in a 24-unit coordinate space. */
final class MillenniumIcons {
 static String forPackage(String s){switch(s){
 case "com.reddit.frontpage":return "reddit";case "com.openai.chatgpt":case "com.pocketpalai":return "ai";
 case "com.spotify.music":return "spotify";case "com.google.android.youtube":case "com.google.android.apps.youtube.music":case "org.videolan.vlc":case "org.jellyfin.mobile":return "video";
 case "com.android.deskclock":case "com.sec.android.app.clockpackage":case "com.google.android.deskclock":return "clock";
 case "com.google.android.apps.maps":case "com.waze":case "net.osmand.plus":return "maps";
 case "com.termux":case "com.termux.x11":case "com.ddm.qute":return "terminal";
 case "com.sec.android.app.myfiles":case "com.google.android.documentsui":case "com.google.android.apps.nbu.files":return "files";
 case "com.microsoft.office.outlook":case "com.google.android.gm":return "mail";
 case "com.whatsapp":case "com.discord":case "org.telegram.messenger":case "org.thoughtcrime.securesms":case "com.facebook.orca":return "chat";
 case "com.amazon.mshop.android.shopping":case "com.ebay.mobile":case "com.walmart.android":case "com.target.ui":return "shopping";
 case "com.google.android.keep":case "com.samsung.android.app.notes":case "com.microsoft.office.onenote":return "notes";
 default:return null;}}
 static int tint(String name){switch(name){case "reddit":return 0xffff8043;case "ai":return 0xff6de0b5;case "spotify":return 0xff63e88d;case "video":return 0xffff6584;case "maps":return 0xff62dfbe;case "terminal":return 0xff8fe96c;case "files":case "notes":return 0xffffcf65;case "chat":return 0xffb79aff;case "shopping":return 0xffffb469;default:return 0xff47a8df;}}
 static void draw(Canvas canvas,String name,float x,float y,float radius){
 Canvas c=canvas; c.save();c.translate(x,y);c.scale(radius/12,radius/12);
 Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);p.setStrokeWidth(1.8f);p.setStrokeCap(Paint.Cap.ROUND);p.setStrokeJoin(Paint.Join.ROUND);p.setStyle(Paint.Style.STROKE);
 p.setShader(new LinearGradient(0,-10,0,10,new int[]{0xffffffff,0xffb6eeff,tint(name),0xffe2faff},null,Shader.TileMode.CLAMP));
 Path path=new Path();
 if(name.equals("clock")){c.drawCircle(0,0,10,p);c.drawLine(0,0,0,-6,p);c.drawLine(0,0,5,3,p);}
 else if(name.equals("reddit")){c.drawOval(-10,-5,10,8,p);c.drawCircle(-5,0,1,p);c.drawCircle(5,0,1,p);c.drawArc(-5,-1,5,5,15,150,false,p);path.moveTo(0,-5);path.lineTo(2,-10);path.lineTo(7,-9);c.drawPath(path,p);c.drawCircle(9,-8,2,p);}
 else if(name.equals("ai")){for(int i=0;i<6;i++){c.save();c.rotate(i*60);c.drawRoundRect(-3,-10,5,3,4,4,p);c.restore();}c.drawCircle(0,0,2,p);}
 else if(name.equals("spotify")){c.drawCircle(0,0,10,p);for(int i=0;i<3;i++)c.drawArc(-7+i,-6+i*4,7-i,2+i*4,205,130,false,p);}
 else if(name.equals("video")){c.drawRoundRect(-11,-8,11,8,4,4,p);path.moveTo(-3,-4);path.lineTo(5,0);path.lineTo(-3,4);path.close();c.drawPath(path,p);}
 else if(name.equals("maps")){path.moveTo(0,10);path.cubicTo(-15,-4,-6,-12,0,-10);path.cubicTo(10,-11,12,-1,0,10);c.drawPath(path,p);c.drawCircle(0,-4,3,p);}
 else if(name.equals("terminal")){c.drawRoundRect(-11,-8,11,8,2,2,p);path.moveTo(-7,-4);path.lineTo(-3,0);path.lineTo(-7,4);c.drawPath(path,p);c.drawLine(1,4,7,4,p);}
 else if(name.equals("files")){path.moveTo(-10,-7);path.lineTo(-3,-7);path.lineTo(0,-4);path.lineTo(10,-4);path.lineTo(9,9);path.lineTo(-10,9);path.close();c.drawPath(path,p);c.drawLine(-8,0,8,0,p);}
 else if(name.equals("chat")){c.drawRoundRect(-10,-9,10,5,4,4,p);path.moveTo(-5,5);path.lineTo(-7,10);path.lineTo(2,5);c.drawPath(path,p);for(int i=-1;i<=1;i++)c.drawCircle(i*5,-2,.7f,p);}
 else if(name.equals("shopping")){path.moveTo(-11,-8);path.lineTo(-7,-8);path.lineTo(-4,5);path.lineTo(8,5);path.lineTo(11,-4);path.lineTo(-6,-4);c.drawPath(path,p);c.drawCircle(-2,9,1,p);c.drawCircle(7,9,1,p);}
 else if(name.equals("notes")){c.drawRoundRect(-8,-10,8,10,2,2,p);for(int i=0;i<3;i++)c.drawLine(-4,-4+i*5,4,-4+i*5,p);}
 else if(name.equals("phone")){path.moveTo(-7,-9);path.cubicTo(-13,-4,-4,12,5,9);path.lineTo(9,5);path.lineTo(4,1);path.lineTo(1,4);path.cubicTo(-2,3,-5,-1,-5,-3);path.lineTo(-2,-5);path.close();c.drawPath(path,p);}
 else if((name.equals("messages")||name.equals("mail"))){c.drawRoundRect(-10,-7,10,7,2,2,p);path.moveTo(-9,-5);path.lineTo(0,2);path.lineTo(9,-5);c.drawPath(path,p);}
 else if(name.equals("browser")){c.drawCircle(0,0,10,p);c.drawOval(-4,-10,4,10,p);c.drawLine(-9,-4,9,-4,p);c.drawLine(-9,4,9,4,p);}
 else if(name.equals("contacts")){c.drawCircle(0,-5,4,p);path.moveTo(-9,9);path.cubicTo(-9,-1,9,-1,9,9);path.close();c.drawPath(path,p);}
 else if(name.equals("camera")){c.drawRoundRect(-10,-6,10,8,2,2,p);c.drawCircle(1,1,4,p);path.moveTo(-6,-6);path.lineTo(-4,-9);path.lineTo(3,-9);path.lineTo(5,-6);c.drawPath(path,p);c.drawLine(-7,-2,-5,-2,p);}
 else if(name.equals("music")){path.moveTo(-4,6);path.lineTo(-4,-7);path.lineTo(7,-10);path.lineTo(7,3);c.drawPath(path,p);c.drawOval(-9,4,-3,9,p);c.drawOval(2,1,8,6,p);c.drawLine(-4,-3,7,-6,p);}
 else if(name.equals("gallery")){c.drawRoundRect(-10,-9,10,9,1,1,p);c.drawCircle(5,-4,2,p);path.moveTo(-9,6);path.lineTo(-3,-2);path.lineTo(2,4);path.lineTo(5,1);path.lineTo(10,7);c.drawPath(path,p);}
 else if(name.equals("calendar")){c.drawRoundRect(-9,-8,9,10,2,2,p);c.drawLine(-9,-2,9,-2,p);c.drawLine(-5,-10,-5,-5,p);c.drawLine(5,-10,5,-5,p);for(int i=0;i<6;i++)c.drawCircle(-5+(i%3)*5,2+(i/3)*4,.6f,p);}
 else {for(int i=0;i<12;i++){double a=i*Math.PI/6;float r=i%2==0?10:8;float xx=(float)Math.cos(a)*r,yy=(float)Math.sin(a)*r;if(i==0)path.moveTo(xx,yy);else path.lineTo(xx,yy);}path.close();c.drawPath(path,p);c.drawCircle(0,0,4,p);}
 c.restore();
 }
}
