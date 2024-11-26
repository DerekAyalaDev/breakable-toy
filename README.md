# Breakable-Toy

**Description:**
Breakable-Toy is a full-stack flight search application that leverages the Amadeus REST API to provide a user-friendly interface for finding and exploring flights. The application allows users to search for flights by specifying key parameters such as departure and arrival airports, travel dates, the number of travelers, currency, and whether they prefer non-stop flights. It dynamically integrates airport search functionality for users unfamiliar with IATA codes and enforces robust input validations for dates and parameters.

The application offers a comprehensive view of flight details, including departure and arrival times, airline and airport information, total flight duration (including layovers), and pricing breakdowns in the selected currency. Users can sort the results by price and duration and explore detailed flight segments, cabin amenities, and fare breakdowns. Support for roundtrip searches ensures that departing and returning flights are displayed with all necessary details.

---

## Prerequisites

Before proceeding, ensure you have the following installed:

1. [Docker](https://www.docker.com/) (with Docker Compose support)
2. A valid [Amadeus API](https://developers.amadeus.com/) account to generate API credentials.
3. [Git](https://git-scm.com/) to clone the repository.

---

## Step 1: Clone the Repository

1. Open your terminal.
2. Clone the project repository:

   ```bash
   git clone https://github.com/your-username/breakable-toy.git](https://github.com/DerekAyalaDev/breakable-toy.git
   ```

3. Navigate to the project directory:

   ```bash
   cd breakable-toy
   ```

---

## Step 2: Create an Amadeus API Account

1. Go to [Amadeus for Developers](https://developers.amadeus.com/).
2. Sign up or log in to your account.
3. Create a new application and obtain the following credentials:
   - **API Key**
   - **API Secret**
   - **Base URL** (e.g., `https://test.api.amadeus.com` for testing)
   - **Token URL** (usually `https://test.api.amadeus.com/v1/security/oauth2/token`)

---

## Step 3: Configure the Backend `.env` File

1. Navigate to the **`Backend`** directory:

   ```bash
   cd Backend
   ```

2. Create a new file named `.env`.
3. Add the following content to the `.env` file, replacing the placeholder values with your Amadeus API credentials:

   ```plaintext
   # Amadeus API credentials
   AMADEUS_API_KEY=your_amadeus_api_key
   AMADEUS_API_SECRET=your_amadeus_api_secret

   # Base URL for Amadeus API (without version)
   AMADEUS_BASE_URL=https://test.api.amadeus.com

   # Token URL for authentication
   AMADEUS_TOKEN_URL=https://test.api.amadeus.com/v1/security/oauth2/token
   ```

---

## Step 4: Build Backend and Frontend Docker Images

### Backend

1. Ensure you are in the **`Backend`** directory:

   ```bash
   cd Backend
   ```

2. Build the backend Docker image:

   ```bash
   docker build -t backend-image .
   ```

3. Return to the root project directory:

   ```bash
   cd ..
   ```

### Frontend

1. Navigate to the **`frontend`** directory:

   ```bash
   cd frontend
   ```

2. Build the frontend Docker image:

   ```bash
   docker build -t frontend-image .
   ```

3. Return to the root project directory:

   ```bash
   cd ..
   ```

---

## Step 5: Run the Project Using Docker Compose

1. From the root directory of the project, run the following command to start both services (backend and frontend):

   ```bash
   docker-compose up
   ```

2. Docker Compose will:
   - Start the **backend** service on port `9090`.
   - Start the **frontend** service on port `8080`.

3. You can access the frontend by navigating to [http://localhost:8080](http://localhost:8080) in your web browser.

---

## Step 6: Testing the Application

### Frontend
1. Open [http://localhost:8080](http://localhost:8080) in your browser.
2. Enter search criteria for flights in the UI and click the "Search" button.

### Backend
1. Confirm that the backend is running by checking its logs in the terminal.
2. The backend is accessible at [http://localhost:9090](http://localhost:9090) (if needed for direct API testing).

---

## Notes

- **Dependencies**: Ensure the `.env` file is correctly created in the backend directory; otherwise, the backend will not start successfully.
- **Docker Cleanup**: To stop the services, press `Ctrl+C` in the terminal where Docker Compose is running. To remove containers, run:

  ```bash
  docker-compose down
  ```

---

## Troubleshooting

### Common Errors

1. **Backend Fails to Start**
   - Ensure the `.env` file exists and contains valid Amadeus API credentials.
   - Verify that the ports `9090` (backend) and `8080` (frontend) are not already in use.

2. **Docker Compose Error**
   - Ensure that the `docker-compose.yml` file is correctly configured.
   - Verify Docker is installed and running.

3. **Frontend Issues**
   - Ensure that the frontend is properly built using the Dockerfile.
   - Clear browser cache if UI changes are not reflected.

---

This guide ensures that you can set up, run, and test the **Breakable-Toy** project successfully. If you encounter issues not covered here, consult Docker or application-specific documentation.
