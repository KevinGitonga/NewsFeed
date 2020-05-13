<img src="https://i.imgur.com/8UH3sOK.png"/> &nbsp;&nbsp;&nbsp;

# About News Feed
News Feed is a news app powered by <a href="https://newsapi.org/"><b>NewsAPI.org</b></a> which shows latest news and categorized news based on user location.
- News is Loaded based on user Locality.
- Clean and Simple Material UI.
- Supports dark theme.

# Download

**News Feed** can be download from our releases section (https://github.com/KevinGitonga/NewsFeed/blob/master/app/release/app-release.apk):

## App architecture in MVVM
![](https://i.imgur.com/u20cfQT.png?w=960&h=720&f=webp&s=15382)


# Libraries used
- [**Kotlin**](https://github.com/JetBrains/kotlin) 
- [**Retrofit**](https://github.com/square/retrofit) for constructing the REST API
- [**Glide**](https://github.com/bumptech/glide) for loading images
- [**Kotlin Coroutines**](https://github.com/Kotlin/kotlinx.coroutines) for retrofit.
- [**Room**](https://developer.android.com/topic/libraries/architecture/room) for persistence.
- [**Koin Dependency Injection**](https://github.com/InsertKoinIO/koin) for dependency injection.
- [**Pretty Time**](https://github.com/ocpsoft/prettytime) for parsing Date.
- **Android Support Libraries**

## Build
### Open the project in Android Studio
```
git clone https://github.com/KevinGitonga/NewsFeed.git
```
- Create and Place your NewsAPI key in [apiKey.properties](https://github.com/KevinGitonga/NewsFeed) .<br/>
- Wait for project to finish building and happy coding.

## License

    MIT License

    Copyright (c) 2020 Kevin Gitonga

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
