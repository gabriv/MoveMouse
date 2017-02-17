package com.gabri;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		int pxDesplazamiento = 1;
		int segPause = 240; //4 minutos - 240 seg - 240000 ms
		if (args[0] != null) {
			segPause = Integer.parseInt(args[0]);
			if (args[1] != null) {
				pxDesplazamiento = Integer.parseInt(args[1]);
			}
		}
		long msPause = segPause * 1000;
		Point p = MouseInfo.getPointerInfo().getLocation();
		int xOld = 0;
		int yOld = 0;
		int xNew = 0;
		int yNew = 0;
		Robot robot;
		String sufijo = null;
		try {
			robot = new Robot();
			do {
				sufijo = "";
				xOld = p.x;
				yOld = p.y;
				Thread.sleep(msPause);
				p = MouseInfo.getPointerInfo().getLocation();
				xNew = p.x;
				yNew = p.y;
				if (xOld == xNew && yOld == yNew) {//No cambia posición, lo muevo
					sufijo = "\tMOVIDO " + pxDesplazamiento + " px!";
					robot.mouseMove(p.x - pxDesplazamiento, p.y - pxDesplazamiento);
					p = MouseInfo.getPointerInfo().getLocation();
					xNew = p.x;
					yNew = p.y;
				}
				System.out.println(new Date().toString().substring(0, 20) + "Tras " + segPause + "seg (" + (segPause/60) + " minutos), la nueva posición es: " + xNew + " - " + yNew + sufijo);
			} while (1==1);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
