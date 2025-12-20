# # Flappy Bird - Design Patterns Project

A Java implementation of the classic Flappy Bird game demonstrating multiple software design patterns including Singleton, Decorator, Factory, State, and Composite patterns.

## 🎮 Game Overview

This is a fully functional Flappy Bird clone built with Java Swing, featuring:
- Classic Flappy Bird gameplay mechanics
- Power-up system with visual effects
- Score tracking with double points bonus
- Collision detection
- Game over and restart functionality

## 🏗️ Architecture & Design Patterns

### 1. **Singleton Pattern**
- **Class**: `Game.java`
- **Purpose**: Ensures only one game instance exists throughout the application
- **Implementation**: Private constructor with static `getInstance()` method

### 2. **Decorator Pattern**
- **Base**: `PlayerDecorator.java`
- **Concrete Decorators**:
  - `DoublePoints.java` - Doubles score points with golden visual effect
  - `SpeedBoost.java` - Enhanced jump/flap strength with cyan visual effect
  - `ShieldBoost.java` - Protection from collisions with blue visual effect
- **Purpose**: Dynamically adds abilities to the player without modifying the base Player class

### 3. **Factory Pattern**
- **Abstract Factory**: `AbstractFactory.java`
- **Concrete Factories**:
  - `EnemyFactory.java` - Creates enemy entities
  - `ObstacleFactory.java` - Creates obstacle entities
  - `PowerUpFactory.java` - Creates power-up entities
- **Purpose**: Encapsulates object creation logic

### 4. **State Pattern**
- **Game States**:
  - `GameState.java` (interface)
  - `MenuState.java`
  - `PlayState.java`
  - `PauseState.java`
  - `GameOverState.java`
- **Player States**:
  - `PlayerState.java` (interface)
  - `PlayerIdleState.java`
  - `PlayerJumpingState.java`
  - `PlayerFallingState.java`
  - `PlayerDeadState.java`
- **Purpose**: Manages different behavioral states of the game and player

### 5. **Composite Pattern**
- **Component Interface**: `GameComponent.java`
- **Implementations**:
  - `EntityGroup.java` - Groups multiple game entities
  - `Level.java` - Represents a game level with multiple components
- **Purpose**: Treats individual objects and compositions uniformly

## 📁 Project Structure

```
DesignProject/
├── src/
│   ├── SwingGameApp.java          # Main application entry point
│   ├── controller/
│   │   ├── GameController.java    # Main game loop and logic
│   │   ├── CollisionController.java
│   │   └── InputController.java
│   ├── model/
│   │   ├── Game.java              # Singleton game instance
│   │   ├── Score.java             # Score tracking
│   │   ├── composite/             # Composite pattern
│   │   │   ├── GameComponent.java
│   │   │   ├── EntityGroup.java
│   │   │   └── Level.java
│   │   ├── decorator/             # Decorator pattern
│   │   │   ├── PlayerDecorator.java
│   │   │   ├── DoublePoints.java
│   │   │   ├── SpeedBoost.java
│   │   │   └── ShieldBoost.java
│   │   ├── entities/              # Game entities
│   │   │   ├── Entity.java
│   │   │   ├── Player.java
│   │   │   ├── Pipe.java
│   │   │   ├── Enemy.java
│   │   │   ├── Obstacle.java
│   │   │   └── PowerUp.java
│   │   ├── factories/             # Factory pattern
│   │   │   ├── AbstractFactory.java
│   │   │   ├── EnemyFactory.java
│   │   │   ├── ObstacleFactory.java
│   │   │   └── PowerUpFactory.java
│   │   └── state/                 # State pattern
│   │       ├── GameState.java
│   │       ├── MenuState.java
│   │       ├── PlayState.java
│   │       ├── PauseState.java
│   │       ├── GameOverState.java
│   │       └── player/
│   │           ├── PlayerState.java
│   │           ├── PlayerIdleState.java
│   │           ├── PlayerJumpingState.java
│   │           ├── PlayerFallingState.java
│   │           └── PlayerDeadState.java
│   └── util/
│       ├── Config.java            # Game configuration constants
│       └── LoggerManager.java     # Logging utility
├── pom.xml                        # Maven configuration
├── play.sh                        # Build and run script (Unix/Linux)
└── README.md                      # This file
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Building the Project

#### Using Maven:
```bash
mvn clean compile
```

#### Using the provided script (Unix/Linux/Git Bash):
```bash
./play.sh
```

### Running the Game

#### Using Maven:
```bash
mvn exec:java
```

#### Using compiled classes:
```bash
java -cp target/classes SwingGameApp
```

#### Using the JAR file:
```bash
mvn package
java -jar target/flappy-bird-game-1.0-SNAPSHOT.jar
```

## 🎮 How to Play

### Controls
- **SPACE** - Flap/Jump (make the bird fly upward)
- **R** or **SPACE** (after death) - Restart the game

### Gameplay
1. Press SPACE to make the bird flap and avoid pipes
2. Navigate through the gaps between pipes
3. Collect power-ups for special abilities:
   - **Golden (2x)** - Double points for 8 seconds
   - **Cyan (SPEED)** - Enhanced jump strength
   - **Blue (SHIELD)** - Protection from one collision
4. Score points by passing through pipes
5. Avoid hitting pipes or the ground

### Power-Ups
Power-ups spawn automatically during gameplay:
- **Double Points** (Golden): Doubles all points earned for 8 seconds
- **Speed Boost** (Cyan): Increases flap strength for better control
- **Shield Boost** (Blue): Provides temporary invincibility

## 🔧 Configuration

Game parameters can be adjusted in `src/util/Config.java`:

```java
// Window settings
WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600
GROUND_Y = 550

