import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;//for test
import java.util.Collections;
import java.util.Comparator;
import java.util.List;//for test
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		Popu population = new Popu(100, 0.05);
	
		while(population.myMostFit.fitness() != 0) {
			population.day();
			System.out.println(population.myMostFit);
		}
	}
}
