package modele.bateau;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Bateau5 implements Bateau{
	
	protected int id;
	protected List<Point> coords;
	protected List<Point> touche;
	
	public Bateau5(int id) {
		this.id = id;
		this.coords = new ArrayList<>();
		this.touche = new ArrayList<>();
	}
	
	public int getTaille(){
		return 5;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public List<Point> getPosition() {
		// TODO Auto-generated method stub
		return coords;
	}
	
	@Override
	public void setPosition(List<Point> points){
		for(Point p : points ){
			this.coords.add(p);
		}
	}
	
	@Override
	public void touche(Point p){
		if(coords.contains(p) && !touche.contains(p)) {
			touche.add(p);
		}
	}
		
	@Override
	public boolean estCoule() {
		boolean b = true;
		for (int i = 0; i < coords.size(); i++) {
			if(!coords.contains(touche.get(i)))
				b = false;
		}
		return b;
	}
	public List<Point> getTouche(){
		return this.touche;
	}

}
