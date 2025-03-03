# PingStream

PingStream is a notification service application built with Spring Boot. It currently supports sending notifications via email using the AHASEND API.

## Getting Started

### Prerequisites

- Java 23
- Gradle
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/pingStream.git
    cd pingStream
    ```

2. Create a `.env` file in the root directory with the following content:
    env```
    AHASEND_API_KEY=your_api_key
    AHASEND_API_URL=your_api_url
    ```

3. Configure the database in `src/main/resources/application.properties`:
     //Currently disabled, no need to touch this
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/notifications
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

5. Build and run the project:
    ```sh
    ./gradlew build
    ./gradlew bootRun
    ```
