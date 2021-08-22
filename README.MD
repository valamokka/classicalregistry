Classical Registry

This application is meant to keep records of classical periods, composers worked in those
classical periods, and the compositions they made.

**Running the application from terminal:**
1. Open the terminal
2. Step into the root directory of the project
3. Type in the following command: java -jar target/classicalregistry-0.0.1-SNAPSHOT.jar

The application starts.

**Running the application with docker:**
Open terminal
Pull the image from dockerhub: sudo docker pull valamokka/classical
Then run the container: sudo docker run -p 8080:8080 valamokka/classical

Use swagger to see and test the endpoints:
http://localhost:8080/swagger-ui.html
