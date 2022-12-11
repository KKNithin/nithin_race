# Nithin's Race (A version of Simon's Race) - Game Developed using Java & Java FX
> A version of Simon's Race &copy; Authored by Nithin Katta Kiranprakash (Student Number: 22200096)
### Basic requirements to run the game:
* Minimum Java JDK/JRE version 17.
* Maven version of 3.8.6 or latest.

### Below are the instructions of how to run the game:
* Run the Game:
  * Run the `mvn clean javafx:run` command in the root directory of the game.
* Run test cases:
  * Run the `mvn clean test` command in the root directory of the game to see the results of all the junit tests.
  * Run `mvn clean test -Dtest="<Test class name>"` command in the root directory of the game to see the result of 
    single test class.

### Rules of Nithin's Race:
* Board Size and player placement:
  1. Minimum board size of 4 rows and 4 columns.
  2. Minimum of 2 players should play the game.
  3. Only one player per lane, hence you can't have players more than number of columns.
  4. Players are placed automatically along with a color, on the lanes based on custom player placement algorithm.
  5. Level of difficulty can be chosen (Easy, Medium, Difficult) which increases the obstacles on board respectively.
  6. Each player shall start the game with 50 points initially.
* Trap Rules:
  1. Players can cross all the traps except - Fence, where they have to choose a direction either Right/Left or 
     Up/Down or Stay.
  2. Fire Trap - Player who falls on this trap shall start from the initial point where he started the game.
  3. Tar Pit Trap - Player shall miss his next chance and sits in the trap if he falls on it.
  4. Teleportation Tunnel - Player shall choose another player from the choice dialog to teleport to.
* Winner Announcement:
  1. Once a player reaches the green line he shall be announced as winner by displaying his name and score.
  2. A list for top 10 winners are also displayed in the same view.

## Test Scenarios:
#### Scenario: Player Basic Move Test (PlayerBasicMoveTest.java)
##### Run the command `mvn clean test -Dtest=PlayerBasicMoveTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
And no trap in his path
When player rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
Then player shall move to row position < i > and column position < j >

|  x  |  y  | Move Value | Direction Value |  i  |  j  |
|-----|-----|------------|-----------------|-----|-----|
|  7  |  4  |     1      |     FORWARD     |  6  |  4  |
|  7  |  4  |     2      |     FORWARD     |  5  |  4  |
|  7  |  4  |     3      |     FORWARD     |  4  |  4  |
|  7  |  4  |     4      |     FORWARD     |  3  |  4  |
|  3  |  5  |     1      |    BACKWARD     |  4  |  5  |
|  3  |  5  |     2      |    BACKWARD     |  5  |  5  |
|  3  |  5  |     3      |    BACKWARD     |  6  |  5  |
```

```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
And no trap in his path and hits upper or boundary of board
When player rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
Then player shall move to initial row position < i > and initial column position < j >

|  x  |  y  | Move Value | Direction Value |  i  |  j  |
|-----|-----|------------|-----------------|-----|-----|
|  7  |  4  |     4      |    BACKWARD     |  7  |  4  |
|  3  |  5  |     4      |  MISS_A_TURN    |  3  |  5  |
```

#### Scenario: Player falls on _Fire_ Trap (FireObstacleTest.java)
##### Run the command `mvn clean test -Dtest=FireObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
When player rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
And Player falls on Fire Trap at row position < p > and column position < q >
Then player shall move to his initial row position < i > and column position < j > where he started from.

|  x  |  y  |  p  |  q  | Move Value | Direction Value |  i  |  j  |
|-----|-----|-----|-----|------------|-----------------|-----|-----|
|  7  |  4  |  3  |  4  |     4      |     FORWARD     |  6  |  4  |
|  7  |  4  |  4  |  4  |     3      |     FORWARD     |  5  |  4  |
|  7  |  4  |  5  |  4  |     2      |     FORWARD     |  4  |  4  |
|  7  |  4  |  6  |  4  |     1      |     FORWARD     |  3  |  4  |
|  2  |  5  |  3  |  5  |     1      |    BACKWARD     |  4  |  5  |
|  2  |  5  |  4  |  5  |     2      |    BACKWARD     |  5  |  5  |
|  2  |  5  |  5  |  5  |     3      |    BACKWARD     |  6  |  5  |
|  2  |  5  |  6  |  5  |     4      |    BACKWARD     |  6  |  5  |
```

#### Scenario: Player falls on _Tar Pit_ Trap (TarPitObstacleTest.java)
##### Run the command `mvn clean test -Dtest=TarPitObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
When player rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
And Player falls on Fire Trap at row position < p > and column position < q >
When player tries to roll for the next time he shall not be eligible to roll as he is on Tar pit.
And for the subsequent time the player deems to be eligible to roll and shall take his move. 
Then player shall move to  row position < i > and column position < j >.

