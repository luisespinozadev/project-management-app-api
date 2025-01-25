# Methodology and Architecture

The development process will follow the next practices:

## Test-Driven Development (TDD)

Test-Driven Development (TDD) is a software development methodology where you write tests before writing the actual code. 
The main cycle of TDD is:

1. **Write a test**: Define the expected behavior of a feature.
2. **Run the test**: It will fail initially because the code hasn’t been written yet.
3. **Write the code**: Implement the simplest code to pass the test.
4. **Refactor**: Clean up the code while ensuring the test still passes.
5. **Repeat**: Keep iterating, building small pieces of functionality at a time.

## Controller-Service-Repository pattern

The Controller-Service-Repository pattern is a software architecture pattern that separates concerns into three distinct layers:

1. **Controller**: Handles user input and delegates tasks to the service layer.
2. **Service**: Contains business logic and processes requests from the controller.
3. **Repository**: Manages data access, typically interacting with a database or external system.

This pattern promotes separation of concerns, making the application easier to maintain, test, and scale. 
It allows for clear organization of code, where each layer has a specific responsibility.