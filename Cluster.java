import java.util.*;

public class Cluster {
	int Label;
    List<YardPoint> plots;
    YardPoint centroid;
    
	public Cluster(int label) {
		// TODO Auto-generated constructor stub
	 
		this.Label= label;
		this.plots = new ArrayList<YardPoint>();
	}
	
	public void addPlot(YardPoint point)
	{
		this.plots.add(point);
	}
	
	public void clustInfo() {
		System.out.println("Cluster: " + this.Label);
		System.out.println("Centroid: " + this.centroid);
		System.out.println("[Plots are: \n");
		for(YardPoint p : plots) {
			System.out.println(p);
		}
		System.out.println("]");
	}
 
	
	public void reset() {
		plots.clear();
	}

}
