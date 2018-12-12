/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;


import modele.Jeu;
import modele.ModelClassique;
import modele.factory.AntiquiteFactory;
import controleur.Controleur;
import controleur.ControleurJeuxClassique;
import vues.VueComplete;
import vues.VuePerso;
import vues.VuePlacement;

/**
 *
 * @author trabels33u
 */
public class Main { 

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ModelClassique     model = new ModelClassique(new AntiquiteFactory());
		Controleur c  = new ControleurJeuxClassique(model);
		VueComplete vc = new VueComplete(c);
		VuePlacement vp = new VuePlacement(c);
		model.addVue(vc);
		model.addVue(vp);
		c.addVue(vc);
	}

}
