## Steps to Run the Application

### 1. Install IntelliJ IDEA
- To install IntelliJ IDEA:
    - Go to the JetBrains website [here](https://www.jetbrains.com/idea/download/?section=windows)
    - Download the Community (free) version of IntelliJ IDEA.
    - Follow the on-screen instructions to install it on your computer.
- Reference: [Video Tutorial](https://www.youtube.com/watch?v=viNG3VVnzFE) or [Tutorial](https://www.tutorialspoint.com/step-by-step-guide-to-install-intellij-idea)

### 2. Setup JDK
- Download Oracle JDK from [Oracle's website](https://www.oracle.com/java/technologies/downloads).
- Follow the installation instructions specific to your operating system.
- Example: I downloaded Oracle JDK 21 for Windows 64-bit.

### 3. Clone the GitHub Repository
- Open your web browser and go to the GitHub repository.
- Click the "Download" button to download the project.
- Replace "//git url" with the actual Git repository URL.

### 4. Import the Downloaded Project in IntelliJ IDEA
- Open IntelliJ IDEA.
- Click "File" > "Open."
- Select the project downloaded as a project.

### 5. Build and Run Your Application
- Open the project in IntelliJ IDEA.
- Locate the main Java class, usually with a main method.
- Before running the project, do a Maven clean and install.
- Right-click on the main class and select "Run" or "Debug" to start your application.
- Ensure it's running on port 8000, as mentioned in the application.properties file.

## Steps to Test My Application

You can test the application using either Postman or cURL.

### Using cURL

#### Add Points
- Run the following cURL commands to add points with different (payer/points) payloads:
```shell
curl -i -X POST -H "Content-Type: application/json" -d "{\"payer\":\"DANNON\", \"points\":300, \"timeStamp\":\"2022-10-31T10:00:00.00Z\"}" http://localhost:8000/account/add
curl -i -X POST -H "Content-Type: application/json" -d "{\"payer\":\"UNILEVER\", \"points\":200, \"timeStamp\":\"2022-10-31T11:00:00.00Z\"}" http://localhost:8000/account/add
curl -i -X POST -H "Content-Type: application/json" -d "{\"payer\":\"DANNON\", \"points\":-200, \"timeStamp\":\"2022-10-31T15:00:00.00Z\"}" http://localhost:8000/account/add
curl -i -X POST -H "Content-Type: application/json" -d "{\"payer\":\"MILLER COORS\", \"points\":10000, \"timeStamp\":\"2022-11-01T14:00:00.00Z\"}" http://localhost:8000/account/add
curl -i -X POST -H "Content-Type: application/json" -d "{\"payer\":\"DANNON\", \"points\":1000, \"timeStamp\":\"2022-11-02T14:00:00.00Z\"}" http://localhost:8000/account/add
```
#### Spend Points
- Once points are added to the account, run the following cURL command to spend points:
```shell
curl -i -X POST -H "Content-Type: application/json" -d "{\"points\":5000}" http://localhost:8000/account/spend
```
#### Get Balance
- After spending points, fetch the balance of each payer by running the following cURL command:
```shell
curl -X GET -H "Content-Type: application/json" http://localhost:8000/account/balance
```

## Using Postman

### Install Postman
- Download Postman from [here](https://www.postman.com/downloads/).
- Once Postman is downloaded, follow the steps below.

### Import the Postman Collection
- To import the Postman collection:
  - In the top left corner of Postman, click on the "Import" button.
  - Import this [Postman collection](link) from the repository.
  - After importing, you will see the imported collection listed in the left sidebar of Postman under "Collections."
  - Click on the collection to expand it and see the requests and folders it contains.
  - To run a request within the collection, click on the request's name in the collection.
  - In the request view, you can configure parameters, headers, and request body if needed.
  - Click the "Send" button to execute the request.

### Test Scenarios

#### Add Points
- Run the `/add` POST call 5 times with different request body payloads. The URL is `http://localhost:8000/account/add`.
- To add the payload, go to Body -> raw and change the type to JSON.
  - Example payloads:
    - `{ "payer": "DANNON", "points": 300, "timestamp": "2022-10-31T10:00:00Z" }`
    - `{ "payer": "UNILEVER", "points": 200, "timestamp": "2022-10-31T11:00:00Z" }`
    - `{ "payer": "DANNON", "points": -200, "timestamp": "2022-10-31T15:00:00Z" }`
    - `{ "payer": "MILLER COORS", "points": 10000, "timestamp": "2022-11-01T14:00:00Z" }`
    - `{ "payer": "DANNON", "points": 1000, "timestamp": "2022-11-02T14:00:00Z" }`

#### Spend Points
- Once the points are added to the account, run the `/spend` POST call to spend points.
- The payload is already present in the collection, and the URL is `http://localhost:8000/account/spend`.
  - Payload: `{ "points": 5000 }`

#### Get Balance
- After spending points, fetch the balance of each payer by running the `/balance` GET call.
- The URL is `http://localhost:8000/account/balance`.