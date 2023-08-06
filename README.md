# Quick Loans 
**Quick Loans** is an Android application designed to provide users with a seamless experience for managing loans. The app features login and registration functionality, a history of previously arranged loans, detailed loan information, and the ability to retrieve available loan conditions.
## Features

- User Authentication: Secure login and registration functionality.
- Loan History: View a list of previously arranged loans.
- Loan Conditions: Retrieve and display information about available loan conditions.
- Loan Details: Get detailed information about a specific loan.
- Multilingual Support: Available in two languages for enhanced accessibility.
- Theme Options: Light and dark theme support for user customization.
- Error Handling: Well-managed error handling for a smooth user experience.
- Modular Structure: The app is split into modules for maintainability and scalability.
- Data Security: Utilizes Android Security for encrypted shared preferences.
- Modern UI: Developed using Material 3 design guidelines.

## Screenshots
### Authentication 
| Login            | Register            |
| ---------------------- | ---------------------- |
| ![login](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/de78f44b-09a5-4ab1-b255-9baa6b58f6a3) | ![register](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/29c592b5-777c-4937-aa3b-62ce4497fdfc)

When a user registers through the app, the following steps are taken to ensure data accuracy and prevent potential issues:
**Input Validation:** The registration form validates each input field to ensure the provided information is valid and meets specific criteria.
  - Checks for empty fields, incorrect symbols, and proper formatting.
**Existing User Check:** Before registering a new user, the app checks if a user with the same username already exists.
  - If a user with the same username is found, an appropriate error message is displayed to the user.

### History
| Light Theme          | Dark Theme            |
| ---------------------- | ---------------------- |
| ![1688287833364_100 (2)](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/608de0bf-cf2e-4cf1-bd45-097517757f31)|![1688287839438_100 (2)](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/6a5aeeef-cf56-43f1-90c8-7ca192aeb3b2)

### New loan and details

| Conditions             | Details            |
| ---------------------- | ---------------------- |
|![1688287827242_100 (2)](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/45813ee1-3061-4fb8-a668-1bbca0131891) | ![1691312120483_100](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/59635783-a54a-46ac-8edd-50b4958da9e9)

## Error handling

**Quick Loans** is designed with robust error handling mechanisms to ensure a smooth and intuitive user experience, even when facing connectivity and server-related issues. The app employs various strategies to address different types of errors:

### Connection Errors

In case of connectivity problems, such as network unavailability or poor internet connection, the app takes the following approach:

- **Offline Detection:** The app detects when the device is offline and displays a user-friendly message, informing the user about the need for a stable internet connection.
- **Retry Mechanism:** For transient connection errors, the app presents a "Retry" button on the screen. When the user taps the "Retry" button, the app attempts to reconnect and fetch the data again.
- **Toast Notifications:** For minor connection hiccups, a non-intrusive toast notification is displayed to let the user know that the operation failed temporarily.

### Server Errors

When encountering errors related to server interactions, **Quick Loans** provides detailed messages and guidance to users:

- **Unique Error Messages:** Different types of server errors, such as timeouts, internal server errors, and data validation failures, are associated with unique and informative error messages.
- **User-Friendly Explanations:** Error messages are crafted to be user-friendly and provide context, explaining what went wrong in a clear manner.
- **Retry Screens:** For critical server errors that might require user intervention or acknowledgment, the app displays a dedicated screen with a concise description of the issue and a "Retry" button.
- **Toast Notifications:** For minor server errors or cases where retrying is not necessary, the app displays a toast notification to notify the user.

### Handling Unforeseen Scenarios

For scenarios that were not anticipated in the design, the app employs a generic error message and encourages users to try again later.

By employing these error handling strategies, **Quick Loans** aims to minimize user frustration and provide them with appropriate information and options to resolve issues.


| Error screen             | Error message         | Input validation
| ---------------------- | ---------------------- | ---------------------- |
|![1688287847150_100 (2)](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/a49dadcf-be1b-4230-ab73-046fae4b1282)| ![1691313461510_100](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/afdc6847-20d2-4493-81e2-76e1daad7d16)|![1691313488183_100](https://github.com/vladshapovalovv/QuickLoans/assets/106911470/5e0896a4-7d0f-4803-8f7f-a06a6cf09a69)


## Tech Stack 

- OkHttp: Used for efficient HTTP communication.
- Retrofit: A type-safe HTTP client for networking.
- Dagger-Hilt: Dependency injection library for Android.
- Cicerone: A library for managing app navigation.
- Material 3: Design system for creating modern, intuitive interfaces.
- Android Jetpack Libraries: Utilized for various Android components.
- Kotlin Coroutines: For managing asynchronous tasks.
- LiveData: Lifecycle-aware data holder for UI components.
- ViewBindingDelegates: Streamlines view binding implementation.

## Application Structure and Architecture
<img width="2658" alt="Untitled(10)" src="https://github.com/vladshapovalovv/QuickLoans/assets/106911470/f3f29018-13c7-4a32-a002-9420da43342b">


### App Module
- Main activity and UI components.
- Dependency injection setup (Dagger-Hilt).
- Navigation setup (Cicerone or other navigation framework).
- Initialization and configuration of the app.
  
### Component Module
- Common UI components like error screens, loaders, etc.
- Error handling classes and error response parsers.
- Utility classes that are used across the app.
  
### Feature Modules
- Creating Loan: User interface for initiating a new loan request.
- Loan History: User interface to display a list of previously arranged loans.
- Profile: User settings and profile management screens.
  
### Shared Module
- Data models for loans (LoanModel, LoanStatus, etc.).
- Repository for accessing and managing loan data.
- Network and database interfaces for loan-related operations.
  
### Util Module
- Converters and formatters for transforming data.
- Common extension functions.
- Utility classes for handling various tasks.

### App is build following the principles of Clean Architecture, SOLID and using  MVVM + UDF patterns for presentation layer.
<img width="1296" alt="untitiled" src="https://github.com/vladshapovalovv/QuickLoans/assets/106911470/185bca57-7e3a-4b56-abfe-aa4f745211e6">

## Credits 

The development of **Quick Loans** would not have been possible without the hard work and dedication of the Center of Financial Technologies **SHIFT** team. Sincerely appreciate them for providing the essential backend services that power this application and creating Android Course that equipped me with the knowledge and skills to build **Quick Loans**. Their comprehensive course laid the foundation for app's development.  

<br />
