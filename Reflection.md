# Reflection of Assignment Two Part B

Write your reflection in this document.

*Note: You do not need to use any Markdown or html tags to organise your reflection. However, if you want to highlight any particular parts in this document using Markdown or html tags, you can refer to the [Markdown guideline](https://www.markdownguide.org/basic-syntax/).*

_________________
## My answer:
### 1. Which task(s) did you find the most challenging?
I found Task 5 to be the most challenging. It is not because the task itself was difficult, but because the process of applying the necessary logic to meet the requirements was quite complex. It required significant time and effort to refactor the existing code than other tasks, which were originally designed to support 4-digit codes for both `SinglePlayer` mode and `VSComputer` mode (with Easy, Medium, and Hard difficulty levels).

**What I needed to do:**
- Convert `EasyComputer` into a public abstract class, and create two subclasses: `FourDigitEasyComputer` and `SixDigitEasyComputer`.
- Override the abstract method `getSecretCode()` in both subclasses to generate the appropriate secret code depending on the option the player chooses to play with.
- Modify `GameManager` to include an option for players to select the number of digits in the secret code.
- Update `CodeValidation` to validate user input based on the number of digits chosen (Rename the validated methods for user’s input so that we know `isValidOptionForTwoChoices()` method is used to validate the GameMode and Digit Code options, and `isValidOptionForThreeChoices()` is used to validate the Game Level options).
- Adjust `GameManager`, `SinglePlayer`, and `VSComputer` to accommodate the fact that `EasyComputer` is now an abstract class.
- Implement logic in `SixDigitEasyComputer` to:
    - Read a list of 6-digit codes from the `hexadecimals.txt` file.
    - Validate those codes.
    - Randomly select a valid 6-digit code to be used as the secret code.

I also added error handling in `SixDigitEasyComputer` to throw a `FileNotFoundException` if `hexadecimals.txt` is missing. In that case, the method catches the exception and returns `null`. I experimented with two options for handling this scenario:

- **Option 1:**
    - In `GameManager`, the variable `digitOptionEasyComputer` can reference either `FourDigitEasyComputer` or `SixDigitEasyComputer`, since both are subclasses of `EasyComputer` and override the `getSecretCode()` method.
    - `FourDigitEasyComputer.getSecretCode()` returns a secret code via `generateSecretCode()`, while `SixDigitEasyComputer.getSecretCode()` reads from the file, validates the codes, and randomly selects one.
    - If the file is missing or invalid, the method returns `null`. In this case, in `GameManager`, the programme prompts an error message to inform the user, returns to the beginning, and restarts the game by calling the `start()` method.
    - Although this logic works, I was hesitant to use `getSecretCode()` directly in `GameManager` because that class should manage game flow only, not handle how or where secret codes are generated. I wanted to maintain separation of concerns.
- **Option 2 (Built up from option 1):**
    - I added a new method, `isSecretCodeAvailable()`, in `EasyComputer`, which simply checks whether `getSecretCode()` returns a non-null value.
    - In `GameManager`, I used `isSecretCodeAvailable()` instead of calling `getSecretCode()` directly. This helped preserve abstraction and reduced the risk of accidentally exposing secret codes or their source.

Once these structural and logic updates were complete, the remaining tasks involved validating user’s guess, checking bulls and cows for the 6-digit secret code, and displaying appropriate messages. While this seemed straightforward, given that I had implemented similar logic for 4-digit codes, I had to carefully consider how to:
- Use `isValidCode()` from `CodeValidation` for 4-digit codes or use `isValidCode()` from `HexaComputer.java` for 6-digit codes, both under the `SinglePlayer`.
- Decide whether to update the current `countBullsAndCows()` method to work for both 4-digit and 6-digit codes or write a separate method specifically for 6-digit validation.

Unfortunately, I later realised that the `isValidCode()` method in `HexaComputer.java` is `private`, and I was not allowed to modify that file. This forced me to copy and paste similar validation logic into `CodeValidation.java` so I could reuse it for validating the 6-digit guesses from the player. Although it introduced some redundancy, it was the only viable option within the project constraints.

_________________
### 2. Briefly describe the scenarios you have covered when testing the HexaComputer class.
When testing the `HexaComputer` class, these are cases that I tried to cover:
- **testNullCodeListThrowException()**: This test checks the behaviour when the programme cannot find the code list, resulting in a `null` value. In this case, the method should throw an `IllegalArgumentException`.
- **testEmptyCodeListThrowException()**: This test verifies that if the code list exists but is empty (i.e., contains no elements), the programme correctly throws an `IllegalArgumentException`.
- **testAllInvalidCodesThrowException()**: I tried to create a list including those invalid codes, which fail the validation checks within the `HexaComputer` class. As a result, the final validated code list is empty, and an `IllegalArgumentException` is thrown. The invalid cases I tested include:
    - Code has fewer than 6 digits: In this case, code only has 5 digits. Eg. “12345”.
    - Code has more than 6 digits: In this case, code has 7 digits. Eg. “1234567”.
    - Code has characters outside the hexadecimal range (from ‘a’ to ‘f’): Eg. “12345z” with ‘z’ character is invalid.
    - Code with a blank space in between digits/characters: Eg. “1234 5”, "abcd f".
    - Code with duplicated digits. Eg. “a12344”.
    - Code with duplicated characters. Eg. “aa1234”.
    - Code with special characters (including the arithmetic operators such as + or -). Eg. “@12345”, “+12345”, “-12345”.
    - Code with all UPPERCASE letters or mixed UPPERCASE, lowercase and digits. Eg. “ABCDEF”, “ABC123”, "Abc123".
- **testAllValidCodesPassValidation()**: I tried to create a list including those valid codes (6-digit secret code like “012345” and “456789”, 6-character secret code like “abcdef”, and mixed 6-character/digit secret code like “123abc”). All of these passed the validation checks in the `HexaComputer` class and were added to the validated code list. The `getCode()` method successfully retrieves the codes based on their indices.
- **testValidCodesMixedInvalidCodes()**: This test uses a mixed list of valid and invalid codes: "@12345", "012345", "12345", "456789", "1234567", "abcdef", "12345z", "123abc". Out of these, only four codes ("012345", "456789", "abcdef", and "123abc") pass the validation and are added to the validated list. The `getCode()` method can retrieve these codes using index values from 0 to 3.
- **testGetCodeThrowIndexOutOfBoundsException()**: I used a list of valid and invalid codes: "edf456", "123abc", and "1234567". Only two of them ("edf456" and "123abc") are valid and get added to the list. Therefore, we can only retrieve the code based on the index respectively (index: from 0 to 1). I tested two scenarios:
    - Trying to retrieve a code using a negative index, e.g., -1, which throws an `IndexOutOfBoundsException`.
    - Trying to retrieve a code using index 2, which also throws an `IndexOutOfBoundsException` since only two valid codes exist at index 0 and 1.

_________________
### 3. If there are any bugs in Task Four, briefly explain the bugs and how you have identified such bugs.
There is one bug in **Task Four**, which the class doesn’t cover enough condition for the valid code. The requirement for `HexaComputer` class is to manage a list of hexadecimal codes, ensuring that only valid codes, which are exactly six unique characters and the allowed characters for the codes are numbers (0-9) and letters (a-f), are stored.

However, **on line 37 in `HexaComputer.java`**, the condition `else if ((c < 'a' || c > 'f')) { return false; }` incorrectly treats **any character outside the range 'a' to 'f' as invalid**. This means digits from 0 to 9 are also considered invalid, even though they should be allowed, causing any code containing numbers to be incorrectly rejected.

I recognised this bug when I was writing the unit test **testAllValidCodesPassValidation()**:
### **Screenshot 1:**
![Screenshot 1 - Finding the bugs in Task 4.png](Screenshot%201%20-%20Finding%20the%20bugs%20in%20Task%204.png)
- When I created a list including all valid codes (“012345”, “456789”, “abcdef”, “123abc”) based on the task’s requirement and pass that list to the object creation of `HexaComputer`.
- However, the test was failed with `AssertionFailedError`, showing **“Expected: 012345” and “Actual: abcdef”**. It means that not only “012345” but also “456789” didn’t pass the validation inside `HexaComputer` class. Only the third element “abcdef” was accepted and added as the first element in the validated code list.


### **Screenshot 2:**
![Screenshot 2 - Finding the bugs in Task 4.png](Screenshot%202%20-%20Finding%20the%20bugs%20in%20Task%204.png)
- I decided to use the same list as **Screenshot 1** but rearranged the position of the valid code “abcdef” appeared first (“abcdef”, “012345”, “456789”, “123abc”).
- However, the test was failed with `IndexOutOfBoundsException: Invalid index!` and the error happens **at line 47 of the `TestHexaComputer.java`**. This error occurred because the test attempted to access an element at index 1, which didn't exist in the validated code list. This confirmed that "012345", "456789", and "123abc" were all rejected during validation.

**=> Therefore, there is something wrong in the condition to include the valid code that can have any numbers between 0 and 9.**

After fixing the condition (The condition now should be: `else if ((c < ‘a’ || c > ‘f’) && (c < ‘0’ || c > ‘9’)) {return false;}`, 2 test cases in **Screenshot 1** and **Screenshot 2** are now passed:

### **Screenshot 3**
![Screenshot 3 - Finding the bugs in Task 4.png](Screenshot%203%20-%20Finding%20the%20bugs%20in%20Task%204.png)
### **Screenshot 4**
![Screenshot 4 - Finding the bugs in Task 4.png](Screenshot%204%20-%20Finding%20the%20bugs%20in%20Task%204.png)

_________________
### 4. What changes did you make from Part A to Part B?
On May 7th, I cloned all the code from Assignment 2A into the Part B folder. Before starting the tasks for Part B, I attended a tutor session and asked Jordan to review my code for any potential improvements.

One of the main changes I made based on Jordan's feedback was refactoring how the user’s guess is processed. Originally in Part A, I created a `GuessHandler` class that contained a single static method, `handleGuessProcess()`, used by both `SinglePlayer` and `VSComputer` to avoid code duplication. Jordan suggested that instead of having a separate class with only one method, I should convert `GameModeStrategy` from an interface into a **public abstract class**, and move the `handleGuessProcess()` method into it. Both `SinglePlayer` and `VSComputer` now extend `GameModeStrategy`, which allows them to inherit this shared logic. This change also allowed me to remove the `GuessHandler` class entirely, making the second UML diagram cleaner, especially in the top-right section where this interaction is shown:
![Nhu (Nikko) Pham - Assignment2B_Task1_UML Diagram (Part B)_Updated.drawio.png](Nhu%20%28Nikko%29%20Pham%20-%20Assignment2B_Task1_UML%20Diagram%20%28Part%20B%29_Updated.drawio.png)

While working on my UML diagrams for **Task One**, another important change was related to the `generateSecretCode()` method. Initially, it was declared as an abstract method inside `Player`, which required both `HumanPlayer` and `Computer` to override it. Since only the `Computer` class (and its subclasses) actually uses this method, I moved it to `Computer` and marked it as `protected`. This makes the codebase cleaner by removing unnecessary overrides in `HumanPlayer`, and better reflects the actual responsibility for generating secret codes.
- In `SinglePlayer` mode, if the player chooses to play with a 4-digit code, the `FourDigitEasyComputer` will call `getSecretCode()`, which internally calls the inherited `generateSecretCode()` method.
- In `VSComputer` mode, no matter if the opponent is an instance of `FourDigitEasyComputer`, `MediumComputer`, or `HardComputer`, each subclass can still call `generateSecretCode()` from the `Computer` superclass to generate the appropriate secret code.

_________________
### 5. Comparing the initial design you created for Part A with your final implementation, how different are they? Why did you change your design?
### 6. Which part(s) of your design (final class diagram) and/or implementation is good? (What makes them good? If you do not think your design / implementation is good, explain what and how you could improve.Please also specify the class(es), method(s) and/or line number(s)).
My final implementation differs significantly from the initial design in Part A, especially in terms of structure and the number of classes. **The final version includes significantly more classes than my first design.**

**One of the biggest improvements was the decision to replace the original ENUM-based approach for GameMode (e.g., `SinglePlayer`, `VSComputer`) and GameLevel (e.g., `EASY`, `MEDIUM`) with separate classes.** 
- These now inherit from their respective parent classes: `SinglePlayer` and `VSComputer` extend from `GameModeStrategy` (which I changed from an interface to an abstract class in Part B), and `EasyComputer`, `MediumComputer`, and later `HardComputer` inherit from the `Computer` superclass. 
- This shift was based on Jordan’s feedback, and I’m really grateful for that advice. As I progressed through the assignment, I realised how much easier it became to add new features—such as introducing `HardComputer` or allowing the game to support a 6-digit secret code.

**This improvement also reflects good separation of concerns: Each class has a clear and distinct responsibility.**
- As a result, adding new features doesn’t require major changes to existing code. For example, if I want to add a new difficulty level or allow `SinglePlayer` to support a 6-digit code, I can do that without affecting the functionality of existing components like `EasyComputer` and `MediumComputer`. 
- Even the `GameLog` and `GameManager` features I originally built in Part A to ask players whether they want to save their game results still work as expected with the new features (adding `HardComputer` or playing with 6-digit code) in Part B, despite the fact that saving results wasn’t a requirement in this part of the assignment.

**That said, one limitation I encountered was not being allowed to modify `HexaComputer.java`. This restriction made it harder to reuse some logic cleanly.** 
- As a result, I had to duplicate some of the validation logic for 6-digit codes that already existed in `HexaComputer.java` (from line 30 to 46) into `CodeValidation.java` (from line 62 to 78) **(Mentioned in the answer for Question 1)**. This redundancy is not ideal and slightly weakens the overall quality of my implementation.
- Additionally, because I couldn’t add new methods like `getSize()` in `HexaComputer.java` to retrieve the size of the validated codes list, I had to implement extra code inside my `SixDigitEasyComputer` class. Specifically, I needed to calculate the total number of validated codes manually in order to perform a random selection of the secret code after validation. This workaround added unnecessary complexity and further highlights the limitations caused by the inability to modify `HexaComputer.java`.

_________________
### 7. If you used any external resources such as Generative AI tools, how well did you think the resources have supported you to complete this assignment? How different do you think your work might look like without any use of external resources? Also, list any external resources you have used.
Similar as I mentioned in Part A of Assignment 2, I continued to use ChatGPT as a Generative AI tool to support my learning in Part B as well. For example, when I wrote code that seemed logically correct but still had bugs I couldn’t easily identify, I would quickly consult ChatGPT. It helped me discover small but critical issues that I might have otherwise missed, preventing potential breakdowns in the code.

The external resources I used for Part B of Assignment 2 include:
- **Lecture recordings and slides:** I consistently reviewed lessons, revisited my annotated slides, and referenced examples shared in lectures. I adapted and modified these examples to fit my coding scenarios, which helped me better understand the concepts and apply them correctly in the assignment.
- **Week 8 - Tuesday lecture by Yu-Cheng:** I re-watched the session where Yu-Cheng introduced Assignment 2B and demonstrated **Task 3 – Hard AI**. Based on her detailed explanation of how the **HardAI** example works step-by-step, I was able to gradually build the logic needed to solve **Task 3**. This clear walkthrough helped me understand the process thoroughly, which in turn guided me to design my UML diagram with the necessary fields and methods to implement that logic effectively.
- **Lab exercises:** I reviewed Lab 12 to study the example test case provided for `RobotTest`. By examining how the test was written and what cases it covered, I was able to learn effective testing techniques. I then applied this understanding to **Task 4** by writing tests for `HexaComputer.java` based on those principles.
- **IntelliJ with AI assistant for code writing:** As I mentioned in Assignment 2A, IntelliJ continued to be a valuable assistant. Beyond auto-completing code snippets and speeding up my coding, it also highlighted duplicated code sections, helping me stay aware and avoid redundancy. Additionally, IntelliJ suggested when some fields I created were unnecessary and recommended using local variables instead, which helped me write cleaner and more efficient code.

Without these external resources above, I believe completing **Tasks 3, 4 and 5** would have taken me significantly longer. Moreover, my code would likely be longer and less clean, which is an ongoing concern for me. I understand that writing clean, maintainable code is important, especially when future updates or debugging might be needed.

_________________
### 8. If you have time to extend this assignment, what would you do?
If I have time to extend this assignment, I have some ideas that I would do:
1. **Enhancing the UX/UI of the game:**
   - **User Interface:** I would use Java Swing, which was taught in class, to build a simple graphical user interface (GUI) for the Bulls and Cows game.
   - **User Experience:**
     - With a GUI and buttons, users could easily select game modes, difficulty levels, and other options instead of relying on keyboard inputs like pressing 1, 2, or 3.
     - Improve feedback messages for invalid guesses by separating them in different scenarios, such as when a user enters duplicate characters/digits or when the guess length is shorter or longer than expected.
2. **Adding a Time Challenge mode:** For example, giving the player 60 seconds and 7 attempts to guess the secret code in `SinglePlayer` mode.
3. **Implementing a Leaderboard feature:** Track and display records for fastest wins or fewest attempts to complete the game.
