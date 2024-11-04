sprint1.md:

Participants: Werner, Jaimin, Monica, Flavio

As detailed in the PRM.md, for sprint 1 we have planed to work on the following user stories for the duration of sprint 1:

Guest Play
  Allows users to play a match of chess without creating an account, ensuring accessibility for casual users.

Multiple Language Support
  Provides language options for users, expanding inclusivity and accessibility for users who don’t speak the same language as the development team.

Timer Mode
  Adds a time-based challenge to gameplay, enhancing engagement and improving users’ decision-making under time constraints.

Tutorial on How to Play for Beginners
  Offers an interactive tutorial for new players, aligning with our mission to create a welcoming and educational environment for beginner chess players.

Themes & Local Customization
  Customization options for board themes, piece styles, and colors.

We also determined the amount of points via agile each user stories we selected for this sprint: 

User Story Point Estimations

  Guest Play – 3 Points: This is a small but critical feature that involves enabling guest login without account creation. It requires some backend work for user session management but isn’t overly complex.

  Multiple Language Support – 5 Points: Implementing multilingual support involves translating UI text and possibly managing language files. While it’s not particularly difficult, it does require time and 
  attention to detail, especially to ensure no UI issues arise with different language lengths.

  Timer Mode – 8 Points: Adding a timer requires front-end and back-end changes to manage timing, potential game-state updates, and user notifications. The complexity is moderate to high due to the real-time   component and UI considerations.

  Tutorial on How to Play for Beginners – 8 Points: This requires a guided, interactive experience for new users, with potential animations and instructional overlays. It’s complex and involves careful   
  planning to ensure accessibility and clarity for beginners.

  Themes & Local Customization – 8 Points: Implementing themes requires developing customizable UI components and providing options for theme selection (e.g., light and dark modes or color themes). Local 
  customization may involve adapting the interface to accommodate different regional preferences, which adds another layer of complexity.

Preliminary Task Breakdown

Each user story will be divided into tasks to manage workflow effectively. We also need to assign tasks for the other project requirements. Initial task outlines are as follows:

  Guest Play
    Design and develop guest access mode
    Set access limitations for guest users
    Testing and debugging guest functionality

  Multiple Language Support
    Identify and implement languages
    Create language selection interface
    Testing for language-specific UI consistency

  Timer Mode
    Design timer options (e.g., blitz, rapid)
    Implement timer mechanics
    Testing and adjustments based on feedback
    Tutorial on How to Play for Beginners

  Design tutorial flow and interactive elements
    Develop tutorial content with step-by-step instructions
    Testing tutorial interactions for clarity and usability
    
  Themes & Local Customization 
    Design a UI component allowing users to select their preferred theme.
    Ensure compatibility with the existing UI structure.

  Code Structure and Organization
    Describe the organization of the codebase, including folder structure and key modules.
    Write detailed documentation for core classes, modules, and functions.
    Outline any testing tools or frameworks and how to run tests locally.

  Project/Sprint Documentation
    Write a concise overview of the project, including objectives, goals, and key requirements (sprint and standups). 
    Review the entire document for clarity, consistency, and completeness.
    Edit for grammar, style, and formatting consistency.


Responsibilities 

Each of these tasks will be assigned to a group member to handle during this sprint, for the local customization / timer mode / tutorial tasks these will be assessed and determined if these can be completed during the 1st sprint. If these tasks cannot be reasonably completed before the deadline on Nov 3rd then these tasks will be moved for the 2nd sprint with the reason being added to their user story trello card. The tasks have been assigned to the following group members:

Project/Sprint Documentation: Flavio

Code Structure and Organization: Werner

Themes & Local Customization: Flavio (To assess feasibility) 

Tutorial on How to Play for Beginners: Flavio (To assess feasibility) 

Timer Mode: Flavio (To assess feasibility) 

Guest Play: Monica (Core Logic) & Jaimin (Visuals)

The task tickets for Trello are as follows: 

  01 Implement Chess Game Logic 
  
  02 Update Code Documentation
  
  03 Implement Multi Language Code for Front End
  
  04 Update Sprint and Sprint Release Documentation
 
  05 Create Chess Visuals

[2023-10-23] - Sprint #1 Standup #1

Flavio (Multiple Language Support)

Worked on: Set up the base structure for language files and researched language localization tools.

Next: Start integrating language selection into the UI and test basic language switches.

Completion Estimate: Two more standups to get the basic setup running.

Blockers: None at the moment.

Monica (Guest Play - Core Chess Code)

Worked on: Set up initial code for the chess engine and game rules.

Next: Continue working on basic game logic and user input for guest players.

Completion Estimate: About three more standups to finish core functionality.

Blockers: Some complexity in ensuring a seamless experience for guest players.

