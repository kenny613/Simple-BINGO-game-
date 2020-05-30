package bingo;

/**
 *
 * @author Tong Kai Hin
 * This is a very simple java program of BINGO game (開口中). Player have to recursively guess a random number starting from 1-100, 
 * when the guess is wrong the range will be more tight with respect to your guessed number. 
 * Once the number is guessed correctly, the game end.
 */

import java.util.*; // for phone book data management
import javax.swing.*; // for GUI dialogues and image handling
import java.io.*; // for file opening and stream handling
import java.awt.*;


public class Bingo {
    private static String dialogQuestionMarkIconImageFilename = "ICT1.png"; 
    private static String WinIconImageFilename = "broken.png"; 
    int match[]=new int[101];
    int noPlayer;
    Random r = new Random();
    int correctans = r.nextInt(100) + 1;   
    String option;
    
public int createContents() throws NumberFormatException  {
try{	
    
for(int i=1;i<101;i++)
{
    this.match[i]=0;
    //System.out.println(this.match[i]);
}
 String menuHTML = "<html>";
 menuHTML += "<h1>Welcome to BINGO game!</h1><hr>";
 menuHTML += "Select number of player below:";
 menuHTML += "</html>";

// this.getClass().getSimpleName(), 0, 0, options, null);
ImageIcon icon = new ImageIcon(dialogQuestionMarkIconImageFilename);
Image image = icon.getImage(); 
Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
icon = new ImageIcon(newimg); // transform it back

int mType=JOptionPane.INFORMATION_MESSAGE;
String[] players={"1","2","3","4","5","6","7","8","9","10"};

 String choice= (String)JOptionPane.showInputDialog(null, menuHTML,this.getClass().getSimpleName(), mType, icon, players, players[0]);
 System.out.println(correctans);
   
 return Integer.parseInt(choice);
}catch(NumberFormatException e)
{
return 0;}
}

public String MainGame(int turns,int low,int high)throws NumberFormatException{ //Using recursion
try{    
    ImageIcon icon2 = new ImageIcon(WinIconImageFilename);
    Image image2 = icon2.getImage(); 
    Image newimg2 = image2.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
    icon2 = new ImageIcon(newimg2); // transform it back
    
    String gameHTML = "<html>";
    gameHTML += "Enter a number from "+ Integer.toString(low)+"-"+Integer.toString(high);
    gameHTML += "</html>";
    
    int mType=JOptionPane.INFORMATION_MESSAGE;
    int next_turn=(turns+1)%this.noPlayer;
    if (next_turn==0)
            next_turn=this.noPlayer;

    String attempt=JOptionPane.showInputDialog(null,gameHTML,"Player "+turns+" turns",mType) ;
    //System.out.println(attempt);
    //System.out.println("interger is "+Integer.parseInt(attempt));
    this.option=attempt;
 
    if(Integer.parseInt(attempt)>high||Integer.parseInt(attempt)<low)
    {
        JOptionPane.showMessageDialog(null,"Invalid input", "Warning",JOptionPane.WARNING_MESSAGE);
         return  MainGame(turns,low,high);
    }
    else if(this.match[Integer.parseInt(attempt)]==0)
    {
        if(Integer.parseInt(attempt)>this.correctans)
        {
            this.match[Integer.parseInt(attempt)]=1;
            return MainGame(next_turn,low,Integer.parseInt(attempt));
        }
        else if(Integer.parseInt(attempt)<this.correctans)
        {
            this.match[Integer.parseInt(attempt)]=1;
            return MainGame(next_turn,Integer.parseInt(attempt),high);
        }
        else if(Integer.parseInt(attempt)==this.correctans)
        {
            JOptionPane.showMessageDialog(null,"BINGO! The correct answer is "+this.correctans+" ! Player "+turns+" loses the fucking game!", "Player "+turns+" loses the fucking game!",JOptionPane.PLAIN_MESSAGE, icon2);
            return "fuckyou";
        }
    }
    else
    {
        JOptionPane.showMessageDialog(null,"You have already inputted this before!", "Warning",JOptionPane.WARNING_MESSAGE);
         return  MainGame(turns,low,high);
    }

 return "uckyou";
}

catch(NumberFormatException e){
    {
        if(this.option!=null)
        {   
            JOptionPane.showMessageDialog(null,"Invalid input", "Warning",JOptionPane.WARNING_MESSAGE);
            MainGame(turns,low,high);
        }
        return " ";
    }      
}
}

public static void main(String[] args)  {
	Bingo game=new Bingo();
        game.noPlayer=game.createContents();
        if(game.noPlayer!=0)
        game.MainGame(1,1,100);

   
}
}

