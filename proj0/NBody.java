public class NBody{
	public static double readRadius(String fileName){

		In in = new In(fileName);

		@Deprecated
		int num = in.readInt();

		@Deprecated
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String fileName){

		In in = new In(fileName);
		@Deprecated
		int num = in.readInt();

		@Deprecated
		double radius = in.readDouble();


		Planet[] planets = new Planet[num];

		for(int i = 0; i < num; i++){

			@Deprecated
			double xx = in.readDouble();
			double yy = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double m = in.readDouble();
			String file = in.readString();

			planets[i] = new Planet(xx,yy,xv,yv,m,file);
		}

		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet[] all = readPlanets(fileName);
		double time = 0;

		StdDraw.enableDoubleBuffering();

		for(; time<=T; time+=dt){
			double[] xForces = new double[all.length];
			double[] yForces = new double[all.length];
			for(int i = 0; i < all.length; i++){
				xForces[i] = all[i].calcNetForceExertedByX(all);
				yForces[i] = all[i].calcNetForceExertedByY(all);
				all[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.setScale(-radius,radius);
			StdDraw.clear();

			StdDraw.picture(-radius, radius, "./images/starfield.jpg", 4*radius, 4*radius);
			for(int i = 0; i < all.length; i++){
				all[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", all.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < all.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  all[i].xxPos, all[i].yyPos, all[i].xxVel,
		                  all[i].yyVel, all[i].mass, all[i].imgFileName);   
		}
	
	}
}