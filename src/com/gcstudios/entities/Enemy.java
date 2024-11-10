package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public double vida = 30;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		path = AStar.findPath(Game.world,new Vector2i(World.xINITIAL,World.yINITIAL),new Vector2i(World.xFINAL,World.yFINAL));
	}
	
	public void tick() {
		
		followPath(path);
		if(x >= Game.WIDTH) {
			//Perdemos vida aqui!
			Game.vida-=Entity.rand.nextDouble();
			Game.entities.remove(this);
			
			return;
		}
		if(vida <= 0)
		{
			Game.entities.remove(this);
			Game.dinheiro++;
			return;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.red);
		g.fillRect((int)x,(int) (y-5),30,6);
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int) (y-5),(int)((vida/30) *30),6);
	}
	
	

}
