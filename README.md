# SignLanguageApp

SignLanguageApp is an Android application designed to help users learn and practice sign language. It provides features such as fingerspelling, common words with video demonstrations, daily practice quizzes, and an interpreter that converts text to sign images.

## Features

- **Login & Registration:** Secure user authentication with local SQLite database.
- **Fingerspelling:** Convert spoken words to sign images using speech recognition.
- **Common Words:** Browse a list of common words with sign language video demonstrations.
- **Daily Practice:** Quiz yourself with sign images and track your score.
- **Interpreter:** Convert typed text into a sequence of sign images.
- **More Resources:** Access external links and emergency sign language guides.

## Screenshots

*(Add screenshots of your app here)*

## Getting Started

### Prerequisites

- Android Studio (or compatible IDE)
- Android device or emulator (API 24+)
- Java 11+

### Building the Project

1. Clone the repository:
    ```sh
    git clone <your-repo-url>
    ```
2. Open the project in Android Studio.
3. Let Gradle sync and download dependencies.
4. Build and run the app on your device or emulator.

### Project Structure

- `app/src/main/java/com/example/signlanguageapp/` - Main application source code
- `app/src/main/res/` - Resources (layouts, drawables, values, videos)
- `app/src/main/res/raw/` - Video files for sign demonstrations (add your `.mp4` files here)
- `app/src/main/res/drawable/` - Images for sign letters (e.g., `sign_a.png`, `sign_b.png`, etc.)

### Adding Sign Images and Videos

- Place sign letter images in `app/src/main/res/drawable/` with names like `sign_a.png`, `sign_b.png`, etc.
- Place word demonstration videos in `app/src/main/res/raw/` with names matching those referenced in `CommonWordsActivity.java`.

## Dependencies

- AndroidX Libraries
- Material Components
- SQLite (via Android SDK)
- JUnit (for testing)

## Contributors

- [@unknown7751](https://github.com/unknown7751) (unknown7751)
- [@dipanshumilmile](https://github.com/dipanshumilmile) (dipanshumilmile)
- [@Auxilus08](https://github.com/Auxilus08) (Auxilus08)

## Acknowledgements

- [Handspeak](https://www.handspeak.com)
- [Start ASL](https://www.startasl.com)
- [Sign Language 101](https://www.signlanguage101.com)

---

*For any questions or contributions, please open an issue or submit a pull request.*
