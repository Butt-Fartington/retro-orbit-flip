package com.retro.launcher;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
public class Alerts extends NotificationListenerService {
 public static Alerts current;
 void update(){MainActivity a=MainActivity.foreground;if(a!=null)a.runOnUiThread(()->a.view.invalidate());}
 public void onNotificationPosted(StatusBarNotification n){update();}
 public void onNotificationRemoved(StatusBarNotification n){update();}
 public void onListenerConnected(){current=this;update();}
 public void onListenerDisconnected(){current=null;}
 public void onDestroy(){current=null;super.onDestroy();}
 public static StatusBarNotification[] items(){try{return current==null?new StatusBarNotification[0]:current.getActiveNotifications();}catch(Exception e){return new StatusBarNotification[0];}}
}
