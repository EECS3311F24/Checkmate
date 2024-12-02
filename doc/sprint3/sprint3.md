sprint3.md:

Participants: Werner, Jaimin, Monica, Flavio

Notetaker: Flavio

For sprint 3 we have planned to work on the following four user stories for the duration of sprint 3:

User Story: Game Lobbies (Allow players to create lobbies when playing chess and to allow players to join in a lobby given space)

User Story: Lobby Chat (Allow players to chat with each other in a chat box on the chess board screen)

User Story: Game History/Replay (Allow players to view over the history of a game and to view moves done)

We also determined the amount of points via agile each user stories we selected for this sprint:

User Story Point Estimations

Game Lobbies - 8 Points: Involves lobby creation, real-time room joining, and user interface updates for online status.

Lobby Chat – 4 Points: Basic real-time chat functionality, with optional mute and session-based clearing.

Game History/Replay – 6 Points: Requires backend support for saving game data, replay functionality, and a user interface for viewing history and replays.

Preliminary Task Breakdown

Each user story will be divided into tasks to manage workflow effectively. We also need to assign tasks for the other project requirements. Initial task outlines are as follows:

Responsibilities

Each of these tasks will be assigned to a group member to handle during this sprint. If these tasks cannot be reasonably completed before the deadline on Dec 1st then these tasks will be given a reason for thier delay with that being added to their user story trello card. The tasks have been assigned to the following group members:


Task 10 Game Lobby Frontend (Flavio)

Task 11 Game Lobby Backend (Werner)

Task 12 Lobby Chat Frontend (Flavio)

Task 13 Lobby Chat Backend (Werner)

Task 14 Game History/Replay Frontend (Jaiminkumar)

Task 15 Game History/Replay Backend (Monica)

Stand ups:
Monica
[Tuesday, November 19, 2024] - Sprint 3

    What did you work on since the last standup? 
    Looked into cloneable interface and drew UML for how to implement game history
    
    What do you commit to next?
    I plan on implementing the game history within ChessBoard class
    
    When do you think you'll be done?
    I expect to be done in 2 days.
    
    Do you have any blockers?
    No blockers at the moment.


[Tuesday, November 26, 2024] - Sprint 3

    What did you work on since the last standup? 
    Decided on using cloneable interface and implement ChessBoard and ChessPieces to this interface. Overrided the clone() methods for all related classes.
    
    What do you commit to next?
    I plan on connecting ChessBoard states to the Chess class and store game history board states within Chess class so users can call for it directly via Chess.
    
    When do you think you'll be done?
    I expect to be done within 2 days.
    
    Do you have any blockers?
    No blockers at the moment.



[Friday, November 29, 2024] - Sprint 3

    What did you work on since the last standup? 
    I finished the logic for game history and connected Chess to ChessBoard to get the chessboard states for game history. Game history should be working.
    
    What do you commit to next?
    I plan on doing more testing to ensure everything is working as intended and update CRC cards.
    
    When do you think you'll be done?
    I expect to be testing for another day and finish CRC cards by Saturday.
    
    Do you have any blockers?
    No blockers at the moment.

Flavio

[Tuesday, November 19, 2024] - Sprint 3

    What did you work on since the last standup? 
    Looked into any bugs with the translation and theme providers.
    
    What do you commit to next?
    I plan to resolve these bugs and improve the code readability.
    
    When do you think you'll be done?
    I expect to be done in 3 days.
    
    Do you have any blockers?
    No blockers at the moment.


[Tuesday, November 26, 2024] - Sprint 3

    What did you work on since the last standup? 
    Dealt with some edge cases for the theme provider where the selected theme got overriden during a refresh.
    
    What do you commit to next?
    I plan on implementing the chatbox into the project, connecting it to the backend code and chess game.
    
    When do you think you'll be done?
    I expect to be done within 4 days.
    
    Do you have any blockers?
    Currenlty dealing with a final exam on thursday so I will be focused on that for time being.



[Friday, November 29, 2024] - Sprint 3

    What did you work on since the last standup? 
    I finished the basic logic for the chatbox, added it to the website.
    
    What do you commit to next?
    Plan right now is checking for any bugs or issues with the chatbox and updating the docs.
    
    3.When do you think you'll be done?
    I expect it to take a day and finish my documents by Saturday, Sunday at the latest.
    
    Do you have any blockers?
    None at the moment of writing.
    
    Do you have any blockers?
    No blockers at the moment.

Werner

[Tuesday, November 19, 2024] - Sprint 3

    What did you work on since the last standup? 
    Worked on starting the backend code for chat messages so that players can send and receive chats and view them at a later data.
    
    What do you commit to next?
    I plan on connecting the frontend and backend chat code so that the frontend uses that chat message data from the backend.
    
    When do you think you'll be done?
    I expect to be done in 2 days.
    
    Do you have any blockers?
    Just need the frontend gui to be implemented so I can better display the chat messages as well as send new ones


[Tuesday, November 26, 2024] - Sprint 3

    What did you work on since the last standup? 
    Finished connected the frontend and backend chat messages to the database. Users can now send and recieve chat messages and view them at a later date.
    
    What do you commit to next?
    I plan on fixing chess database bugs that cause inconsistent retrieving of data from the database.
    
    When do you think you'll be done?
    I expect to be done within 1 day.
    
    Do you have any blockers?
    Difficulty finding what is causing the issue that only causes these issues to occur when storing/retrieving from the database. I need to spend a lot of time debugging this issue.



[Friday, November 29, 2024] - Sprint 3

    What did you work on since the last standup? 
    I fixed the database retrieving inconsistent chess board data. Set refreneces such that it was consistent when updating moves, so all values are updated like from chess controller.
    
    What do you commit to next?
    I plan on completing gamelobby backend and polishing up how gamel lobbies are created and stored in the database
    
    When do you think you'll be done?
    I expect to be done in 1 day.
    
    Do you have any blockers?
    No blockers at the moment.

Jaimin

[Tuesday, November 19, 2024] - Sprint 3

    What did you work on since the last standup?
    Implemented the GameHistory component to fetch recent moves from the database.

    What do you commit to next?
    Finalize integration with the replay feature.

    When do you think you'll be done?
    By Friday, November 22, 2024.

    Do you have any blockers?
    Need updated API documentation.

[Tuesday, November 26, 2024] - Sprint 3

    What did you work on since the last standup?
    Completed integration and started testing the replay feature.

    What do you commit to next?
    Work on UI improvements for GameHistory.

    When do you think you'll be done?
    By Thursday, November 28, 2024.

    Do you have any blockers?
    Need updated CSS styling guidance from Monica.

[Friday, November 29, 2024] - Sprint 3

    What did you work on since the last standup?
    Improved UI for the GameHistory and replay controls.

    What do you commit to next?
    Complete usability testing and incorporate feedback.

    When do you think you'll be done?
    By Sunday, December 1, 2024.

    Do you have any blockers?
    Waiting for team feedback on UI layout.
