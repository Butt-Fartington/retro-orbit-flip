# Version 0.6 validation

- Build completed and APK signature verified with Android SDK build-tools 36.0.0.
- Installed and started on the physical KYF42: Android 10/API 29, 480×854, 240 dpi.
- Physical F1/F2/F3 behavior in earlier builds confirmed by the user; 0.6 retains those mappings and adds contextual F2 menus.
- Carousel indexing: 200,100 cases passed (empty lists, modulo bounds, forward/reverse transitions).
- Opened native weather and theme dialogs using keypad events; theme writes confirmed in device preferences. Restored Chrome Blue after testing.
- ZIP 63021 resolved through Open-Meteo geocoding to Ballwin, Missouri. Configured that location with Fahrenheit while preserving favorites.
- Real weather fetched by the Android app on the KYF42 and displayed on home: confirmed non-empty cached temperature, location and condition. No weather values seeded from the host. Initial no-network state handled without crashing.
- Device screenshot saved as kyf42-0.6-home.png.
- No AndroidRuntime crash output observed during these smoke checks.
- Notification access enabled on phone. Posted a shell test alert and confirmed it appeared in the launcher. Dismissed only that test alert using the launcher alert menu; count decreased from 5 to 4.
- Opened the existing USB notification from the launcher; Android resumed its USB mode chooser. Returned to launcher without changing USB settings.
- Corrected native-dialog lifecycle handling so closing an older dialog cannot clear the reference to a newer one.
- Rechecked build and on-device startup after the final changes.

Unverified: full phone sleep/flip/End-key behavior, long battery/performance measurements, complete screen-reader virtual focus, device keyboard/IME behavior for arbitrary city entry, and release-level checks. Current wallpapers are three built-in styles rather than gallery image selection. The direct SDK build has no Gradle lint task.
