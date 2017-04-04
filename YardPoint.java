
public class YardPoint {

	Float x;
	Float y;
	int cluster_num=-1;
	
	public YardPoint() {}
	
	public YardPoint(Float X , Float Y) {
		x = X;
		y= Y;
		
	}
	
	protected static double calcEuclideandist( YardPoint point, YardPoint centroid) {
        return Math.sqrt(Math.pow((centroid.y - point.y), 2) + Math.pow((centroid.x - point.x), 2));
    }
 

}
