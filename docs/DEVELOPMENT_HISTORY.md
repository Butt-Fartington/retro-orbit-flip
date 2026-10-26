# Retro Orbit 0.8 — native KYF42 launcher

A keypad-first Android launcher with a circular floating app wheel, chrome orb, app grid, optional weather and notifications. Source is a small native Java/Canvas Android project. The current theme approximates the concept artwork; it is not a finished reproduction.

## Controls

With “Retro Orbit F1 key” accessibility service enabled:

| Screen | F1 | F2 | F3 |
| --- | --- | --- | --- |
| Home | Apps | Notifications | Settings |
| Applications | Open | App options | Back |
| Notifications | Open / set up access | Open / dismiss options | Back |
| Settings and other lists | Select | Select | Back |
| Dialog | Activate focused control | Activate focused control | Close dialog |

D-pad center always activates the focused control. Home Up/Down rotates the wheel, Left/Right selects the right panels; the lower panel opens weather options. In native dialogs, D-pad navigates fields/buttons. On the app grid, 1–9 opens the corresponding visible app, * and # switch pages, and D-pad moves between entries. Home number keys open the dialer with a digit; they do not place a call. Clear/Back returns to the previous launcher screen. Call/End and non-function-key system shortcuts remain firmware-controlled.

F1–F3 physically report as APP_SWITCH, F11, F12 on this handset. The optional accessibility service filters these three keys only while Retro Orbit is the resumed activity; it requests no window-content retrieval. Other apps retain their normal keys. The phone may also have other key-mapping tools installed; conflicting user mappings may affect behavior.

## Functions

- Installed-app discovery, automatic package-change refresh, persistent favorites and order.
- Infinite wheel with fixed middle selection, optional animation, and no spokes.
- F2 app options: open, add/remove favorite, system app information, reorder favorites.
- Reorder: center picks up an entry, Up/Down moves it, center drops it.
- 12/24-hour clock, device-locale date, battery percentage.
- Chrome Blue, Amethyst and Silver themes; midnight gradient, orbit lines or plain-dark wallpaper.
- Original pixel icons for recognizable phone/message/camera/music/settings packages; installed icons otherwise.
- Manual city/postal-code weather search, matching-location chooser, Fahrenheit/Celsius, refresh and disable controls.
- Optional real notification list, open action, and dismissal of clearable alerts; ongoing alerts stay intact.
- Android settings, notification access, function-key setup and key diagnostics.
- Back navigation history and basic screen state saved for activity recreation.

## Weather

Enter your city or postal code under Settings → Weather → Choose city. Choose the intended result; postal codes are not globally unique. No GPS access is requested. The chosen search/location is sent to Open-Meteo. Updates occur at most hourly while the launcher is visible, with a 15-minute automatic retry backoff after failures. Manual refresh overrides the interval. No background polling when paused. Cached readings persist and are marked stale after two hours; offline/no-reading states are explicit. No secret API key is embedded.

