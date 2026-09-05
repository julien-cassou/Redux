# Redux — Marble Puzzle Game (Java)

A 2D tile-based marble puzzle game developed in Java with a custom graphical interface. Players navigate a marble through challenging grid layouts across three distinct levels, interacting with special tile mechanics to reach the target.

## 🎮 Features

- **Multi-Level Progression**: 3 handcrafted levels with increasing complexity and puzzle depth.
- **Interactive Tile System**: Various tile types triggering specific game physics and mechanics (ice, friction, teleporters, directional arrows, traps).
- **Custom Visual Assets**: Original art assets created and refined for an intuitive user interface.
- **Game State Management**: Complete lifecycle handling including restarts, movement counters, and victory detection.

## 🏗️ Technical Highlights

- **Object-Oriented Architecture**: Clean separation between board state, player entity, and modular tile behaviors via polymorphism.
- **Collision & Movement Logic**: Grid-based path resolution preventing invalid states or out-of-bounds errors.
- **Asset Pipeline**: Centralized sprite loading and cached rendering for responsive gameplay.

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- IntelliJ IDEA, Eclipse, or terminal with `javac`

### Running the Game
1. Clone the repository:
   ```bash
   git clone [https://github.com/ton-profil/Redux.git](https://github.com/ton-profil/Redux.git)
   cd Redux
   ```
2. Compile and run:
   ```bash
   javac -d bin $(find src -name "*.java")
   java -cp bin Main
   ```

## 🛠️ Built With
- **Language**: Java
- **GUI Framework**: Swing / AWT