|  x  |  y  |  p  |  q  | Move Value | Direction Value |  i  |  j  |
|-----|-----|-----|-----|------------|-----------------|-----|-----|
|  7  |  4  |  3  |  4  |    4,1     |     FORWARD     |  2  |  4  |
|  7  |  4  |  4  |  4  |    3,2     |     FORWARD     |  2  |  4  |
|  7  |  4  |  5  |  4  |    2,3     |     FORWARD     |  2  |  4  |
|  7  |  4  |  6  |  4  |    1,4     |     FORWARD     |  2  |  4  |
|  2  |  5  |  6  |  5  |    1,4     |    BACKWARD     |  7  |  5  |
|  2  |  5  |  5  |  5  |    2,3     |    BACKWARD     |  7  |  5  |
|  2  |  5  |  4  |  5  |    3,2     |    BACKWARD     |  7  |  5  |
|  2  |  5  |  3  |  5  |    4,1     |    BACKWARD     |  7  |  5  |
```

#### Scenario: Player falls on _Teleportation Tunnel_ Trap (TeleportationObstacleTest.java)
##### Run the command `mvn clean test -Dtest=TeleportationObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player <player-1>  is at row position < x > and column position < y >
And other player <Player-2> is at row position < a > and column position < b > 
When <player-1> rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
And <Player-1> falls on Teleportation Tunnel at row position < p > and column position < q >
Then the two players shall interchange their positions.

| Player-1 | Player-2 |  x  |  y  |  a  |  b  |  p  |  q  | Move Value | Direction Value |  x  |  y  |  a  |  b  |
|----------|----------|-----|-----|-----|-----|-----|-----|------------|-----------------|-----|-----|-----|-----|
| Nithin-N | Chethan-C|  7  |  4  |  2  |  5  |  3  |  4  |     4      |     FORWARD     |  2  |  5  |  7  |  4  |
| Nithin-N | Chethan-C|  7  |  4  |  2  |  5  |  4  |  4  |     3      |     FORWARD     |  2  |  5  |  7  |  4  |
| Nithin-N | Chethan-C|  7  |  4  |  2  |  5  |  5  |  4  |     2      |     FORWARD     |  2  |  5  |  7  |  4  |
| Nithin-N | Chethan-C|  7  |  4  |  2  |  5  |  6  |  4  |     1      |     FORWARD     |  2  |  5  |  7  |  4  |
| Chethan-C| Nithin-N |  2  |  5  |  7  |  4  |  3  |  5  |     1      |    BACKWARD     |  7  |  4  |  2  |  5  |
| Chethan-C| Nithin-N |  2  |  5  |  7  |  4  |  4  |  5  |     2      |    BACKWARD     |  7  |  4  |  2  |  5  |
| Chethan-C| Nithin-N |  2  |  5  |  7  |  4  |  5  |  5  |     3      |    BACKWARD     |  7  |  4  |  2  |  5  |
| Chethan-C| Nithin-N |  2  |  5  |  7  |  4  |  6  |  5  |     4      |    BACKWARD     |  7  |  4  |  2  |  5  |
```

#### Scenario: Player encounters _Fence_ Trap (FenceObstacleTest.java)
##### Run the command `mvn clean test -Dtest=FenceObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
When player rolls Move dice and gets < Move Value >
And rolls a Direction dice and gets < Direction Value >
And Player encounters Fence Trap at row position < p > and column position < q > 
And chooses < Direction when obstructed> to move
Then player shall move to row position < i > and column position < j >.

|  x  |  y  |  p  |  q  | Move Value | Direction Value | Direction when obstructed |  i  |  j  |
|-----|-----|-----|-----|------------|-----------------|---------------------------|-----|-----|
|  7  |  4  |  5  |  4  |     4      |     FORWARD     |         Right - R         |  6  |  7  |
|  7  |  4  |  5  |  4  |     3      |     FORWARD     |         Right - R         |  6  |  6  |
|  7  |  4  |  5  |  4  |     2      |     FORWARD     |         Right - R         |  6  |  5  |
|  2  |  5  |  3  |  5  |     2      |    BACKWARD     |         Left - L          |  2  |  4  |
|  2  |  5  |  3  |  5  |     3      |    BACKWARD     |         Left - L          |  2  |  3  |
|  2  |  5  |  3  |  5  |     4      |    BACKWARD     |         Left - L          |  2  |  2  |
```

#### Scenario: Player Movement During Obstacles and Border (BorderAndObstacleTest.java)
##### Run the command `mvn clean test -Dtest=BorderAndObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
And there is a trap just above the player
When player rolls Move dice and gets 4
And rolls a Direction dice and gets FORWARD
And Player encounters Fence or Border and chooses < Direction when obstructed> to move
Then player shall move to row position < i > and column position < j >

|  x  |  y  | Direction when obstructed |  i  |  j  |
|-----|-----|---------------------------|-----|-----|
|  7  |  6  |     Right - R, Up - U     |  4  |  7  |
|  7  |  6  |           Left - L        |  7  |  2  |
```
#### Scenario: Player Movement During Player Obstruction and Fence (PlayerAndObstacleTest.java)
##### Run the command `mvn clean test -Dtest=PlayerAndObstacleTest` to see the output of below test scenarios
```
Given Board size of 8 rows and 8 columns
And player is at row position < x > and column position < y > 
And there is a trap just above the player
When player rolls Move dice and gets 4
And rolls a Direction dice and gets FORWARD
And Player encounters other player or Fence and chooses < Direction when obstructed> to move
Then player shall move to row position < i > and column position < j >

|  x  |  y  |  Direction when obstructed  |  i  |  j  |
|-----|-----|-----------------------------|-----|-----|
|  7  |  2  |      Right - R, Up - U      |  4  |  3  |
|  7  |  2  | Right - R, Up - U, Right - R|  5  |  4  |
```
#### Scenario: Saving excess number of players and their scores to file (SaveToFileTest.java)
##### Run the command `mvn clean test -Dtest=SaveToFileTest` to see the output of below test scenarios
```
Given 13 players with names and scores
When each player is inserted to the Top Score file 
And once all the players are inserted, the file shall be opened 
Then total number of players stored in the file is 10
```
