## Android ToDo App

This Todo App demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (Room,
ViewModel,Retrofit), and Material Design based on MVVM architecture.

### About

This app,helps you to create todos and view them but here comes the interesting part the todos
created are persistent i.e, they are actually stored

### Backend Api

The backend api used as a integration for retrofit and okhttp3 , the backend api serves as a primary
source of data for the todo. The api is made using `fastapi` and `python`, for more information
follow the links:

- [FastApi](https://fastapi.tiangolo.com/)
- [python](https://www.python.org/)

### Tech stack & Libraries Used

- Minimum SDK level 28
- [Kotlin](https://kotlinlang.org/)
  based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
  for asynchronous.
- Jetpack
    - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive
      configuration changes such as screen rotations.
    - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent
      database access.
    - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- Architecture
    - MVVM Architecture (View - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging
  network data.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [Material-Components](https://github.com/material-components/material-components-android):
  Material design components for building ripple animation, and CardView.
