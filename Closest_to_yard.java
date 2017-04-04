/*
 * The below code uses K-means clustering algorithm as part of unsupervised learning. 
 * The program is feeded with given coordinates in zipcodes.csv file ( the file needs to copied and pasted inside the folder 
 * from where the code will run)
 * The training data is used to form clusters with K ( value taken is K=10)
 * After the training is complete , the program takes input and displays closest locations as per the cluster. 
 * 
 */


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

public class Closest_to_yard {
	
	static List<Cluster> clusters = new ArrayList<Cluster>();
	static List<YardPoint> points = new ArrayList<YardPoint>();	

	public Closest_to_yard() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		String line = "";
		String zipcodes = "zipcodes.csv";
		String delimiter = ",";
		Float[] latitude = new Float[42742];
		Float[] longitude = new Float[42742];
		boolean done = false;
		int iteration = 10;
		
		//fetching the positions based on csv file to plot 
		try{
			File f = new File(".");
			FileReader fr = new FileReader(new File(f.getCanonicalPath() + "\\" + zipcodes));
			BufferedReader bw = new BufferedReader(fr);
			line = bw.readLine();
			while((line = bw.readLine())!=null)
			{
				
				String g[] = line.split(delimiter);	
				//System.out.println(g[1]);
				if(!g[1].isEmpty() && !g[2].isEmpty())
				{
					YardPoint p = new YardPoint(Float.parseFloat(g[1]),Float.parseFloat(g[2]));
					points.add(p); 
				   //System.out.println(p.x + "--- " + p.y );
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/*
		 * Using K-means Clustering Plotting the points and finding the euclidean distance..
		 * Create clusters with k = 5
		 */	
		
		int k=30;// Randomly selecting K value
		
		//Initialize the clusters with random points....
		for(int i=0;i<k;i++)
		{
			Cluster cluster = new Cluster(i);
			Random rand = new Random();
			int r = rand.nextInt(points.size()-1);			
			cluster.centroid = points.get(r);
			clusters.add(cluster);
			
		}
		
		// Iteratively calculating and recalculating the clusters
		//Setting Iteration to a value = 10 
		while(iteration>0) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<YardPoint> lastCen = CentroidList();
        	
        	//Assign points to the closer cluster
        	ClusterReassignment();
            
            //Calculate new centroids.
        	ReCalCentroids();       	
        	
        	
        	List<YardPoint> newCen = CentroidList();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCen.size(); i++) {
        		distance += YardPoint.calcEuclideandist(lastCen.get(i),newCen.get(i));
        	}
        	
        	//System.out.println("#################");
        	iteration--;
        	        	       	
        	if(distance == 0) {
        		done = true;
        	}
		
	   }
		
		// after applying the learning alogorithm .. the closest yards for the given point can be determined in n-k complexity
		
		Float givenx = Float.parseFloat(args[0]);
		Float giveny = Float.parseFloat(args[1]);
		Double distance = Double.MAX_VALUE;
		Double v=0.0;
		int clusternum =-1;
		
		for(Cluster cluster : clusters) {
		{
			v = YardPoint.calcEuclideandist(new YardPoint(givenx,giveny),cluster.centroid);
			if(v<distance)
			{
				distance=v;
                clusternum = cluster.Label;
			}
		}
		
		System.out.print("The closest locations as per given pont are as followd:");
		for(Cluster cluster1 : clusters) {
		      if(cluster1.Label==clusternum)
		      {
		    	  for(YardPoint yp : cluster1.plots) {
		    		
		    		  System.out.println("X coordinate : " + yp.x + " and Ycoordinate: " + yp.y);
		    		  
		    	  }
		      }
		
		  }
		}
		
		
	}
				
		  private static void clearClusters() {
		    	for(Cluster cluster : clusters) {
		    		cluster.reset();
		    	}
		    }
		    
		    private static List CentroidList() {
		    	List<YardPoint> centroids = new ArrayList<YardPoint>();
		    	for(Cluster cluster : clusters) {
		    		YardPoint point = cluster.centroid;		    		
		    		centroids.add(point);
		    	}
		    	return centroids;
		    }
		    
		    private static void ClusterReassignment() {
		        double max = Double.MAX_VALUE;
		        double min = max; 
		        int cluster = 0;                 
		        double distance = 0.0; 
		        
		        System.out.println(clusters.size());
		        for(YardPoint point : points) {
		        	min = max;
		            for(int i = 0; i<clusters.size(); i++) {
		            	Cluster c = clusters.get(i);
		            	//System.out.println("point: " + point.x + " : : " + point.y);
		                distance = YardPoint.calcEuclideandist(point, c.centroid);
		                if(distance < min){
		                    min = distance;
		                    cluster = i;
		                }
		            }
		            point.cluster_num = cluster;
		            clusters.get(cluster).addPlot(point);
		        }
		    }
		    
		    private static void ReCalCentroids() {
		        for(Cluster cluster : clusters) {
		            float sumX = 0;
		            float sumY = 0;
		            List<YardPoint> list = cluster.plots;
		            int size = list.size();
		            
		            for(YardPoint point : list) {
		            	sumX += point.x;
		                sumY += point.y;
		            }
		            
		            YardPoint centroid = cluster.centroid;
		            
		            if(size > 0) {
		            	float x1 = sumX / size;
		                float y1 = sumY / size;
		                centroid.x = x1;
		                centroid.y = y1;
		            }
		            
		            //System.out.println( size +  "  point: " + centroid.x + " : : " + centroid.y);
		        }
		    }

}
