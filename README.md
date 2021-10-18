# MovieStubs

MovieStubs is an app for users to discover and research movies. In addition, users can also purchase a movie ticket from the app. For the purpose of the assignment, I focused most of my time on the app architecture instead of the UI/UX

## Features

- Movie Discovery - view top 10 or all movies of the year at a glance
- Movie Research - view details of a movie (duration, actors, poster, description)
- Movie Booking - purchase a ticket for the movie


## App Architecture

#### Separation of concerns
- Business logic should not be intertwined with the UI code
- UI-based classes should only contain logic that handles UI and operating system interactions

#### Drive UI from a model
- The (view) model is responsible for handling data for the UI
- The (view) model should not be affected by the app's lifecycle and configuration changes


#### Repository Pattern (https://martinfowler.com/eaaCatalog/repository.html)
- Repositories to be mediators between different data sources
-  A repository pattern approach will provide a clean API to retrieve data easily from single or multiple access points. (network, local storage, etc..)


### Libraries
- Live Data & view model - android life cycle
- Navigation
- Retrofit - API HTTP client
- Gson - serialization/deserialization of Java/Kotlin objects to JSON
- Glide - image loading & caching
- Hilt (dagger) - for dependency injection
- Data & View binding - to reduce UI & layout boilerplate


## References
- https://developer.android.com/jetpack/guide#recommended-app-arch

- https://developer.android.com/jetpack#architecture-components

- https://developer.android.com/jetpack/guide#common-principles
