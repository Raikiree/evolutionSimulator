import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Popu {

	private int myNumGenomes; //100 genes in the world
	private double myMutationRate;//mutation rate?
	private List<Geno> myGenomeList; 
	public Geno myMostFit; 
	private Random myRandom; 
	
	//constructor
	public Popu(int theNumGenomes, double theMutationRate) {
		myRandom = new Random(); 
		myNumGenomes = theNumGenomes; 
		myMutationRate = theMutationRate; 
		myGenomeList = new ArrayList<Geno>(); 
	
		for(int i = 0; i< myNumGenomes; i++) {
			myGenomeList.add(new Geno(myMutationRate));
		}
		myMostFit = myGenomeList.get(0);
	}
	
	public void day() {
		//daily mutate
		//get sorted fitnesss
		//update the mostFit gene
		//remove the last 50 genes
		//create the 50 new genes using the remaining genes with cross method
		
		for(Geno g : myGenomeList){
			g.mutate();
			g.fitness();
		}
		
		Comparator<Geno> comparator = new Comparator<Geno>(){
			@Override
			public int compare(Geno g1, Geno g2){
				if(g1.fitness() - g2.fitness() < 0)
					return -1;
				else if(g1.fitness() - g2.fitness() > 0)
					return 1;
				else
					return 0;
					
			}
		};
		Collections.sort(myGenomeList,comparator);	
		//update the best ans
		myMostFit = myGenomeList.get(0);
		int iii = 50;
		while(iii > 0){
			myGenomeList.remove(50);
			iii--;
		}
		
		while(myGenomeList.size() < 100){
			int createWay = myRandom.nextInt(2);
			if(createWay == 0){
				//clone only
				int r = myRandom.nextInt(50);
				Geno g = new Geno(myGenomeList.get(r));
				myGenomeList.add(g);
			}else{
				//crossover
				int r1 = myRandom.nextInt(50);
				int r2 = myRandom.nextInt(50);
				Geno g1 = new Geno(myGenomeList.get(r1));
				Geno g2 = new Geno(myGenomeList.get(r2));
				g1.crossover(g2);
				myGenomeList.add(g1);
			}
		}
		
		
	}
	
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for (Geno g: myGenomeList){
			strBuilder.append(g.toString() + " ");
		}
		return strBuilder.toString();
	}
}
