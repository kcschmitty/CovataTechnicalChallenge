package com.covata.marsrovers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarsRovers
{
    public static void main(String args[])
    {
        //Read file from command line, or use input file from project
        File input = null;
        if (args.length == 0) {
            input = new File("src/Files/input.txt");
        }
        else {
            input = new File(args[0]);
        }
        BufferedReader br;

        //read lines from file and put into list, then close the file
        List<String> lines = new ArrayList<String>();
        String currentLine = null;
        try {
            br = new BufferedReader(new FileReader(input));
            while ((currentLine = br.readLine()) != null) {
                lines.add(currentLine);
            }
            br.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
        //validate the file contents
        String error = validateInput(lines);
        if (error != null) {
            System.out.println(error);
            return;
        }
       
        //first, get the number of rovers
        int numRovers = (lines.size() -1) / 2;
       
        //second, get the upper right coordinates
        currentLine = lines.get(0);
        String[] split = currentLine.split(" ");
        int plateauX = 0;
        int plateauY = 0;
        try {
            plateauX = Integer.parseInt(split[0]);
            plateauY = Integer.parseInt(split[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("First line in input file must have two integers");
            return;
        }
       
        //move each rover
        for (int i = 1; i <= numRovers; i++) {
            //get rover position
            currentLine = lines.get(2*i-1);
            split = currentLine.split(" ");
            Rovers rover = null;
            char roverDirection = split[2].charAt(0);
            boolean validDir = roverDirection == 'N' || roverDirection == 'S' || roverDirection == 'E' || roverDirection == 'W';
            if (!validDir) {
            	System.out.println("The provided direction for the Rover must be N, S, E, or W");
            	return;
            }
            try {
            	rover = new Rovers(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2].charAt(0));
            }
            catch (NumberFormatException e) {
            	System.out.println("Rover coordinates must be integers");
            	return;
            }
            //tell rover where the edge is so it doesn't fall off
            rover.setPlateau(plateauX, plateauY);
            currentLine = lines.get(2*i);
            
            //issue commands to rover, if it receives an unknown command, it will do nothing
            for (int j = 0; j < currentLine.length(); j++) {
                char command = currentLine.charAt(j);
                rover.command(command);
            }
            rover.position();
        }
    }
   
    public static String validateInput (List input)
    {
        if (input.size() == 0) {
            return "Input file is empty";
        }
        //If size == 1, then there is a plateau but no rovers
        if (input.size() == 1) {
            return "No Rovers specified in input file";
        }
        //There should be an odd number of lines, otherwise there will ba a rover that will receive no commands
        if (input.size() % 2 != 1) {
            return "Input file does not have expected number of lines";
        }
        return null;
    }
}