# Flux Runner

A 2D precision puzzle-platformer with 69 levels where **the rules keep changing**: controls get inverted or remapped, gravity drops, floors turn to ice or crumble, spikes appear from nowhere, and doors lie or run away. Built with vanilla JavaScript and HTML5 Canvas, with no game engine and no dependencies. Runs in the browser and as a native Android app (Kotlin WebView).

## Play online
https://helicasecoded.github.io/FLUXRUNNER/

Works on phone and desktop. On Android, open the link in Chrome, then menu > Add to Home screen to install it as an app.

## Download the Android app
Go to the Releases page and download the latest `FluxRunner.apk`. New builds install over old ones.

## Features
- 69 levels: 19 hand-built and 50 procedurally composed from a mechanic library (keys, button and gate, portals, springs, moving platforms, crumbling floors, hidden spikes, fake doors)
- Difficulty ramps up over 6 tiers, and every level has its own layout and name
- Changing rules: inverted controls, remapped buttons, low gravity, ice
- Level select with progress saved locally, no login needed
- Lives system: 5 lives, one regenerates every 20 minutes (a full refill takes 100 minutes), or watch a rewarded ad for +1 life
- Cryptic riddle-style hints, unlocked by watching a rewarded ad. There is no level skipping
- Light adaptive assist: after repeated deaths on a level, hazards slow down and hitboxes become more forgiving, and after 8 deaths a double jump is enabled
- Touch controls (multi-touch) and keyboard support (arrows, WASD, Space)
- Installable PWA with offline support
- Native Android app built with Kotlin, signed APKs built automatically with GitHub Actions

## Controls
- Phone: on-screen left, right and jump buttons
- Keyboard: arrow keys or A/D to move, Up, W or Space to jump
- In-game: the lightbulb button shows a hint (after an ad), the menu button returns to level select

## Project structure
- index.html: the entire game (logic, levels, rendering, UI)
- manifest.json, sw.js, icon.svg: PWA support (install and offline)
- settings.gradle.kts, build.gradle.kts, app/: Kotlin Android app that loads the game in a WebView
- app/src/main/java/com/fluxrunner/game/MainActivity.kt: the Android activity
- .github/workflows/build.yml: builds and signs the APK and publishes it to Releases

## How levels work
Levels 1 to 19 are hand-designed. Levels 20 to 69 are generated from a seeded random generator, so they are the same for every player. The generator composes each level from segments (spikes, saws, gaps, springs, moving platforms and more) and adds global rule changes and special features. It follows a difficulty curve, and each level gets a short riddle hint built from its features.

## Build the Android APK
1. Go to the Actions tab, select "Build signed APK" and click Run workflow.
2. The first run creates a signing key and stops on purpose. Download the artifact SAVE-THESE-2-SECRETS.
3. Add its two files as repository secrets named KEYSTORE_BASE64 and KEYSTORE_PASSWORD (Settings > Secrets and variables > Actions).
4. Run the workflow again. The signed FluxRunner.apk appears under Releases.
5. Every later push to the game or app files builds a new APK. The version code goes up each time and the same key is reused, so updates install over the old version.

Keep the signing key safe. If it is lost, new versions cannot be installed over old ones.

## Host the game online
Settings > Pages > Build and deployment > Deploy from a branch > main > / (root) > Save.

## Ads
The rewarded ad is currently a demo (a 5 second placeholder screen). To use real ads, integrate AdMob and define window.showRewardedAd(callback), which should call callback() once the ad is completed. The game uses that hook automatically for both lives and hints.

## Notes
- Progress and lives are stored in the browser or app local storage, so there is no server and no account.
- Lives are not tamper-proof because there is no backend.

## Tech
JavaScript, HTML5 Canvas, Service Worker, Web App Manifest, Kotlin, Android WebView, Gradle, GitHub Actions
