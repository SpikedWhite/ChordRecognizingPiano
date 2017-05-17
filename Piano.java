import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piano extends World
{
    
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]"};
    private String[] whiteNotes = {"3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g"};
    private String[] blackKeys = {"2", "3", "", "5", "6", "7", "", "9", "0", "", "="};
    private String[] blackNotes = {"3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#"};
    private Key[] whiteKeyObjects = new Key[12];
    private Key[] blackKeyObjects = new Key[11];
    private Key[] allKeyObjects = new Key[23];
        
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }
    
    /**
     * makeKeys creates a key object at said location
     * @param there are no parameters
     * @return nothing is returned
     */
    private void makeKeys()
    {
        Key currentKey;
        
        for(int i = 0; i < whiteKeys.length; i++ )
        {
            currentKey = new Key(whiteKeys[i], whiteNotes[i], "white-key", "white-key-down");
            addObject(currentKey, (i*67)+30, 250);
            whiteKeyObjects[i] = currentKey;
        }
        
        for(int i = 0; i < blackKeys.length; i++ )
        {
            if(blackKeys[i] != "")
            {
                currentKey = new Key(blackKeys[i], blackNotes[i], "black-key", "black-key-down");
                addObject(currentKey, (i*67)+65, 195);
                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
        }
        
        makeAllKeysArray();
    }
    
    /**
     * makeAllKeysArray does stuff and things for the piano
     * @param there are no parameters
     * @return nothing is returned
     */
    private void makeAllKeysArray()
    {
        for( int i = 0; i < blackKeyObjects.length; i++)
        {
            allKeyObjects[2*i] = whiteKeyObjects[i];
            allKeyObjects[2*i+1] = blackKeyObjects[i];
        }
        
        allKeyObjects[22] = whiteKeyObjects[11];
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;
        
        int[] keyDownLocations = new int[20];
        
        for(int i = 0; i < allKeyObjects.length; i++)
        {
            if(allKeyObjects[i] == null)
            {
                numNulls = numNulls + 1;
            }
            else
            {
                if(allKeyObjects[i].checkDown() == true)
                {
                    keyDownLocations[numAllDown] = i - numNulls;
                    numAllDown++;
                }
            }
        }  
        
        if(numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);
        }
        else if(numAllDown == 3)
        {
            checkForTriads(keyDownLocations);
        }
        else if(numAllDown == 4)
        {
            checkForSevenths(keyDownLocations);
        }
        else
        {
            showText("", getWidth()/2, 50);
        }
    }
    
    /**
     * checkforSeconds checks to see if two keys are pressed down at the same time
     * @param downKeys is how many keys are pressed at the same time
     * @return nothing is returned
     */
    private void checkForSeconds(int[] downKeys)
    {
        if( 1 + downKeys[0] == downKeys[1] || 2 + downKeys[0] == downKeys[1])
        {
            showText("You have made a second", getWidth()/2, 50);
        }
    }
    
    /**
     * checkforTriads checks to see if three keys are pressed down at the same time
     * @param downKeys is how many keys are pressed at the same time
     * @return nothing is returned
     */
    private void checkForTriads(int[] downKeys)
    {
        if( 3 + downKeys[0] == downKeys[1] && 4 + downKeys[1] == downKeys[2] || 4 + downKeys[0] == downKeys[1] && 3 + downKeys[1] == downKeys[2]  || 3 + downKeys[0] == downKeys[1] && 3 + downKeys[1] == downKeys[2])
        {
            showText("You have made a triad", getWidth()/2, 50);
        }
    }
    
    /**
     * checkforSevenths checks to see if four keys are pressed down at the same time
     * @param downKeys is how many keys are pressed at the same time
     * @return nothing is returned
     */
    private void checkForSevenths(int[] downKeys)
    {
        if( 3 + downKeys[0] == downKeys[1] && 4 + downKeys[1] == downKeys[2] && 3 + downKeys[2] == downKeys[3] || 4 + downKeys[0] == downKeys[1] && 3 + downKeys[1] == downKeys[2] && 4 + downKeys[2] == downKeys[3] || 3 + downKeys[0] == downKeys[1] && 3 + downKeys[1] == downKeys[2] && 3 + downKeys[2] == downKeys[3])
        {
            showText("You have made a seventh", getWidth()/2, 50);
        }
    }
    
    
}
