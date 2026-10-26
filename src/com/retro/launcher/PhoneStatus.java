package com.retro.launcher;
import android.content.*;
import android.telephony.*;
import android.net.*;
import android.net.wifi.*;
import android.media.AudioManager;
import android.graphics.*;
final class PhoneStatus {
 final MainActivity a;TelephonyManager phone;int signal=-1;final PhoneStateListener listener;
 PhoneStatus(MainActivity owner){a=owner;listener=new PhoneStateListener(){public void onSignalStrengthsChanged(SignalStrength strength){signal=strength==null?-1:strength.getLevel();a.view.invalidate();}};try{phone=(TelephonyManager)a.getSystemService(Context.TELEPHONY_SERVICE);phone.listen(listener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);}catch(Exception e){android.util.Log.i("OrbitStatus","Signal unavailable",e);}}
 void stop(){try{if(phone!=null)phone.listen(listener,PhoneStateListener.LISTEN_NONE);}catch(Exception ignored){}}
 void draw(MainActivity.Orbit v){Canvas c=v.c;Paint p=v.p;int white=v.white,dim=0xff526780;boolean airplane=android.provider.Settings.Global.getInt(a.getContentResolver(),"airplane_mode_on",0)==1;boolean noSim=false;try{noSim=phone!=null&&phone.getSimState()==TelephonyManager.SIM_STATE_ABSENT;}catch(Exception ignored){}if(airplane)v.text("AIR",7,13,8,white);else{for(int i=0;i<4;i++)v.rect(8+i*4,15-(i+1)*2.5f,3,(i+1)*2.5f,!noSim&&signal>i?white:dim);if(noSim||signal<0)v.text(noSim?"×":"?",26,14,8,white);}
 try{ConnectivityManager cm=(ConnectivityManager)a.getSystemService(Context.CONNECTIVITY_SERVICE);NetworkInfo net=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);if(net!=null&&net.isConnected()){WifiManager wifi=(WifiManager)a.getApplicationContext().getSystemService(Context.WIFI_SERVICE);int level=WifiManager.calculateSignalLevel(wifi.getConnectionInfo().getRssi(),4);v.fill(white);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.4f);for(int k=0;k<=level;k++)c.drawArc(42-k*2,12-k*2,46+k*2,16+k*2,225,90,false,p);p.setStyle(Paint.Style.FILL);v.rect(43,14,2,2,white);}}catch(Exception ignored){}
 try{android.bluetooth.BluetoothAdapter bt=android.bluetooth.BluetoothAdapter.getDefaultAdapter();if(bt!=null&&bt.isEnabled())v.text("B",64,14,9,white);}catch(Exception ignored){}
 AudioManager audio=(AudioManager)a.getSystemService(Context.AUDIO_SERVICE);int mode=audio.getRingerMode();SoundPanel.glyph(c,82,2,17,mode);
 Intent b=a.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));int battery=-1;boolean charging=false;if(b!=null){battery=b.getIntExtra("level",-1)*100/Math.max(1,b.getIntExtra("scale",100));charging=b.getIntExtra("plugged",0)!=0;}v.fill(white);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1);c.drawRect(185,6,202,15,p);p.setStyle(Paint.Style.FILL);v.rect(202,9,2,3,white);if(battery>=0)v.rect(187,8,13*battery/100f,5,white);if(charging)v.text("+",177,13,8,white);v.text(battery<0?"—":battery+"%",208,13,8,white);
 }
}
