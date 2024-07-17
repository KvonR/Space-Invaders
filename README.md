# Space Invaders Game - Object-Oriented Design

**Date:** December, 2024

## Introduction

This project is an object-oriented design for a Space Invaders game, implemented in Java. The main objective was to focus on the application of design patterns and principles such as SOLID. The game logic is fully developed, though rendering for objects and user input are represented by stubs.

![Untitled](Space%20Invaders%20Game%20-%20Object-Oriented%20Design%20f66e5e915406468f86e79cc373ad2020/Untitled.png)

## Design Patterns and Principles Implemented

### 1. Factory Pattern

Used to create instances of various game objects such as aliens, player ships, and bullets.

### 2. Chain of Responsibility

Implemented for handling different types of game events and actions, such as collision detection and scoring.

### 3. Singleton Pattern

Applied to manage the game state, ensuring there is only one instance of the game controller throughout the application.

### 4. Open/Closed Principle

Ensured that the system is open for extension but closed for modification by using abstract classes and interfaces.

### 5. Single Responsibility Principle

Each class has a single responsibility, making the codebase easier to understand, maintain, and extend.

## Project Structure and File Manifest

### Main.java

- **Pattern Implemented**: Supports Singleton pattern, Implements keylistener
- **Description**: Contains the main method to start the game.

### GameController.java

- **Pattern Implemented**: Singleton
- **Description**: Manages the overall game state and flow.

### Constants.java

- **Pattern Implemented**: Interface
- **Description**: Contains fundamental settings and values used by other classes.

### Entity.java

- **Pattern Implemented**: Abstract Class, Supports Chain of Responsibility
- **Description**: Base class for all game entities (Player, Alien, Bullet, Wall).

### Player.java

- **Pattern Implemented**: Supports Chain of Responsibility
- **Description**: Represents the player character in the game.

### Alien.java

- **Pattern Implemented**: Supports Chain of Responsibility
- **Description**: Represents alien characters in the game.

### Bullet.java

- **Pattern Implemented**: Supports Chain of Responsibility
- **Description**: Represents bullets fired by the player or aliens.

### Wall.java

- **Pattern Implemented**: Supports Chain of Responsibility
- **Description**: Represents walls that can be hit by bullets.

### AlienType.java

- **Pattern Implemented**: Enum
- **Description**: Enumerates types of aliens.

### EntityCollisions.java

- **Pattern Implemented**: Supports Chain of Responsibility
- **Description**: Represents a collision event between two entities.

### Events.java

- **Pattern Implemented**: Interface, Supports Chain of Responsibility
- **Description**: Defines methods for handling collision events in the game.

### Screen.java

- **Pattern Implemented**: Assignment 1 Manifest Kev Rahimi 2 Rendering
- **Description**: Manages the rendering of game entities on the screen.

## Responsibilities and Relationships

### Constants

- **Responsibilities**: Provide fundamental settings and values for other classes.
- **Data**: Various game constants like screen size, game speed, etc.

### Entity

- **Responsibilities**: Define common properties and methods for all game entities.
- **Data**: Position, size, velocity, state.

### Player

- **Responsibilities**: Handle player-specific actions and properties.
- **Data**: Health, score, position, movement.

### Alien

- **Responsibilities**: Handle alien-specific actions and properties.
- **Data**: Type, movement pattern, position.

### Bullet

- **Responsibilities**: Handle bullet-specific actions and properties.
- **Data**: Speed, direction, position.

### Wall

- **Responsibilities**: Handle wall-specific properties and interactions.
- **Data**: Durability, position.

### AlienType

- **Responsibilities**: Define different types of aliens.
- **Data**: Various alien types and their properties.

### EntityCollisions

- **Responsibilities**: Handle collision detection and response between entities.
- **Data**: Involved entities, collision logic.

### Events

- **Responsibilities**: Define methods for handling game events.
- **Data**: Event details, handling logic.

### GameController

- **Responsibilities**: Manage the overall game state and control game flow.
- **Data**: Singleton instance, game entities, game logic.

### Screen

- **Responsibilities**: Render game entities and handle screen updates.
- **Data**: Graphics context, rendering logic.

### Main

- **Responsibilities**: Initialize and start the game.
- **Data**: Main method, game initialization.

## Future Work

- **Rendering**: Improve the rendering system to make the game visually appealing. This includes adding assets for the player, aliens, walls. etc
- **Sound Effects**: Integrate sound effects for a more immersive experience.

## Conclusion

This project demonstrates the application of object-oriented principles and design patterns in the development of a Space Invaders game. The focus was on creating a robust, extensible, and maintainable codebase, with placeholders for rendering and input to be implemented in future iterations.
