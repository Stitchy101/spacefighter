package com.thinkbox.sf.model;

import java.awt.image.BufferedImage;

public class EnemyType extends Unit {
	private BufferedImage standing;
	private BufferedImage moving;
	private int accuracy;

	public EnemyType(double a, String act, int h, int th, BufferedImage s, BufferedImage m, Weapon w, int ac) {
		super(0, 0, a, act, h, th, w);
		standing = s;
		moving = m;
		accuracy = ac;
	}

	public Enemy create(int x, int y, boolean b) {
		return new Enemy(x, y, getAngle(), getAction(), getHealth(), getMaxHealth(), standing, moving, getWeapon(),
				accuracy, b);
	}
}
