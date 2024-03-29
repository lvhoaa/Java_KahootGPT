# KahootGPT

## Table of Contents

- [Project Overview](#project-overview)
- [Guide & Illustration](#guide--illustration)
- [Technologies](#technologies)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Project Overview 
KahootGPT is a Java project that leverages ChatGPT to solve Kahoot multiple choice questions. 

Key features:
- Integrated OCR (Optical Character Recognition) to extract words (questions, choices) from screen.
- Leveraged OpenAI's API to generate answers for Kahoot quizzes
- Supported user-friendly interface for usage and timely execution 

## Guide & Illustration 
1. At every new question, click Enter to start the process
2. Use Java Abstract Window Toolkit (AWT) Robot to capture the full screen, including questions and answer choices

![image](https://github.com/lvhoaa/KahootChatGPT/assets/87745938/80b3b774-913b-4268-9677-6153eecea146)


3. Apply OCR (optical character recognition) via Tesseract to extract the TEXT questions and choices from the screenshot
4. Based on this information, prompt ChatGPT by sending API requests to OpenAI
5. Get back answer as ChatGPT response

![image](https://github.com/lvhoaa/KahootChatGPT/assets/87745938/82da407a-61de-41da-b5c9-da466b502f3a)


6. Choose the correct answer on the screen using Java AWT

![image](https://github.com/lvhoaa/KahootChatGPT/assets/87745938/c0033cfb-fa18-4723-963f-e5fb482c89d0)

7. Repeat at each new question

## Technologies 
- Java AWT Robot: for capturing screens
- Tesseract: for OCR (optical character recognition) operations
- Java KeyListener interface: for initiating the process
- OpenAI's ChatGPT: for generating answers based on quiz prompts and choices

## Configuration
- Java KahootGPT project requires certain configurations to ensure proper functioning.
1. Config build with Maven: This project is built with Maven, thus, requires updating dependencies and related packages in pom.xml file. 
2. OpenAI API key configuration: To use the OpenAI API, you need to obtain an API key from OpenAI and configure it in the project. Obtain an API key from OpenAI by following their documentation and adding API Key to KahootGpt.java
   

## Contributing
- Contributions to this project are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request. For major changes, please discuss them first in the project's issue tracker.

## License
- This project is licensed under the MIT License. You can find the full license text in the LICENSE file.
