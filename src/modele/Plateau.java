package modele;

import java.awt.Point;
import java.util.List;

import modele.Case;

/**
 * 
 * @author trabels33u
 */
public class Plateau {

	public static final int Width = 11;
	public static final int Height = 11;

	public Case[][] plateau;

	public Plateau() {
		plateau = new Case[Width][Height];
		this.initialiser();
	}

	public Plateau(Case[][] plateau) {
		this.setPlateau(plateau);
	}

	public final void initialiser() {// initialiser plateau vide
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				plateau[i][j] = new Case(-1);
			}
		}

	}

	public Case[][] getPlateau() {
		return this.plateau;
	}

	public void libererCase(Point p) {
		Case c = getCase(p);
		c.setidBateau(-1);
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	public Case getCase(Point p) {
		return plateau[p.x][p.y];
	}

	public Case getCase(int x, int y) {
		return plateau[x][y];
	}

	// verfifier si la case est déjà vide avant de placer le bateau
	public boolean estVide(Case c) {
		if (c.getidBateau() == -1)
			return true;
		return false;
	}

	public boolean estVide(Point p) {
		boolean b = false;
		int x = (int) p.getX();
		int y = (int) p.getY();

		if (this.estVide(this.getCase(x, y))) {
			b = true;
		}
		return b;
	}

	public boolean positionVide(List<Point> p) {
		int i = (int) p.get(0).getX();
		int j = (int) p.get(0).getY();
		while (i <= p.get(1).getX()) {
			while (j <= p.get(1).getY()) {
				if (this.estVide(plateau[i][j])) {
					i++;
					j++;
				} else {
					return false;
				}
			}
		}

		return true;
	}

	public void allocatePosition(int id, List<Point> p) {
		/*
		 * int i = p[0].x; int j = p[0].y; while (i <= p[1].x) { while (j <= p[2].y) {
		 * this.plateau[i][j] = new Case(id); } }
		 */
		int x = 0;
		int y = 0;
		for (Point point : p) {
			x = (int) point.getX();
			y = (int) point.getY();
			this.plateau[x][y].setidBateau(id);
		}
	}

	public void afficherPlateau() {
		for (int i = 0; i < this.Height - 1; i++) {
			System.out.println(i);
			for (int j = 0; j < this.Width - 1; j++) {
				System.out.print(this.plateau[i][j].getidBateau());
			}
			System.out.println();
		}
	}

}
