import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Key here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Key extends Actor
{
    private boolean isDown;
    private String key;
    private String sound;
    private String notPressed;
    private String pressed;
    
    public Key(String keyName, String soundFile, String keyNotPressed, String keyPressed)
    {
        key = keyName;
        sound = soundFile;
        notPressed = keyNotPressed;
        pressed = keyPressed;
        
        setImage(notPressed + ".png");
    }
    
    /**
     * Create a new key.
     */
    public Key()
    {
        
    }

    /**
     * Do the action for this key.
     */
    public void act()
    {
        if(isDown == false && Greenfoot.isKeyDown(key))
        {
            setImage(pressed + ".png");
            isDown = true;
            play();
        }
        
        if(isDown == true && !Greenfoot.isKeyDown(key))
        {
            setImage(notPressed + ".png");
            isDown = false;
        }
    }
    
    /**
     * play plays the correct sound to the corrasponding note/key
     * @param there are no parameters
     * @return nothing is returned
     */
    private void play()
    {
        Greenfoot.playSound(sound + ".wav");
    }
    
    /**
     * checkDown returns isDown
     * @param there are no parameters
     * @return returns isDown a boolean that represents a key being pressed down
     */
    public boolean checkDown()
    {
        return (isDown);
    }  
}
