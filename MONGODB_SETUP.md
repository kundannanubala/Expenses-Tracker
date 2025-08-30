# MongoDB Setup for Expenses Tracker

## Prerequisites

1. **MongoDB Server**: Install MongoDB Community Edition from [https://www.mongodb.com/try/download/community](https://www.mongodb.com/try/download/community)

2. **Java 17**: Ensure you have JDK 17 installed

3. **Maven**: For building the project

## MongoDB Installation and Setup

### Windows
1. Download MongoDB Community Edition installer
2. Run the installer and follow the setup wizard
3. Choose "Complete" installation
4. Install MongoDB as a Windows Service
5. MongoDB will start automatically on port 27017

### Linux/macOS
1. Follow the installation guide for your specific OS from MongoDB documentation
2. Start MongoDB service:
   ```bash
   sudo systemctl start mongod  # Linux
   # or
   brew services start mongodb-community  # macOS
   ```

## Database Configuration

The application is configured to connect to:
- **Host**: localhost
- **Port**: 27017 (MongoDB default)
- **Database**: expenses_tracker

No authentication is required for local development.

## Data Initialization

The application includes automatic data initialization through `MongoDataInitializer.java` which will:
- Create default user roles (ROLE_USER, ROLE_ADMIN)
- Create default expense categories (Food, Transportation, Entertainment, etc.)

## Running the Application

1. Ensure MongoDB is running on localhost:27017
2. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## MongoDB Collections Created

The application will automatically create the following collections:
- `users` - User accounts and authentication
- `clients` - Client profile information
- `roles` - User roles and permissions
- `categories` - Expense categories
- `expenses` - Expense records

## Verification

You can verify the database setup using MongoDB Compass or the MongoDB shell:
```bash
mongo
use expenses_tracker
show collections
```

## Notes

- MongoDB uses document-based storage instead of relational tables
- Entity relationships are handled through document references (@DBRef)
- No foreign key constraints - referential integrity managed by application logic
- Automatic ID generation using MongoDB ObjectId (stored as String in entities)