// Bird physics
GRAVITY = 0.3f
FLAP_STRENGTH = -8f
MAX_VELOCITY = 7f

// Pipe settings
PIPE_WIDTH = 52
PIPE_GAP = 140
PIPE_SPEED = 3f
```

## 🎨 Visual Features

- **Smooth animations** at 60 FPS
- **Visual effects** for power-ups (glowing halos)
- **Color-coded power-ups** for easy identification
- **Game over overlay** with score display
- **Real-time score tracking**

## 🧪 Testing

Run unit tests with Maven:
```bash
mvn test
```

## 📝 Design Pattern Benefits

1. **Singleton (Game)**: Ensures consistent game state across all components
2. **Decorator (Player abilities)**: Easily add/remove player abilities at runtime without modifying core Player class
3. **Factory (Entity creation)**: Centralized entity creation makes it easy to add new entity types
4. **State (Game/Player states)**: Clean separation of behavior based on current state
5. **Composite (Entity groups)**: Uniform treatment of individual entities and groups

## 🛠️ Technologies Used

- **Java 11** - Programming language
- **Java Swing** - GUI framework
- **Maven** - Build and dependency management
- **JUnit 4.13.2** - Unit testing framework

## 📦 Maven Dependencies

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

## 🎯 Key Features Implementation

### Collision Detection
- Rectangle-based collision detection between player and pipes
- Boundary collision detection (ground and ceiling)
- Power-up collection detection

### Score System
- Base scoring: +1 point per pipe passed
- Double points decorator: +2 points per pipe when active
- Reflection-based decorator chain traversal for bonus detection

### Game Loop
- 60 FPS game loop using separate thread
- Delta time-based updates
- Automatic pipe spawning every 120 frames

## 🐛 Known Issues

None currently. The game is fully functional and playable.

## 🔮 Future Enhancements

Potential improvements:
- Add difficulty levels
- Implement high score persistence
- Add sound effects and background music
- Create additional power-up types
- Add particle effects
- Implement mobile touch controls

## 👨‍💻 Development

### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- Keep methods focused and single-purpose
- Document complex logic with comments

### Adding New Power-Ups
1. Create a new decorator class extending `PlayerDecorator`
2. Implement the desired behavior modification
3. Add visual effects in the `render()` method
4. Update `GameController.checkPowerUpCollisions()` to handle the new type

## 📄 License

This project is created for educational purposes to demonstrate design patterns in Java.

## 🙏 Acknowledgments

- Original Flappy Bird game by Dong Nguyen
- Design patterns from "Design Patterns: Elements of Reusable Object-Oriented Software" (Gang of Four)

---

**Project**: Flappy Bird Design Patterns Demo  
**Version**: 1.0-SNAPSHOT  
**Java Version**: 11+  
**Build Tool**: Maven 3.x
