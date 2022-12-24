package cw.train.cli;

import cw.train.ticket.Planet;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class PlanetChooser {
    private final Scanner scanner;
    public Planet ask(){
        while(true){
            String line = scanner.nextLine();
            try{
                return Planet.valueOf(line);
            }catch (Exception ex){
                System.out.println("Planet "+line+" not found. Enter correct planet");
            }
        }
    }
}