Jaimin (Visuals of Chess Game)

Worked on: Created initial design wireframes for the chessboard and player interface.

Next: Start implementing the visuals into the app using React components.

Completion Estimate: Likely by Standup #3.

Blockers: None currently.

Werner (Backend and Code Documentation)

Worked on: Set up backend structure and started drafting initial documentation for API calls.

Next: Continue fleshing out backend functionality to support game sessions for guest players.

Completion Estimate: Around Standup #4.

Blockers: None at this stage.


[2023-10-24] - Sprint #1 Standup #2

Flavio (Multiple Language Support)

Worked on: Integrated basic language selection functionality in the UI.

Next: Add more language options and test for edge cases with long texts.

Completion Estimate: Potentially by the next standup.

Blockers: Minor UI adjustments for certain language formats.

Monica (Guest Play - Core Chess Code)

Worked on: Completed initial logic for setting up a game session for guest users.

Next: Implement game state management and error handling.

Completion Estimate: End of the week.

Blockers: Small issues with game state persistence for guests.

Jaimin (Visuals of Chess Game)

Worked on: Added chessboard visuals and tested player interaction components.

Next: Start adding animations for piece movements.

Completion Estimate: Standup #4.

Blockers: None, moving smoothly.

Werner (Backend and Code Documentation)

Worked on: Developed backend endpoints to support guest play sessions.

Next: Document and test endpoints with Monica's guest play code.

Completion Estimate: Standup #3.

Blockers: Coordinating timing with Monica to integrate changes.

[2023-10-29] - Sprint #1 Standup #3

Flavio (Multiple Language Support)

Worked on: Added additional language files and refined UI to handle text adjustments.

Next: Begin testing with actual users to ensure smooth switching and comprehension.

Completion Estimate: Final adjustments by Standup #4.

Blockers: Minor issues with word overflow in certain languages.

Monica (Guest Play - Core Chess Code)

Worked on: Finalized game state handling and basic error prevention.

Next: Begin testing guest sessions and integrate with Werner’s backend code.

Completion Estimate: Should be done by Standup #4.

Blockers: No blockers; ready for integration.

Jaimin (Visuals of Chess Game)

Worked on: Integrated visual feedback for piece selected and placed on the board.

Next: Add a piece captured display for both players and improve UI.

Completion Estimate: Standup #4.

Blockers: None; visuals are progressing well.

Werner (Backend and Code Documentation)

Worked on: Integrated backend with Monica’s guest play code and performed initial tests.

Next: Finish up documentation and polish backend responses.

Completion Estimate: Standup #4 for documentation completion.

Blockers: No major blockers; just finalizing documentation.


[2023-10-31] - Sprint #1 Standup #4

Flavio (Multiple Language Support)

Worked on: Completed user testing and adjusted text sizes and positions based on feedback.

Next: Final review of language files and testing for any lingering bugs.

Completion Estimate: Should be complete by next standup.

Blockers: None; only minor tweaks remain.

Monica (Guest Play - Core Chess Code)

Worked on: Finalized integration with Werner’s backend and tested the full guest play feature.

Next: Perform usability testing and fix any bugs.

Completion Estimate: Likely complete by next standup.

Blockers: No blockers, just bug fixes.

Jaimin (Visuals of Chess Game)

Worked on: Captured pieces display, turn-based enforcement, and finishing up additional UI.

Next: Clear up remaining trello workplace tracking logs.

Completion Estimate: Will finish with final adjustments by next standup.

Blockers: None at this time.

Werner (Backend and Code Documentation)

Worked on: Finalized documentation for guest play APIs and completed code comments.

Next: Ensure the codebase is ready for review and assist in final testing.

Completion Estimate: Will finish by next standup.

Blockers: No blockers, documentation is wrapping up.


[2023-11-2] - Sprint #1 Standup #5

Flavio (Multiple Language Support)

Worked on: Final bug fixes and confirmed language support is functioning as expected.

Next: Wrap up the feature and prepare it for final review.

Completion Estimate: Done by end of day.

Blockers: None; feature is complete.

Monica (Guest Play - Core Chess Code)

Worked on: Completed final usability testing and resolved minor bugs.

Next: Prepare the guest play feature for final review.

Completion Estimate: Done by end of day.

Blockers: None; feature is complete.

Jaimin (Visuals of Chess Game)

Worked on: Addressed feedback on visuals and ensured animations are smooth.

Next: Final review and sign-off.

Completion Estimate: Done by end of day.

Blockers: None; visuals are ready.

Werner (Backend and Code Documentation)

Worked on: Finalized documentation and assisted with end-to-end testing.

Next: Final code review and sign-off.

Completion Estimate: Done by end of day.

Blockers: None; documentation and backend are complete.

