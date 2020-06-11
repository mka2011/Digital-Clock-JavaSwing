package digitalClock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class audio extends JFrame {

Clip clip ;
   public audio(int day) 
   {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Test Sound Clip");
      this.setSize(300, 200);
      this.setVisible(true);

      try 
      {
         File f = null;
         if(day == 0)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/ALARM.wav");
         else if(day == 1)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonMyWay.wav");
         else if(day == 2)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonADoorThatIsOpen.wav");
         else if(day == 3)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonAintItGood.wav");
         else if(day == 4)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonFoolsRushIn.wav");
         else if(day == 5)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonForeverMyDarling.wav");
         else if(day == 6)
        	 f = new File("/home/xxdil/eclipse-workspace/audio2/src/audio2/BrookBentonKiddio.wav.wav");
         
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
         clip = AudioSystem.getClip();
         clip.open(audioIn);
         clip.start();
      } 
      catch (Exception e) 
      {
         e.getMessage();
      } 
   }

   void shut() {
	   clip.stop();
	   clip.close();
	   this.setVisible(false);
   }	
   
   public static void main(String[] args) {
	   new audio(2);
   }
}