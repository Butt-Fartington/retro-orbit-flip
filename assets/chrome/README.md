# Chrome Blue Android Launcher Asset Pack

Inspired by the four supplied design screenshots.

## Contents
- icons/transparent/*.png: 9 individually extracted, circular transparent icons (432x432). The icons retain the original dark inner disc and chrome ring.
- icons/adaptive_foreground/*.png: same 9 icons centered within an Android adaptive-icon 432px canvas, with safe insets.
- android/res/drawable-nodpi/: Android-ready foreground PNGs (icon appearance still depends on your launcher).
- ui/: separately reusable transparent chrome menu bars, selected bar, buttons, selection brackets, theme swatches, paging states. Text is intentionally not baked into reusable controls.
- wallpapers/: 1080x1920 dark blue orbital wallpaper.
- previews/: generated concept sheet for visual guidance only; it is NOT a sprite sheet.

## Usage
Use the transparent PNGs in a custom Android launcher or icon pack. For an Android adaptive icon, provide a plain navy (#061a37) background and set the corresponding foreground. Custom icons in third-party launchers are usually set via the launcher's icon-edit controls; some apps may require an icon-pack APK. You cannot install a PNG folder as a full-fledged icon pack APK. For proper integration of menu rows/buttons/clock widgets, modify your launcher code; stock launchers usually cannot replace those components with PNG assets.

## Notes
App icons were cropped from screenshots, not vectorized. Some chrome rings may have tiny source-background pixels from antialiasing. UI bars and wallpaper are programmatic recreations. This pack is a visual starting point, not a packaged Android APK or a compiled launcher theme.
