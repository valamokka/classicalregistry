Classical Registry

This application is meant to keep records of classical periods, composers worked in those
classical periods, and the compositions they made.

**Running the application from terminal:**
1. Open the terminal
2. Choose a directory to clone the application's repository
3. Clone the repository: git clone https://github.com/valamokka/classicalregistry.git
(You need to have Git installed on your computer: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
4. Step into the root directory of the project <directory-where-you-cloned-repository>/classicalregistry
5. Type in the following command: java -jar classicalregistry.jar
6. Alternatively, if you have Maven installed on your computer, you can use this command: mvn spring-boot:run

The application starts.

**Running the application with docker:**
1. Open terminal
2. Pull the image from dockerhub: sudo docker pull valamokka/classical
3. Run the container: sudo docker run -p 8080:8080 valamokka/classical

Use swagger to see and test the endpoints:
http://localhost:8080/swagger-ui.html
