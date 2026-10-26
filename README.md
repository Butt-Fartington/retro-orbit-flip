# Retro Orbit Flip Phone

Current version: **1.2-kyf** (code 39), minimum API 26, target API 28. Installed and visually checked on Kyocera GRATINA KYF42, Android 10.

Combined launcher and Orbit T9 keyboard. Includes millennium-inspired chrome/glass app symbols, Rajdhani typography, custom orb photos and launch effect, weather, notification previews, and keypad navigation.

## KYF42 controls

- Book: Apps / contextual select.
- Front Camera: Options.
- Clear: Back.
- Bottom F2: Home globally through the enabled key service.
- Bottom F3: recent apps; hold for the firmware-specific cursor toggle.
- Side key: cycle sound modes; hold for the custom volume panel.
- D-pad and center: navigate/select. App drawer numbers 1–9 open items; star/hash change pages.

Enable Retro Orbit key controls in Accessibility, optional notification access, and Orbit T9 (Retro Orbit) in keyboard settings. Android requires the user to enable these. Existing preferences and personal words are retained by an in-place update signed with the original key.

The cursor adapter is specific to verified KYF42 firmware. This branch retains the hardware behaviors and bundled keyboard; touchscreen gestures and unlock effects are not ported here. Latest style update was visually checked; original physical mappings were confirmed in prior device sessions.

Dictionary license and credits are included in assets. Personal learned words live on the device, not in this repository.

## Build

Set `ANDROID_HOME` to your Android SDK (platform 35 and build-tools 36.0.0). A JDK, zip and standard shell tools are required. Run `./build.sh`; the signed APK is `build/retro-orbit.apk`.

Supply the existing private signing key through `ORBIT_KEYSTORE`, or place it at `signing/retro-orbit.keystore`. Keys and build products are excluded from Git. The existing personal certificate uses the historical Android Debug name; APK debugging is disabled. Keep the original key to install future updates without clearing app data. The build script retains the existing signing configuration.

Both versions currently use `com.retro.launcher` and the same signing identity. These are independent source repositories, but cannot be installed side by side on one device as separate packages. Use the matching version for the device.

## Assets

Includes the user-supplied Chrome Blue artwork and generated orb graphics. Public redistribution rights for supplied artwork have not been established; no blanket open-source license is granted. Rajdhani font has its included SIL Open Font License in `assets/fonts/OFL.txt`.

`docs/DEVELOPMENT_HISTORY.md` and `VALIDATION.md` contain historical notes; they are not a claim that every old behavior remains current.