Weather data by [Open-Meteo](https://open-meteo.com/), [CC BY 4.0](https://creativecommons.org/licenses/by/4.0/); location search based on GeoNames. Rounded temperatures and summarized condition labels are derived from the API. Free endpoint is for non-commercial use. Attribution is shown on home and under Weather → Data and attribution. API docs: https://open-meteo.com/en/docs and https://open-meteo.com/en/docs/geocoding-api .

The phone requires an active Wi-Fi/mobile connection and may display a Kyocera per-app data restriction prompt after an update. No weather values are invented when offline.

## Notifications

Enable Retro Orbit in Android notification-listener settings. This is separate from function-key accessibility access. Denying access leaves the launcher functional. Notification text stays on the device; the app does not upload it. F2 opens alert options including dismissal. System ongoing notifications cannot be dismissed. An alert with no content action displays a readable message instead of launching another app.

## Build

Requires JDK, Android SDK platform 35, build-tools 36.0.0, and zip. Set ANDROID_HOME for your SDK location, then run `./build.sh`. No downloaded libraries or Gradle dependencies. Output: build/retro-orbit-debug.apk. The build is debug signed, min API 26, target API 28 for initial Android 10 compatibility. Release targeting and conventional Gradle/lint setup remain future work.

The build generates a private debug signing key under build/; preserve it locally for subsequent in-place updates. Source archives omit build/ and private keys.

Carousel checks:

    mkdir -p build/test
    javac -d build/test src/com/retro/launcher/WheelMath.java tests/WheelMathTest.java
    java -cp build/test WheelMathTest

## Install / recover

Verify device identity with `adb devices -l`, then:

    adb -s PHONE_SERIAL install --no-incremental -r retro-orbit-0.6-debug.apk
    adb -s PHONE_SERIAL shell am start -W -n com.retro.launcher/.MainActivity

Opening the app does not change the default launcher. Keep existing launchers installed. Choose your home application through Android's normal chooser/settings when ready. Switch back to the previous home before uninstalling:

    adb -s PHONE_SERIAL uninstall com.retro.launcher

## Validation and limitations

Built and signature-verified using local SDK tools. Earlier builds installed and rendered on API 36.1 emulator and the physical Android 10 KYF42 (480×854, 240 dpi). User confirmed the physical wheel and F1/F2/F3 behavior. Version 0.6 is installed on that phone; checked settings, appearance dialogs, preference writes and error-free startup. Carousel tests cover 200,100 indexing cases.

Current live-weather and notification-delivery verification is recorded in VALIDATION.md. Do not infer network or notification success from build success. Screen-reader virtual-node navigation for Canvas controls, advanced battery/performance measurements, full firmware sleep/flip/End behavior, and release-grade accessibility remain unverified. Wallpapers are built-in procedural styles, not arbitrary gallery images. Icon mappings are curated heuristics and retain original icons for unrecognized apps.

## Chrome Blue pack integration (0.7)

User-supplied PNG/JPG pack is bundled under assets/chrome with its manifest and source notes. Nine circular icons are mapped by app identity/category; F2 App options → Choose icon permits an explicit per-app override or original icon. Header, menu rows, selected rows, soft-key buttons, selection corners, theme swatches, page indicators and Night Orbit wallpaper are integrated as individual components. Bitmaps are cached; wallpaper is sampled and center-cropped; circular icon clipping hides square source-background corners. Controls preserve their edge proportions. Adaptive icon copies are alternative source assets, not an additional screen. The concept sheet is reference-only: its extra icons, widgets and toggles are not separate usable assets and are not falsely represented as implemented graphics.

Home summary shows the most recent non-ongoing, non-group-summary alert with title, preview and count. Ongoing services remain in the full notification list. Long text is ellipsized. F2 still opens the full list. The count represents notification entries, not unread-message totals.

0.7 validation: built and signature checked; installed on physical KYF42; inspected home and notification-list screenshots; posted a local test alert and confirmed its title and preview appeared on home, then dismissed only that test alert. No AndroidRuntime errors observed. Existing favorites, weather and key mappings preserved. The supplied wallpaper is enabled on first upgrade; built-in wallpaper choices remain available.

## Reference home composition (0.8)

Home now uses an independently rendered chrome orb with an AI-generated St. Louis/Gateway Arch reflection, segmented live clock, live weather symbol, thin panel separators and orbital line background. Orb is a standalone transparent image; all app icons, times, weather readings, notifications and input targets remain live. Circular wheel geometry and existing F1/F2/F3 controls retained. Actual KYF42 screenshot inspected and no AndroidRuntime errors observed after installation. Skyline is an artistic reflection, not a geographically exact photograph.

## Orb revision 0.9

Replaced the warm skyline reflection with a cooler smoky chrome lens, faint skyline/Arch, restrained cyan illumination and scan lines. Generated using built-in imagegen; prior orb retained as a source alternative. Installed on physical KYF42, inspected screenshot and checked AndroidRuntime output without observed crashes.

## Custom orb and launch animation (0.10)

Settings → Orb photo and launch effect offers a system image picker, Restore St. Louis orb, launch-effect toggle and an explicit preview that does not open an app. A selected picture is downsampled to at most 1024 pixels on its longest edge, saved privately inside the app and center-cropped into the lens with tint, scan lines and chrome framing. No broad photo/storage permission or image upload is used. The picker grants access only to the chosen image. Current version uses a centered crop; manual crop positioning and EXIF-orientation correction are not yet implemented.

When launching from the home wheel, the selected icon shrinks into the orb, a short muted CRT shimmer appears, and the orb moves/zooms to cover the display before launching the selected app. Duration is about one second, followed by the destination app's normal startup. This does not pre-render or read the destination app's private content. App-grid launches remain immediate. Global animation Off or launch-effect Off skips the sequence; explicit Preview still runs. Back/F3 cancels before launch; repeated selection is ignored while active. Leaving the activity cancels the animation. The effect has restrained scan bands rather than a full-screen white flash.

Verified on KYF42: recorded the wheel effect and successful handoff into Alarm; inspected extracted video frames. Opened system picker with physical-key-compatible D-pad events, selected a known test JPG from Downloads, verified private saved image and rendered orb. Restored original orb and removed only test files afterward. No AndroidRuntime errors observed in the smoke checks. Test video is launch-animation-test.mp4 in the deliverables. Timing and smoothness still benefit from user testing; no formal frame-time benchmark was run.

## Chunky interface pass (0.11)

Enlarged home orb radius from 59 to 68 logical pixels, tightened the wheel orbit from 108 to 102, and updated launch animation geometry to match. All icons now share the same native multilayer mechanical chrome housing and scan-lined dark disc. Pack glyphs use their inner artwork; other apps use smaller original artwork inside the same housing. Selection reticle uses explicit corners centered on the icon bounds rather than PNG padding.

Reworked app grid to fill usable height with larger circles; settings and notification lists use heavier beveled headers, bold monospaced labels, bright selected rows and thin unselected separators. Settings begin with Appearance, Wheel apps, Wallpaper, Clock, Weather and Notification access, followed by the other controls. Added graphical symbols and right-aligned setting values. Touch hit areas follow new grid/list geometry.

Built, signature-verified and installed on KYF42; inspected home, grid and settings screenshots and checked startup without observed AndroidRuntime errors. Appearance menu remains reachable after reordering. Existing favorites, weather, photo settings, launch-effect setting and physical key mappings retained.

## Open apps button (0.12)

Replaced the home Weather options action with Open apps. Weather configuration remains in Settings → Weather. Uses the enabled Retro Orbit accessibility service's GLOBAL_ACTION_RECENTS to open the KYF42's own Overview task manager. This is Android's task/recent-app view, not a promise that every listed app has a currently running process.

Use D-pad to select an app, center to return to it, and the phone's Menu key (top-left book key) → Remove from list to exit that selected task. System confirmation on this KYF42 says App exited. System background services can still be managed by Android. No custom inaccurate process list, root, app-wide force-stop or bulk closing is used.

Verified on phone: home action opened com.android.systemui.recents.AfpRecentsActivity; inspected individual Remove from list menu; removed only Retro Orbit's own task and observed App exited confirmation, then reopened launcher. Other apps were left untouched. Key Mapper remains disabled. Retro Orbit accessibility access was enabled by the user for this action. Source: https://developer.android.com/reference/android/accessibilityservice/AccessibilityService#GLOBAL_ACTION_RECENTS .

## Function-key sequence handling (0.13)

Accessibility filter claims the full function-key press/release sequence, including repeats, before dispatching the action on release. Ownership is retained even if activity focus changes between down and up. Canceled presses and stale owners do not launch actions. New presses outside Retro Orbit remain untouched. Activity fallback consumes function-key releases. Intended to prevent firmware shortcuts firing alongside Retro Orbit actions; physical F3 regression check requested from user after installation. Built/signature-verified and installed; accessibility service remains enabled.

## Phone settings shortcut (0.14)

Renamed Android settings to Phone settings and moved it to the first launcher-settings row. Opens the standard Android Settings action. Built, installed and verified on the KYF42: choosing it resumed com.android.settings.homepage.SettingsHomepageActivity. F3 sequence fix retained; user confirmed it working in 0.13.

## F3 cursor hold and time zone (0.15)

Time & time zone is second in Launcher settings and opens Android ACTION_DATE_SETTINGS. Verified on KYF42 DateTimeSettingsActivity. Manual zone selection requires disabling Automatic time zone in that system screen; installer does not alter time/zone settings.

F3 short presses retain Options/Back. Holding F3 for 650 ms while Retro Orbit is foreground calls the KYF42 vendor cursor controller (KcPointerManager.showPointer) and suppresses the short action on release. The cursor toggle uses a guarded vendor API and fails with a readable message on unsupported firmware. Other apps retain their native long-press handling. Build/signature/install verified; physical long-press confirmation requested from user.

## Native cursor adapter fix (0.16)

The 0.15 reflective KcPointerManager entry point was blocked by Android's hidden-API blacklist on this handset. Replaced it with a guarded KYF42 Binder adapter using the installed firmware's verified IKcPointerManager interface descriptor and transaction layout. Registers a native pointer-state listener and uses actual state for on/off requests. No hidden-API policy settings, root, privilege grants, or global security settings were changed. An explicit Toggle cursor item is now third in launcher settings as well as the F3 hold shortcut.

Built and installed. Verified Toggle cursor invoked the native arrow on the physical KYF42 with no OrbitCursor/AndroidRuntime errors. Physical F3 on/off regression check requested from user. The vendor adapter is firmware-specific and may fail gracefully on other devices.

## App launch handoff fix (1.0.1)

Retains the zoomed orb as the launcher’s last frame while Android starts the selected application; clears it when returning to the launcher. Cancels pending launches if the activity is interrupted. Removes the additional Android window animation. Preserves the original one-second absorption/flicker/zoom effect.

Verified on the KYF42 with an on-device screen recording and extracted frames: selected Alarm launched, with no home-screen reset between the zoom and the app. The Clock app’s own blank startup surface remains visible briefly before its content, so this does not claim elimination of cold-start latency in other applications. Return to the launcher works without leaving a frozen transition. Default home and permissions retained.

## Key and status cleanup (1.0.2)

Verified physical scan sequence: Book = matrix F1 → Android KEYCODE_F1; Camera = matrix F2 → Android KEYCODE_F2; Clear = KEY_CLEAR → Android BACK. Book acts as Apps on home and Select elsewhere. Camera opens launcher settings on home, app/alert options on relevant lists and a context options dialog elsewhere. Clear is Back, including returning to wheel focus at home. Short bottom F3 opens system Overview; holding F3 retains the native cursor toggle. Previous bottom F1/F2 shortcuts are retained as aliases. Entire press/release sequences are consumed while Retro Orbit is foreground, preventing stock shortcuts from firing alongside them. Other apps keep normal hardware behavior.

Removed the Open apps home action and expanded notification previews into the free space. Preview rows show current title/body, omit ongoing services and group summaries, and contain no button instructions. No-access and empty states are simple status messages. Full notification list remains accessible through the selected notification panel and the existing F2 shortcut.

Top bar now shows actual cellular signal level (or explicit absent/unknown state), connected Wi-Fi signal, Bluetooth enabled, vibrate/mute, battery level and charging. Removed the app-name text. Added normal network/Wi-Fi/Bluetooth state permissions only; no contacts, SMS, call-log or location access. Signal subscription errors degrade gracefully rather than displaying invented bars.

Built, signature-verified and installed on KYF42; no OrbitStatus/AndroidRuntime errors observed. Physical regression test requested from user for Book/Camera/Clear/F3 and retained cursor hold.

## Side-key sound cycle (1.0.3)

Physical side key verified as gpio scan 254 → Android CAMERA, distinct from the front Camera/F2 key. While Retro Orbit is active, it cycles Sound → Vibrate → Silent → Sound once per complete press, including a held press. Top status indicator updates with the actual ringer mode. Front Camera remains Options. Outside Retro Orbit, the side key retains the phone's existing behavior.

Adds MODIFY_AUDIO_SETTINGS and ACCESS_NOTIFICATION_POLICY declarations. If a firmware requires special Do Not Disturb access to enter/leave Silent, the app shows a setup dialog instead of crashing; no grant was needed on this KYF42. Verified internal and external AudioManager modes SILENT, NORMAL and VIBRATE through a complete cycle. Restored Vibrate after test. User confirmed the physical side key cycles all three modes without opening Camera.

## Retro sound controls (1.0.4)

Short physical side-key presses cycle Sound / Vibrate / Silent with a custom pixel icon popup that dismisses automatically. Holding the side key for 650ms opens a custom chrome volume panel instead of cycling. Up/Down selects Ringtone, Media or Alarm; Left/Right adjusts the real AudioManager stream without a system volume popup; Center/Clear closes. Touch fallback selects a stream and adjusts on its left/right halves. The top bar uses the same original pixel icon artwork for sound modes. No raster sheet or third-party icon assets are required for these controls.

F3 hold remains native cursor; front Camera remains Options. User confirmed short and long physical side-key behavior and volume controls working on KYF42. Build/signature/install passed and no AndroidRuntime errors observed during check.

## Global F2 Home key (1.0.5)

Bottom-row F2 (Android F11) is claimed as a complete press/release sequence even outside Retro Orbit, and invokes Android GLOBAL_ACTION_HOME through the already-enabled accessibility service. Other keys are still only remapped while the launcher is foreground. Numeric 2 is unaffected. Traditional and Orbit T9 keyboards remain independently installed. Physical Home behavior test requested from the user.

## Combined launcher and keyboard (1.1)

Orbit T9 service, setup/practice activity, personal dictionary and frequency data are now part of com.retro.launcher. Keyboard settings are reachable through Settings → Keyboard & typing. A read-only same-signature provider in the temporary migration update of standalone T9 transferred five learned words; user confirmed bundled typing, then standalone T9 was uninstalled. Default IME is now com.retro.launcher/com.retro.t9.OrbitIme. Traditional T9 stays enabled. The combined package uses internet for weather; keyboard code does not transmit text. Complete setup/privacy/build notes are in the final deliverable README.
