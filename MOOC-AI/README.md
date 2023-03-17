# AI-Math-Learning

Building AI course project

## Summary

An app for teaching and a learning analyzer that improves and fits the assignments over time for individuals.

## Background:

The app tries to make teaching easier and inclusive while making learning more fun and effective.
The topic is intresting and relevant because quality learning should be a universal norm.

## Data and AI techniques:

Starting data would be a large amount of exercises in math and other subjects that have unambiguous questions.
The app should also need a 

## How is it used:

By students of all ages in school and after school. The program should propably use a neural network in learning to become more precise.
The full app would propably use several different techniques combined.

## Challenges:

It does not solve problems in learning, but it might give a red flag for the teacher to notice.

## What next:

The first step would be to create the app without the AI, then implement the AI to give better assignments.
After this it is just scaling up and adding subjects, assignments and stakeholders to the platform.
Another AI-implementation would be to create an AI that writes problems and assignments for the app.

## Where to start:

```

import random

# Initialize variables
grading = 5
difficulty = 4
past_gradings = []
min_grading = 4
max_grading = 10

while True:
    # Generate a random math problem based on the difficulty level
    x = random.randint(difficulty, difficulty*10)
    y = random.randint(difficulty, difficulty*10)
    operator = random.choice(['+', '-', '*', '/'])
    if operator == '+':
        answer = x + y
    elif operator == '-':
        answer = x - y
    elif operator == '*':
        answer = x * y
    else:
        answer = x // y
        x = answer * y

    # Prompt the user for an answer
    user_answer = input(f"What is {x} {operator} {y}? ")

    # Check if the answer is correct and update the grading
    if int(user_answer) == answer:
        grading = min(grading + 1, max_grading)
        print("Correct!")
    else:
        grading = max(grading - 1, min_grading)
        print(f"Wrong. The correct answer is {answer}.")

    # Add the current grading to the list of past gradings
    past_gradings.append(grading)

    # Update the difficulty level based on the current grading
    if grading >= 8:
        difficulty = 10
    elif grading >= 6:
        difficulty = 7
    else:
        difficulty = 4

    # Display the current grading and difficulty level
    print(f"Current grading: {grading}/10")
    print(f"Next assignment difficulty: {difficulty}")

```
