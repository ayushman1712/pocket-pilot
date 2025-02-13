# Pocket Pilot

## Overview

Pocket Pilot is an Android application that tracks a flight journey with multiple stops, displaying stop details, visa requirements, and journey progress. The app allows users to toggle between distance units (km/miles) and dynamically updates progress as stops are reached.

[Watch Demo](videos/demo.mp4)

## Versions

This project has **two versions**, implemented in separate branches of this repository:

1. **XML Version** (Traditional UI with RecyclerView)
2. **Jetpack Compose Version** (Modern UI with LazyColumn)

Each version follows the same functionality but is built using different UI frameworks.

## Features

- **List of stops**: Displays stop details, visa requirements, remaining distance, and time.
- **Progress bar**: Updates dynamically as stops are marked as "Reached."
- **Color-coded stops**: Green for reached, red for pending.
- **Distance unit toggle**: Switch between kilometers and miles.
- **Bottom section**: Displays total distance covered.

## How to Access the Versions

- The **XML version** is available in the `xml-version` branch.
- The **Jetpack Compose version** is available in the `compose-version` branch.

To switch between versions, use:
```sh
git checkout xml-version
```
or 
```sh
git checkout compose-version
```
