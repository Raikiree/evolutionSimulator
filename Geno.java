import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Geno {
	public static final String TARGET = "EVOLUTION SIMULATOR";
	//not necessary to use Character type
	private static final char[] MY_FULL_LIST = new char[]{ 'A', 'B', 'C', 'D', 'E', 
		'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
		'X', 'Y', 'Z', ' ', '-', '\''};
	private static Random myRandom = new Random();
	public double myMutationRate; // mutation rate means that whether a gene will mutate or not
	private List<Character> gene; 

	//constructor1
	public Geno(double theMutationRate) {

		myMutationRate = theMutationRate; 
		gene = new ArrayList<Character>();
		gene.add('A');
	}
	
	//constructor2
	public Geno(Geno thegene) {
		myMutationRate = thegene.myMutationRate;
		gene = new ArrayList<Character>();
		gene.addAll(thegene.gene);
	}
	
	
	//mutate
	public void mutate() {
		//add
		double r1 = myRandom.nextDouble();
		if (r1 <= myMutationRate) {
			addRandomChar();
		}
		//delete
		double r2 = myRandom.nextDouble();
		if (r2 <= myMutationRate) {
			deleteRandomChar();
		}
		//replace a certain character
		for(int i = 0; i < gene.size(); i++) {
			double r3 = myRandom.nextDouble();
			if(r3 <= myMutationRate) {
				replaceRandomChar(i);
			}
		}
		
	}
	//implementation of three mutations
	//add
	private void addRandomChar() {
		
		int charIndex = myRandom.nextInt(29);  
		int index = myRandom.nextInt(gene.size() +1);  
		if (index < gene.size()) {
			gene.add(index, MY_FULL_LIST[charIndex]);
		} else {
			gene.add(MY_FULL_LIST[charIndex]);
		}
	}
	
	//delete
	private void deleteRandomChar() {
		if (gene.size() >= 2) {
			int charIndex = myRandom.nextInt(gene.size());
			gene.remove(charIndex);
		}
	}
	
	//replace
	private void replaceRandomChar(int theIndex) {
		//not necessary to check the theIndex out the boundary or not
			int charIndex = myRandom.nextInt(29);
			gene.set(theIndex, MY_FULL_LIST[charIndex]);
	}
	
	//crossover
	//will update the current gene
	public void crossover(Geno other) {
	
		List<Character> otherGene = new ArrayList<Character>();
		otherGene.addAll(other.gene);
		List<Character> newGene = new ArrayList<Character>(); 
		int i = 0;
		while(true){
			int r = myRandom.nextInt(2);
			if(r == 0){
				if(i < gene.size())
					newGene.add(gene.get(i));
				else
					break;
			}else{
				if(i < otherGene.size())
					newGene.add(otherGene.get(i));
				else
					break;
			}
			i++;
		}
		this.gene.clear();
		this.gene.addAll(newGene);
	}
	
	//fitness
	public Integer fitness() {
		int n = gene.size();
		int m = TARGET.length();
		int l = Math.max(n, m);
		int f = Math.abs(m - n);
		
		for(int i = 0; i < l; i++){
			if (i < gene.size() 
					&& i < TARGET.length()) {
				if (gene.get(i) != TARGET.charAt(i)) {
					f++; 
				}
			} else {
				f++; 	
			}
		}
		return f;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(\"");
		for(Character c: gene) {
			stringBuilder.append(c);
		}
		stringBuilder.append("\", ");
		stringBuilder.append(fitness());
		stringBuilder.append(")");
		return stringBuilder.toString();
	}	
}
