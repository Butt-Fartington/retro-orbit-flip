package com.retro.launcher;
import android.app.*;
import android.content.*;
import android.media.AudioManager;
final class SoundModes {
 private SoundModes(){}
 static void cycle(MainActivity a){AudioManager audio=(AudioManager)a.getSystemService(Context.AUDIO_SERVICE);int mode=audio.getRingerMode();int next=mode==AudioManager.RINGER_MODE_NORMAL?AudioManager.RINGER_MODE_VIBRATE:mode==AudioManager.RINGER_MODE_VIBRATE?AudioManager.RINGER_MODE_SILENT:AudioManager.RINGER_MODE_NORMAL;try{audio.setRingerMode(next);a.view.invalidate();SoundPanel.show(a,false);}catch(SecurityException e){NotificationManager notifications=(NotificationManager)a.getSystemService(Context.NOTIFICATION_SERVICE);if(!notifications.isNotificationPolicyAccessGranted())a.features.show(new AlertDialog.Builder(a).setTitle("Sound-mode access").setMessage("Android requires Do Not Disturb access to switch into or out of Silent mode. Enable Retro Orbit to allow the side key to cycle Sound, Vibrate and Silent.").setPositiveButton("Set up",(d,n)->a.system(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)).setNegativeButton("Back",null).create());else a.features.toast("The phone did not allow changing sound mode");}}
}
