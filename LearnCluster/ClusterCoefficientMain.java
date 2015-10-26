package LearnCluster;

public class ClusterCoefficientMain {
	
	public static  void main(String[] args) {
		
		ClusterCoefficient cc = new ClusterCoefficient();
		
		int[] E = {
				0, 1,
				0, 2,
				0, 3,
				0, 5,
				1, 3,
				1, 4,
				1, 5,
				2, 3,
				2, 6,
				3, 4,
				3, 6,
				5, 6,
		};
		
		double result = cc.funcC(7, E.length /2, E);
		
		System.out.println("Cluster Coefficient " + result);
		
	}

}
