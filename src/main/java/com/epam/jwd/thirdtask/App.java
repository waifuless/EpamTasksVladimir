package com.epam.jwd.thirdtask;

import com.epam.jwd.thirdtask.model.Lexeme;
import com.epam.jwd.thirdtask.model.Sentence;

public class App {


    public static void main(String[] args) {
        Sentence sentence = new Sentence();
        sentence.addComponent(new Lexeme());
    }
}
