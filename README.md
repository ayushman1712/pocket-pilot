# Pocket Pilot (XML Version)

## Overview
This Android application tracks a flight journey with multiple stops, displaying stop details, visa requirements, and progress. The app includes a toggle button for unit conversion (km/miles) and updates progress dynamically as stops are reached. 

## Features
- **Scrollable list of stops** using RecyclerView, each displayed as a CardView.
- **Progress bar** that updates as stops are reached.
- **Color-coded stops**: Green for reached, red for pending.
- **Distance unit toggle**: Switch between kilometers and miles.
- **Bottom section** showing total distance covered.

## Project Structure
```plaintext
FlightJourneyTracker/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/flightjourney/
│   │   │   │   ├── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── card_stop.xml (CardView for each stop)
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── bottom_box_background.xml
```

## Implementation Details
1. **MainActivity.xml**
   - Displays the progress bar, RecyclerView, and bottom section.
   - Uses ConstraintLayout for structured UI design.

2. **CardView (card_stop.xml)**
   - Each stop has a name, visa requirements, remaining distance, and time.
   - Changes color based on whether the stop is reached.

3. **Unit Toggle Button**
   - Converts total distance between kilometers and miles dynamically.

## How to Run
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device.
