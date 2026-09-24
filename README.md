# Android Project 4 - Flixster++

Submitted by: **Jaden**

**Flixster++** is a movie browsing app that allows users to browse popular and top-rated movies currently playing in theaters, view movie details, ratings, and release dates.

Time spent: **3** hours spent in total

## Required Features

The following **required** functionality is completed:

- [x] **Choose any endpoint on The MovieDB API except `now_playing`**
  - Chosen Endpoint: `https://api.themoviedb.org/3/movie/popular` (Popular Movies)
- [x] **Make a request to your chosen endpoint and implement a RecyclerView to display all entries**
- [x] **Use Glide to load and display at least one image per entry**
- [x] **Click on an entry to view specific details about that entry using Intents**

The following **optional** features are implemented:

- [x] **Add another API call and RecyclerView that lets the user interact with different data.** 
  - Added filter toggle buttons for both Popular (`/movie/popular`) and Top Rated (`/movie/top_rated`) TMDB API endpoints.
- [x] **Add rounded corners to the images using the Glide transformations**
  - Applied `RoundedCorners` transformation with `CenterCrop` via Glide for all movie posters and backdrop images.
- [x] **Implement a shared element transition when user clicks into the details of a movie**

The following **additional** features are implemented:

- [x] Custom dark-mode Material UI styling with rating badges (`⭐ 8.5 / 10`) and vote counts
- [x] Progress bar loading indicator during API network calls
- [x] Landscape and portrait responsive poster/backdrop dimensions

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img width="861" height="988" alt="Flixster++ Demo 2" src="https://github.com/user-attachments/assets/8e37f7df-c80d-427c-9f90-3ba050a7f0aa" />



<!-- Replace this with whatever GIF tool you used! -->
GIF created with ScreenToGif  
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

## Notes

- Handled Intent parameter passing between `MainActivity` and `DetailActivity` cleanly using `Serializable` movie objects.
- Integrated multiple TMDB API endpoints (`/movie/popular` and `/movie/top_rated`) seamlessly in a single interactive UI.

## License

    Copyright 2025 Jaden

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
