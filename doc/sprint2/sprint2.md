sprint2.md:

Participants: Werner, Jaimin, Monica, Flavio

Notetaker: Flavio

For sprint 2 we have planned to work on the following four user stories for the duration of sprint 2:

Saving User Data: Players info can be saved between sessions, be it their stats or other choices.

Custom Games: Players can customize game settings, such as piece removal, time limits, or unique rule sets.

Timer Mode: Adds a time-based challenge to gameplay, enhancing engagement and improving users’ decision-making under time constraints.

Themes & Local Customization: Customization options for board themes, piece styles, and colors.

We also determined the amount of points via agile each user stories we selected for this sprint:

User Story Point Estimations

Saving User Data - 10 Points: Requires that the database for the website can correctly save, load, and overwrite data set for each user. Such a feature needs to be working on different operating systems like Linux, Windows, and MacOs.

Custom Games – 10 Points: Requires customization of game logic and UI to support rule variations and configurations.

Timer Mode – 8 Points: Adding a timer requires front-end and back-end changes to manage timing, potential game-state updates, and user notifications. The complexity is moderate to high due to the real-time component and UI considerations.

Themes & Local Customization – 8 Points: Implementing themes requires developing customizable UI components and providing options for theme selection (e.g., light and dark modes or color themes). Local customization may involve adapting the interface to accommodate different regional preferences, which adds another layer of complexity.

Preliminary Task Breakdown

Each user story will be divided into tasks to manage workflow effectively. We also need to assign tasks for the other project requirements. Initial task outlines are as follows:

Responsibilities

Each of these tasks will be assigned to a group member to handle during this sprint. If these tasks cannot be reasonably completed before the deadline on Nov 17th then these tasks will be moved for the 3nd sprint with the reason being added to their user story trello card. The tasks have been assigned to the following group members:

Custom Games: Monica

Themes & Local Customization: Flavio 

Timer Mode: Jaiminkumar

Saving User Data: Werner

The task tickets for Trello are as follows:

Task 06 Implement Custom Game Logic

Task 07 Implement Saving User Data

Task 08 Implement Themes & Visual Customization

Task 09 Implement Timer Mode

Stand ups:

[Novemeber 06, 2024] - Sprint 2 Stand Up 6

[Werner]
What did you work on since the last standup?

I worked on user authentication, enabling users who signup to have passwords, and editing user info to require a password.

What do you commit to next?

Fixing bugs related to CORS that cause issues with cookies and credentials on some systems. I need to implement a proper CORS configuration to allow the backend and frontend to communicate properly.

When do you think you'll be done

I should be done by the end of the day on Monday.

Do you have any blockers?

Need more knowledge on CORS and to enable good security practices with it. I need a better method of enabling it on the backend. 

[Flavio]

What did you work on since the last standup? I have worked on fixing some left over design issues and bugs on the translation component of the project and worked on the docs required for this sprint.

What do you commit to next?

I will commit to implementing the themes and customization features which were detailed in previous docs.

When do you think you'll be done

I should be done by the end of the week with the core feature working and with no major bugs or issues. 

Do you have any blockers? Currently there are no blockers, I will be discussing with my teammates on the  details of this feature and if requires the use or update of existing system.

[Monica]

What did you work on since the last standup?

I worked on fixing some bugs we later found related to game logic, specifically: pawn movements, and some other capturing bugs in special cases.

What do you commit to next?

Next I will be working on implementing special rules such as: pawn promotions, and fixing some king in check bugs, and castling.

When do you think you'll be done

3-4 days

Do you have any blockers?

No. 

[Jaimin]

What did you work on since the last standup?

I worked on fixing some UI elements related to chess UI. 

What do you commit to next?

Implementing the timer mode for user to time their game while keeping the guest play button for running a normal game.

When do you think you'll be done

Should be done in by weekend

Do you have any blockers?

None. 

[Novemeber 12, 2024] - Sprint 2 Stand Up 7

[Werner]

What did you work on since the last standup?

I fixed bugs related to CORS allowing all systems to accept responses on the frontend and backend. I added easier ways to set the server hosts for backend and frontend using environment variables.

What do you commit to next?

Saving and storing more user data such as preferred language, themes, wins, loses, and total games played 

When do you think you'll be done

I should be done latest on Friday 

Do you have any blockers?

Need to discuss with the group what type of data should be included. If wins/loses are added, need chess game implementation to be more complete with checking if the game was won or lost.

[Flavio]

What did you work on since the last standup? 

I completed implementing the themes and customization features as committed in the last sprint. I also started integrating these features into the main UI and conducted initial testing to ensure compatibility with the translation component.

What do you commit to next?

I commit to working on the docs to update them with the latest features and for the end of the sprint.

When do you think you'll be done?

I expect to update the docs and have them ready in a couple of days.

Do you have any blockers?

No blockers at the moment, but I need to check with the team to ensure compliance. 

[Monica]

What did you work on since the last standup?

I fixed issues with king not being able to capture and not placing itself in danger. I also implemented castling.

What do you commit to next?

I will be working on creating custom game modes where users can remove pieces at the start of the game, and try to fit in some other game modes if possible.

When do you think you'll be done

3 days

Do you have any blockers?

No. 

[Jaimin]

What did you work on since the last standup?

I worked on fixing the bug where timer was running without first move is being made.

Worked on console state message printing times up message once a player runs out of time.

What do you commit to next?

Refactor the enable timer mode UI and pieces captured display showing captured pieces separately. 

When do you think you'll be done

Should be done in by Friday.

Do you have any blockers?

None. 


[Novemeber 16, 2024] - Sprint 2 Stand Up 8

[Werner]

What did you work on since the last standup?

I worked on storing, getting, updating user data that includes information such as preferred language and themes, and some statistics such as wins, loses, and games played. Also fixed bugs to allow updating 
chessboard information so users see live changes consistently.

What do you commit to next?

Updating more user data when a player wins or loses a match, and adding to games played when they join a new match.

When do you think you'll be done

I should be done by the end of Saturday.

Do you have any blockers?

Need more reliable checking of when the game is over, specifically checkmate logic. 

[Flavio]

What did you work on since the last standup? 

I updated the docs for this sprint, created the burndown and schedule and the other required pdfs.

What do you commit to next?

I commit to working with the rest of the team to make sure everyone else is done their tasks (user stories) ensuring we fulfill everything for this sprint.

When do you think you'll be done?

I expect to be done by tomorrow, helping the others finish any remaining task.

Do you have any blockers?

No blockers at the moment.

[Monica]

What did you work on since the last standup?

I added custom modes for removing pieces at the start of the game. I also added 2 extra game modes for pawns only game and no pawns game.

What do you commit to next?

I will be looking into more special cases to do with certain pieces and do more testing. Will be continuously fixing any potential bugs as it comes up.

When do you think you'll be done

Today.

Do you have any blockers?

No. 

[Jaimin]

What did you work on since the last standup?

Worked on refining the UI for timer mode with dropdown menu. 

Fixed bugs for timer mode starting without first move being made.

What do you commit to next?

Check on updates.

When do you think you'll be done

Should be done in by Sunday

Do you have any blockers?

No

