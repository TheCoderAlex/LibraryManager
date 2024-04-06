package com.library;

import com.library.io.Interaction;

public class Main {
    public static void main(String[] args) {
        Interaction.initLog();
        do {
            Interaction.banner();
        } while (Interaction.process(Interaction.getUserInput()));
    }
}
