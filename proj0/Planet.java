public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV,
		double m, String img){

		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b){

		double xx = this.xxPos - b.xxPos;
		double yy = this.yyPos - b.yyPos;
		double r = Math.sqrt(xx*xx + yy*yy);
		return r;
	}

	public double calcForceExertedBy(Planet b){

		double r = b.calcDistance(this);
		double F = (G*mass*b.mass)/(r*r);
		return F;
	}

	public double calcForceExertedByX(Planet b){

		double F = b.calcForceExertedBy(this);
		double r = b.calcDistance(this);
		double Fx = F*(b.xxPos-xxPos)/r;

		return Fx;
	}

	public double calcForceExertedByY(Planet b){

		double F = b.calcForceExertedBy(this);
		double r = b.calcDistance(this);
		double Fy = F*(b.yyPos-yyPos)/r;

		return Fy;
	}

	public double calcNetForceExertedByX(Planet[] all){

		double Fx = 0;

		for(int i = 0; i<all.length; i++)
		{
			if(xxPos==all[i].xxPos && yyPos==all[i].yyPos){
				continue;
			}
			Fx += this.calcForceExertedByX(all[i]);
		}

		return Fx;
	}

	

	public double calcNetForceExertedByY(Planet[] all){

		double Fy = 0;

		for(int i = 0; i<all.length; i++)
		{
			if(xxPos==all[i].xxPos && yyPos==all[i].yyPos){
				continue;
			}
			Fy += this.calcForceExertedByY(all[i]);
		}

		return Fy;
	}

	public void update(double dt, double fX, double fY){
		double xA = fX/mass;
		double yA = fY/mass;
		xxVel = xxVel + dt*xA;
		yyVel = yyVel + dt*yA;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}

	public void draw(){
		
		StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
	}
}