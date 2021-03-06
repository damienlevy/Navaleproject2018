/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import controleur.Controleur;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import modele.Case;
import modele.ModelClassique;
import modele.bateau.Bateau;
import modele.factory.ModernFactory;


/**
 *
 * @author TRABELSI
 */
public class VueAdversaire extends JPanel implements GameVue {
     public static Image back;
    private final static int w = 400;
    private final static int h = 400;
    public final static int CaseX = 37;
    public final static int CaseY =37   ;
    public JPanel adversaire ;
    private BufferedImage para;
    private BufferedImage bat;
    public  ModelClassique model;
    private BufferedImage boat;
    private BufferedImage touche;
    private BufferedImage cercle;
    private Controleur c ;
    private BufferedImage Lose;
    public VueAdversaire (Controleur c)
    {

        model = c.getModele();
        this.display();
    }

    @Override
   public void display() {      
       adversaire = new JPanel();
       adversaire.setPreferredSize(new Dimension(w,h));
       //adversaire.setSize(w,h);
    }

    @Override
    public void update() {

    }

   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
   
    try {
        back = ImageIO.read(getClass().getResourceAsStream("images/back.jpg"));
        para = ImageIO.read(VueAdversaire.class.getResource("para.jpg"));
        bat = ImageIO.read(getClass().getResourceAsStream("images/bateau.png"));
        boat = ImageIO.read(getClass().getResourceAsStream("images/boat.png"));
        Font font = new Font( "Rockwell Extra Bold", Font.PLAIN, 20 );
        touche = ImageIO.read(getClass().getResourceAsStream("images/touche.png"));
        cercle = ImageIO.read(getClass().getResourceAsStream("images/cercle.png"));
        BufferedImage win = ImageIO.read(getClass().getResourceAsStream("images/win.jpg"));
        for( int i=0 ; i <= (w/CaseX); i++ )
        {
            
            for(int j = 0 ; j <=h/CaseY ; j++)
            {
              g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY); 
              g.drawImage(back,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
              
              if(i==0 && j==0)
              {
              g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY); 
              g.drawImage(bat,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
              }
              
              if(i==0 && j>= 1)
              {
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY); 
                        g.drawImage(para,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                        g.setFont(font );
                        String c = Character.toString((char)(65 + ((j-1)/26)*6 + (j-1)));
                  	g.drawString(c,i*CaseX+10,j*CaseY+20);
              }
              
              if(j==0 && i>= 1)
              {
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY); 
                        g.drawImage(para,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this);          
                        g.setFont(font );
                  	g.drawString(String.valueOf(i),i*CaseX+10,j*CaseY+20);
              }
              
            }
           
        }
        for(int i = 1; i< 11 ; i++)     
        {
            for(int j=1; j < 11 ; j++)
            {
                 if(model.getPlateauIA().plateau[i][j].getidBateau() >= 1)
                     
                 {
                     
                     g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                     g.drawImage(boat,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                     g.drawString((String.valueOf(model.getPlateauIA().plateau[i][j].getidBateau())),i*CaseX+10,j*CaseY+20);
                         g.drawImage(back,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                 }
                 if(model.getPlateauIA().plateau[i][j].estTouche())
                 {
                     
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                        g.drawImage(touche,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                 }
                 if(!model.getPlateauIA().plateau[i][j].estEau())
                 {
                     
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                        g.drawImage(cercle,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                 }
                 
            }
        }
         Lose = ImageIO.read(getClass().getResourceAsStream("images/Lose.jpg"));
        if(model.getIa().Lose())
                 {
                     g.drawRect(0,0,w,h);
                     g.drawImage(Lose,0,0,w,h,this);
                 } 
       if(winIA())
                 {
                     g.drawRect(0,0,w,h);
                     g.drawImage(win,0,0,w,h,this);
                 } 
    } catch (IOException ex) {
        Logger.getLogger(VueAdversaire.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
   public boolean winIA()
        {
           int coule =0;
            for(int i=0;i<11;i++)
            {
                for(int j=0; j<11 ;j++)
                {
                    if(model.getPlateauJoueur1().plateau[i][j].estTouche() && model.getPlateauJoueur1().plateau[i][j].getidBateau()>0)
                            coule++;
                        }
                
            }
            if(coule==17)
               return true;
           else return false;
            
        }
}
