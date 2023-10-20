package fr.univlille.felixthecat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Felix {

	public void drawOn(GraphicsContext gc) {
		/*drawHead(gc);
		drawEyes(gc);
		drawEars(gc);
		drawWiskers(gc);*/
		drawNose(gc);
	}

	public void drawNose(GraphicsContext gc){
		int firstX = 150;
                int secondX = 140;
                int thirdX = 160;

                int firstY = 125;
                int secondY =115;
                int thirdY =115;

                gc.setFill(Color.PINK);
                gc.setStroke(Color.BLACK);
                gc.fillPolygon(new double[]{firstX, secondX,thirdX},
                new double[]{firstY, secondY, thirdY}, 3);

                gc.strokePolygon(new double[]{firstX, secondX,thirdX},
                new double[]{firstY, secondY, thirdY}, 3);
	}

}
