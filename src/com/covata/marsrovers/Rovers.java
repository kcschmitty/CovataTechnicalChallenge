package com.covata.marsrovers;

public class Rovers
{
    int xPos;
    int yPos;
    char direction;
    int xMax;
    int yMax;
   
    public Rovers (int x, int y, char dir)
    {
        xPos = x;
        yPos = y;
        direction = dir;
    }
   
    public void setPlateau (int x, int y)
    {
        xMax = x;
        yMax = y;
    }
   
    public void command (char command)
    {
        switch (command) {
        case ('M'):
            move();
            break;
        case ('L'):
            rotateLeft();
            break;
        case ('R'):
            rotateRight();
            break;
        }
    }
   
    public void rotateRight ()
    {
        switch (direction) {
            case('N') :
                direction = 'E';
                break;
            case('S') :
                direction = 'W';
                break;
            case('E') :
                direction = 'S';
                break;
            case('W') :
                direction = 'N';
                break;
            default :
                break;
        }
    }
   
    public void rotateLeft ()
    {
        switch (direction) {
            case('N') :
                direction = 'W';
                break;
            case('S') :
                direction = 'E';
                break;
            case('E') :
                direction = 'N';
                break;
            case('W') :
                direction = 'S';
                break;
            default :
                break;
        }
    }
   
    public void move ()
    {
        if (canMove(direction)) {
            switch (direction) {
                case('N') :
                    yPos++;
                    break;
                case('S') :
                    yPos--;
                    break;
                case('E') :
                    xPos++;
                    break;
                case('W') :
                    xPos--;
                    break;
                default :
                    break;
            }
        }
    }
   
    public void position ()
    {
        System.out.println(this.xPos + " " + this.yPos + " " + this.direction);
    }
   
    public boolean canMove (char dir)
    {
        switch (dir) {
            case('N') :
                return yPos != yMax;
            case('S') :
                return yPos != 0;
            case('E') :
                return xPos != xMax;
            case('W') :
                return xPos != 0;
            default :
                break;
        }
        return true;
    }
}