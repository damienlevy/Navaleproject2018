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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import modele.bateau.Bateau;
import static vues.VueAdversaire.CaseX;
import static vues.VueAdversaire.CaseY;
import static vues.VueAdversaire.back;

/**
 *
 * @author TRABELSI
 */
public class VuePerso extends JPanel implements GameVue {
	public static Image back;
	private final static int w = 400;
	private final static int h = 400;
	public final static int CaseX = 37;
	public final static int CaseY = 37;
	public JPanel perso;
	private BufferedImage para;
	private BufferedImage bateau;

        private BufferedImage check;
        private BufferedImage no;
        private BufferedImage[] fleches ;
        private Controleur c ;
        private Point place ;
        private  static boolean draw;
        private int taille;
        private BufferedImage boat;
        private BufferedImage touche;
        private BufferedImage cercle;
       private BufferedImage Lose;
          private BufferedImage win;
        
        
	public VuePerso(Controleur c )
	{       
                this.c=c;
		this.display();
	}

	@Override

	public void display() {  
            
		perso = new JPanel();
		perso.setPreferredSize(new Dimension(w+100,h));
		draw = false ;
                this.fleches=new BufferedImage[4];
            try {
                fleches[0] = ImageIO.read(getClass().getResourceAsStream("images/fleche1.png"));
                fleches[1] = ImageIO.read(getClass().getResourceAsStream("images/fleche2.png"));
                fleches[2] = ImageIO.read(getClass().getResourceAsStream("images/fleche3.png"));
                fleches[3] = ImageIO.read(getClass().getResourceAsStream("images/fleche4.png"));
            } catch (IOException ex) {
                Logger.getLogger(VuePerso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

	@Override
	public void update() {

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			back = ImageIO.read(getClass().getResourceAsStream("images/back.jpg"));
			para = ImageIO.read(getClass().getResourceAsStream("images/para.jpg"));
			bateau = ImageIO.read(getClass().getResourceAsStream("images/bateau.png"));
                        no = ImageIO.read(getClass().getResourceAsStream("images/no.png"));
			check = ImageIO.read(getClass().getResourceAsStream("images/boat.png"));
                        boat = ImageIO.read(getClass().getResourceAsStream("images/boat.png"));
                        touche = ImageIO.read(getClass().getResourceAsStream("images/touche.png"));
                         win = ImageIO.read(getClass().getResourceAsStream("images/win.jpg"));
                        Lose = ImageIO.read(getClass().getResourceAsStream("images/Lose.jpg"));
                        cercle = ImageIO.read(getClass().getResourceAsStream("images/cercle.png"));        
			Font font = new Font( "Rockwell Extra Bold", Font.PLAIN, 20 );
			for( int i=0 ; i <= (w/CaseX); i++ )
			{

		

				for (int j = 0; j <= h / CaseY; j++) {
					g.drawRect(i * CaseX, j * CaseY, CaseX, CaseY);
					g.drawImage(back, i * CaseX, j * CaseY, CaseX - 1, CaseY - 1, this);
					if (i == 0 && j == 0) {
						g.drawRect(i * CaseX, j * CaseY, CaseX, CaseY);
						g.drawImage(bateau, i * CaseX, j * CaseY, CaseX - 1, CaseY - 1, this);
					}
					if (i == 0 && j >= 1) {
						g.drawRect(i * CaseX, j * CaseY, CaseX, CaseY);
						g.drawImage(para, i * CaseX, j * CaseY, CaseX - 1, CaseY - 1, this);

						g.setFont(font);
						String c = Character.toString((char) (65 + ((j - 1) / 26) * 6 + (j - 1)));
						g.drawString(c, i * CaseX + 10, j * CaseY + 20);
					}
					if (j == 0 && i >= 1) {
						g.drawRect(i * CaseX, j * CaseY, CaseX, CaseY);
						g.drawImage(para, i * CaseX, j * CaseY, CaseX - 1, CaseY - 1, this);
						g.setFont(font);
						g.drawString(String.valueOf(i), i * CaseX + 10, j * CaseY + 20);
					}
				}
			}
                        if(this.draw==true)
                        {
                            System.out.println("place " +place.x);
                            if(this.c.getModele().getPlateauJoueur1().estVide(place))
                            {
                               g.drawRect(place.x*CaseX,place.y*CaseY,CaseX,CaseY); 
                               g.drawImage(check,place.x*CaseX,place.y*CaseY,CaseX-1,CaseY-1,this);
                              /**************Draw Directions*****************************/
                              //1
                              if(place.x+1 <=10)
                              { g.drawRect((place.x+1)*CaseX,place.y*CaseY,CaseX,CaseY); 
                                 g.drawImage(fleches[0],(place.x+1)*CaseX,place.y*CaseY,CaseX-1,CaseY-1,this);}
                              //2
                                   if(place.y+1 <=10){
                                     g.drawRect((place.x)*CaseX,(place.y+1)*CaseY,CaseX,CaseY); 
                                     g.drawImage(fleches[1],(place.x)*CaseX,(place.y+1)*CaseY,CaseX-1,CaseY-1,this);
                                   }
                              //3
                                   if(place.y-1 >=1)
                                   {
                                    g.drawRect((place.x)*CaseX,(place.y-1)*CaseY,CaseX,CaseY); 
                                    g.drawImage(fleches[3],(place.x)*CaseX,(place.y-1)*CaseY,CaseX-1,CaseY-1,this);
                                   }
                              //4
                                   if(place.x-1 >=1){
                                g.drawRect((place.x-1)*CaseX,place.y*CaseY,CaseX,CaseY); 
                                g.drawImage(fleches[2],(place.x-1)*CaseX,place.y*CaseY,CaseX-1,CaseY-1,this);}
                               }
                            }
                           for(int i = 1; i< 11 ; i++)     
        {
            for(int j=1; j < 11 ; j++)
            {
                 if(c.getModele().getPlateauJoueur1().plateau[i][j].getidBateau() >= 1)
                     
                 {
                     
                     g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                     g.drawImage(boat,i*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                     
                 }
                           
                        }
              for(int i1 = 1; i1< 11 ; i1++)     
        {
            for(int j=1; j < 11 ; j++)
            {
                 if(c.getModele().getPlateauJoueur1().plateau[i1][j].getidBateau() >= 1)
                     
                 {
            if(c.getModele().getPlateauJoueur1().plateau[i1][j].estTouche())
                 {
                     
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                        g.drawImage(touche,i1*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                 }}
           if(!c.getModele().getPlateauJoueur1().plateau[i1][j].estEau())
                 {
                     
                        g.drawRect(i*CaseX,j*CaseY,CaseX,CaseY);
                        g.drawImage(cercle,i1*CaseX,j*CaseY,CaseX-1,CaseY-1,this); 
                 
        }
                }
        }
        }
                            /************************Tester LOSE/*****************/
                 if(c.getModele().getj1().Lose() )
                 {
                     g.drawRect(0,0,w,h);
                     g.drawImage(Lose,0,0,w,h,this);
                 } 
                
               
                  if(winJ())
                 {
                     g.drawRect(0,0,w,h);
                     g.drawImage(win,0,0,w,h,this);
                 } 
                }

		 catch (IOException ex) {
			Logger.getLogger(VuePerso.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

    void draw(int i, int i0) {
        place=new Point (i , i0);
        place.x=i;
        place.y=i0;
        draw = true ;
    }
    void setDraw(boolean t)
    {
        this.draw=t;
    }
    void setTaille(int t )
    {
        this.taille=t;
    }
    public int getTaille()
            
    {
        return this.taille;
    }
    public int checkPointOrientation(Point p)
    {
      if(p.x==place.x+1 && p.y==place.y)
        return 1;
      if(place.y+1 ==p.y && p.x==place.x)
          return 2;
      if(place.x-1==p.x && place.y==p.y)
          return 3;
      if(place.y-1 ==p.y && place.x==p.x)
          return 4;
         return -1; 
    }

    void drawP(Point p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
            public boolean winJ()
        {
            int coule =0;
            for(int i=0;i<11;i++)
            {
                for(int j=0; j<11 ;j++)
                {
                    if(c.getModele().getPlateauIA().plateau[i][j].estTouche() && c.getModele().getPlateauIA().plateau[i][j].getidBateau()>0)
                            coule++;
                        }
                
            }
            if(coule==17)
               return true;
           else return false;
            
        }
}
