 # ![Checkmate icon](/src/main/resources/static/images/favicon.png) Checkmate 
  
 Checkmate is a website designed and focused on the beginner and casual players of chess. Checkmate wants players to learn in a friendly environment and not feel frustrated or intimidated by the more competitive sides of chess. Checkmate wants players to have fun playing the game of chess!

 # Features
 
  * Guest Play
  
  * Language Support

  * Timer mode
 
  * Themes and Local Customization

  * Custom Games

  * Game History/Replay

  * Lobby Chat


## Guest Play

<p align="center"> 
 
![GuestPlay](https://github.com/user-attachments/assets/4cc8461d-0535-476c-8073-f52f72ae87bf)
</p>

## Language Support

<p align="center"> 
 
![Language](https://github.com/user-attachments/assets/1e59b5bf-c0c7-45ff-b211-7bc2452065d0)
</p>

## Timer Mode
<p align="center"> 
 
![TimerMode](https://github.com/user-attachments/assets/43ee03a0-eb02-4bad-96d1-172b67c63caf)

</p>

## Themes and Local Customization
<p align="center"> 
 
 ![Themes](https://github.com/user-attachments/assets/48090c0a-8ab3-41ad-b45d-123731c7b18a)

</p>

## Custom Games
<p align="center"> 
 
 ![Custom Games](https://github.com/user-attachments/assets/33e9768d-6536-424d-ac60-821dde13563f)

 </p>

## Game History/Replay
<p align ="center">

![Game History](https://github.com/user-attachments/assets/947eca3a-cedf-4c44-aaf9-5e758635900b)

</p>

## Lobby Chat
<p align ="center">
 
![Lobby Chat](https://github.com/user-attachments/assets/71972af2-ab12-435f-abcd-4179d83459a4)

</p>
# Software Architecture 

<p align="center"> 
 
![System](https://github.com/user-attachments/assets/bc32b8d8-2426-4cc0-8b7a-f7c6a0eef67a)

</p>



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
If npm install fails try removing both package-lock.json and node_modules directory, and trying again

When running the console it will display the link to the frontend application.

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change. Features that are not within the focus of Checkmate will not be added!

All pull requests must meet all formatting styles, and pass all tests.

Please make sure to update tests as appropriate.
