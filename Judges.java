import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Judges
{

	public static int findJudge(int people, int[][] trust)
	{
		//First, it creates an ArrayList of ArrayLists for each person
		List<List<Integer>> whoEachTrusts = new ArrayList<>();
		ArrayList<Integer> arr = new ArrayList<Integer>(people);
		ArrayList<Integer> notTheJudge = new ArrayList<Integer>();
		for(int[] pair: trust){
			int i = pair[0];
			while(whoEachTrusts.size() <= i)
				whoEachTrusts.add(new ArrayList<Integer>());
			whoEachTrusts.get(i).add(pair[1]-1);
		}
		for(int pers = 0; pers < whoEachTrusts.size(); pers++) 
			if(whoEachTrusts.get(pers).size() == 0){
				for(int persB = 0; persB < whoEachTrusts.size(); persB++)
					if(!(whoEachTrusts.get(persB).contains(pers)) && !(persB == pers))
						notTheJudge.add(persB);
				return(pers + 1); 
			}
		return(-1);
	}

	public static void main(String[] args)
	{
		Random r = new Random();
		int people = r.nextInt(100) + 2; //Randomly determines the amount of people in this town from 2 to 100
		int maxLimit;
		System.out.println(people);
		//Because in some cases, there won't be enough space for 10,000 people, it checks how many could even be possible
		if((people * people) < 10000)
			maxLimit = r.nextInt(people * people); //This is only to increase the likelihood of a judge showing up.
		//Otherwise, it just sets it to 10,000
		else
			maxLimit = 10000;
		System.out.println(maxLimit);
		int[][] trust = new int[maxLimit][2];
		int s = 0;
		while(s < maxLimit) {
			trust[s][0] = r.nextInt(people);
			trust[s][1] = r.nextInt(people);
			while((trust[s][0] == trust[s][1]) || ((trust[s][0] != 0) && (trust[s][1] != 0))) //Just in case.
				trust[s][0] = r.nextInt(people);
				trust[s][1] = r.nextInt(people);
			s++;
		}

		System.out.println("This test: There are " + people + " people.");
		System.out.print("In this case, ");
		if(findJudge(people, trust) == -1)
			System.out.print("there is no judge.");
		else
			System.out.print("the judge is Person " + findJudge(people, trust) + ".");



	}
}
