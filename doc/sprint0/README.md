# ![Checkmate icon](/src/main/resources/static/images/favicon.png) Checkmate 
 
Checkmate is a website designed and focused on the beginner and casual players of chess. Checkmate wants players to learn in a friendly environment and not feel frustrated or intimidated by the more competitive sides of chess. Checkmate wants players to have fun playing the game of chess!

## Installation
No installation required to use Checkmate. Users may create an account or play as a guest. Players can use any browser to join and play! 
If you would like to contribute see [#Building](#building).

## Building
Install [mongodb](https://www.mongodb.com/docs/manual/installation/). Launch mongodb to start up the database service.

Install [nodejs and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

Install [java](https://www.oracle.com/java/technologies/downloads/) 17 or later.

Clone the repository.

Install the maven dependencies `./mvnw install`

Run `./mvnw spring-boot:run`or directly run the CheckmateApplication to start up the backend application.

To run the React frontend application use the following commands.
```
cd src/frontend/
npm install
npm run dev
```
In the console it will display the link to the frontend application

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change. Features that are not within the focus of Checkmate will not be added!

All pull requests must meet all formatting styles, and pass all tests.

Please make sure to update tests as appropriate.
