# **Java Spring Boot & React Web App Setup Guide**

This guide is intended to assist you with the initial setup of creating a web application using Java Spring Boot and React. Follow the steps below, making changes at your discretion to build the foundation of a full-stack Java Spring Boot and React web app.

## **Table Of Contents**

- [Environment Setup](#environment-setup)
- [Building Your Application](#building-your-application)
  - [Spring Initializr](#spring-initializr)
  - [Unzipping And Opening The Project In IntelliJ](#unzipping-and-opening-the-project-in-intellij)
  - [Running Your Application](#running-your-application)
  - [Creating The Database](#creating-the-database)
  - [Creating The React App](#creating-the-react-app)
- [Testing Your Application](#testing-your-application)

## **Environment Setup**

Before building it is very important to ensure that you have properly installed and configured the necessary tools needed to construct the application. Download the latest versions of each technology, or determine which version works best for your project. Please complete the requirements below before proceeding:

- Editors/IDEs:

  - [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/) (preferred IDE for Java development)
  - [Visual Studio Code](https://code.visualstudio.com/download/) (preferred IDE for React development)

- Technologies:

  - [Java Development Kit](https://www.oracle.com/java/technologies/downloads/)
  - [Node.js](https://nodejs.org/en/download/)
  - [PostgreSQL](https://www.postgresql.org/download/)

## **Building Your Application**

If you have successfully completed the requirements above, you are now ready to begin constructing your application. Please proceed with the following steps:

### **Spring Initializr**

To initialize a new Java Spring Boot application, go to https://start.spring.io/ where you will be prompted to complete the initializer form. Input the following fields as so, making any changes that you deem necessary:

- Project:

  - Maven

- Language:

  - Java

- Spring Boot:

  - 3.0.3 (or whichever Spring Boot version you are running)

- Project Metadata:

  - Group: com.projectname
  - Artifact: project
  - Name: project
  - Description: Project Description
  - Package name: com.groupinput.artifactinput

- Packaging:

  - Jar

- Java:

  - 19 (or whichever Java version you are running)

- Dependencies:
  - Spring Web
  - Spring Data JPA
  - PostgreSQL Driver
  - Any Additional Dependencies That You Desire

Once you have completed the form and included the necessary dependencies, hit the `Generate` button at the bottom of the page. This should create a .zip file, which will automatically download to your local device.

### **Unzipping And Opening The Project In IntelliJ**

Locate the .zip file on your local device. It will typically be found in the Downloads folder. Once located, unzip the file by double-clicking, or right-clicking and selecting `Open`. A project directory is then extracted. Move the project directory wherever you would like to store your project files locally on your device.

Now open IntelliJ. It will typically prompt you to create a new project, or open an existing project. Click `Open`, and navigate to the directory where you stored your project. Click `Open`, and your project should be opened in the IntelliJ IDE.

### **Running Your Application**

Feel free to familiarize yourself with the Java Spring Boot file structure. Next, open the `pom.xml` file in the project directory. This file is responsible for storing your dependencies. For the purposes of testing your application, comment out the following code snippet within the dependencies (we will comment it back in later):

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Next, navigate to your main project application found in `src/main/java/com.project`. Once you have located and opened your main project application, replace the contents of this file with the following code, replacing any instance of 'project' with the name of your project:

```
package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Project {

    public static void main(String[] args) {
    	SpringApplication.run(ProjectApplication.class, args);
    }

    @GetMapping("/hello")
    public List<String> hello() {
    	return List.of("Hello", "World");
    }

}
```

After saving your main application file. Run your program in IntelliJ by clicking the play button. This should start the server on Port 8080. Navigate to http://localhost:8080/hello in your browser. You should see JSON that looks similar to this:

```
[
  "Hello",
  "World"
]
```

If so, you can now proceed to the creation of your database.

### **Creating The Database**

When creating a database we will be utilizing Spring Data JPA. Find the `application.properties` file, located in the `project/src/main/resources` directory, and open it. This file should be blank. Copy the following code and paste it in the file, replacing 'database' with whatever you intend to name your database:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

After saving the `application.properties` file, we will then create the database using PostgreSQL. The default port for PostgreSQL is 5432.

Open your terminal and enter the following command to initiate the PostgreSQL interactive terminal:

```
$ psql
```

Once inside of the PostgreSQL interactive terminal, enter the following command to create a new database, replacing 'database' with whatever you would like to name your database (and identical to the name used in the url path within the `application.properties` file above):

```
$ CREATE DATABASE database;
```

This will create a new database, with the database name provided. Next we must grant database privileges. Enter the following command to access the active roles:

```
$ \du
```

This will display a list of roles. For each role, we will grant all privileges. Do so by entering the following command, replace 'role1' with the name of the first role listed on your screen, and 'database' with the name of the database that you had just created:

```
$ GRANT ALL PRIVILEGES ON DATABASE "database" TO role1;
```

Now again for the second role listed on your screen:

```
$ GRANT ALL PRIVILEGES ON DATABASE "database" TO role2;
```

At this point you have granted all privileges to both roles, and are now ready to connect to the database. Enter the following command, again replacing 'database' with the name of the database that you recently created:

```
$ \c database
```

You should recieve a response that confirms that you are now connected to the database as a particular user. If so, re-open the `pom.xml` file within your root project directory. Now comment back in the JPA dependency that you uncommented above:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Be sure to then save your `pom.xml` file.

Lastly, we can test our connection to the database by reloading our project. Right-click on the `pom.xml` file and then find and click `Maven -> Reload Project`. Now you can press the green play arrow at the top-right of the screen to start the application. Once the project restarts, read the logs to confirm a successful connection with your database.

Now that we have successfully created and connected our application to a database, we are ready to create our React app, and setup the frontend.

### **Creating The React App**

If you prefer, you can open Visual Studio Code for the next step, as it is the preferred editor for React development. Open the project the same way that you did with IntelliJ, and create a new terminal within the IDE. If you wish however, you may also remain within IntelliJ to complete the following step.

In your terminal, navigate to the `project/src/main directory`. Once you are within the `main` directory, run the following command, replacing:

```
$ npx create-react-app@latest client --use-npm
```

This will create a new `client` folder within your `main` directory, along with the starter files generated for your React app.

Locate and open the `package.json` file within the `client` directory. Once opened add the following line to the top level of the JSON object, before the `"dependencies"`:

```
"proxy": "http://localhost:8080",
```

This proxy will simplify the process of making requests to our backend API server on Port 8080. Additionally, you can now write your network requests like this in your React files:

```
fetch("/hello");
// rather than fetch("http://localhost:8080/hello")
```

After saving the `package.json` file, ensure that you are within the `client` directory in your terminal. If not, navigate to the `client` directory. Once you are within the directory, run the following command to install the packages:

```
$ npm install
```

Once installed, start the application by running the following command:

```
npm start
```

This should open the application in your browser at http://localhost:3000. If your React app starts successfully, you are now ready to test that your entire application is working properly.

## Testing Your Application

To test your application, first make sure that your Java Spring Boot server is still running on PORT 8080 (http://localhost:8080 in your browser), and that your React app is running on Port 3000 (http://localhost:3000 in your browser). We need to ensure that the network requests to our backend API are working properly. To do so, we need to create a `fetch` request within our main `App.js` file. Locate this file within the `client/src` directory and open it up. Replace the entire file with the following code snippet, which will import the `useState` and `useEffect` hooks, request data from our API using a fetch request (notice the syntax as a result of our proxy), and ultimately display the results on the screen:

```
import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';

function App() {
    const [response, setResponse] = useState(null);

    useEffect(() => {
        fetch('/hello')
            .then((resp) => resp.json())
            .then((data) => {
                setResponse(data);
            });
      }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {response}
        </p>
      </header>
    </div>
  );
}

export default App;
```

If you see the React logo, with "HelloWorld" underneath, you can be sure that you properly fetched the data from your Java Spring Boot API. At this point you have completed the initial setup of the application, and can begin constructing the rest of the app.
