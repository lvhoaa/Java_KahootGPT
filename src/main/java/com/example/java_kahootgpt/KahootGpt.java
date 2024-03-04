package com.example.java_kahootgpt;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import io.github.stefanbratanov.jvm.openai.ChatClient;
import io.github.stefanbratanov.jvm.openai.ChatCompletion;
import io.github.stefanbratanov.jvm.openai.ChatMessage;
import io.github.stefanbratanov.jvm.openai.CreateChatCompletionRequest;
import io.github.stefanbratanov.jvm.openai.OpenAI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class KahootGpt implements KeyListener {

    private ArrayList<ArrayList<Integer>> coordinates;
    private ArrayList<String> information; 
    private String prompt;
    private Tesseract tesseract;

    public KahootGpt() {
        information = new ArrayList<>();

        // Initialize the coordinates list in the constructor
        coordinates = new ArrayList<>();
        coordinates.add(new ArrayList<>(List.of(100, 0, 1600, 128)));
        coordinates.add(new ArrayList<>(List.of(70, 480, 500, 120)));
        coordinates.add(new ArrayList<>(List.of(670, 480, 500, 120)));
        coordinates.add(new ArrayList<>(List.of(70, 580, 500, 120)));
        coordinates.add(new ArrayList<>(List.of(680, 580, 500, 120)));

        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\bakin\\Downloads\\Tess4J\\tessdata");
    }

    public void start() {
        // Dummy frame to attach the key listener
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(0, 0);
        frame.setVisible(true);

        while (true) {
            // Keep application running
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Enter keycode: 10
        if (e.getKeyCode() == 10) {
            for (int i = 0;i < 5; i ++){
                try {
                    ArrayList<Integer> coordinate = coordinates.get(i);
                    Rectangle screenRect = new Rectangle(coordinate.get(0), coordinate.get(1), coordinate.get(2), coordinate.get(3));
                    BufferedImage capture = new Robot().createScreenCapture(screenRect);
     
                    // Save screenshot to a file
                    File outputFile = new File("screenshot" + i+ ".png");
                    ImageIO.write(capture, "png", outputFile);
    
                    String result = tesseract.doOCR(outputFile);
                    System.out.println(result);
                    information.add(result);
    
                } catch (AWTException | IOException | TesseractException ex) {
                    ex.printStackTrace();
                }
            }
            prompt = information.get(0) + " A. " + information.get(1) + " B. " + information.get(2) + " C. " + information.get(3) + " D. "+ information.get(4) + " \n Choose among the choices; Just give me the answer as a letter ";
            System.out.println(prompt);
            generateAnswerUsingChatgpt(prompt);
        }
    }

    public void generateAnswerUsingChatgpt(String prompt){
        OpenAI openAI = OpenAI.newBuilder("").build();
        ChatClient chatClient = openAI.chatClient();
        CreateChatCompletionRequest createChatCompletionRequest = CreateChatCompletionRequest.newBuilder()
            .model("gpt-3.5-turbo")
            .message(ChatMessage.userMessage(prompt))
            .build();
        ChatCompletion chatCompletion = chatClient.createChatCompletion(createChatCompletionRequest);
        System.out.println(chatCompletion);
        char ans = chatCompletion.choices().get(0).message().content().charAt(0);
        clickAnswerOnScreen(ans);
    }

    public void clickAnswerOnScreen (char ans){
        int coordX;
        int coordY ;
       
        if (ans== 'A'){ 
            coordX = 320; 
            coordY = 540;
        } else if (ans == 'B') {
            coordX = 920; 
            coordY = 540;
        } else if (ans == 'C') {
            coordX = 320; 
            coordY = 640;
        } else {
            coordX = 930; 
            coordY = 640;
        }

        try{
            Robot robot = new Robot();
            robot.mouseMove(coordX, coordY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e){
            e.printStackTrace();
        }
       
        
        
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

