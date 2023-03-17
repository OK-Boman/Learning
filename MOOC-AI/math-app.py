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
