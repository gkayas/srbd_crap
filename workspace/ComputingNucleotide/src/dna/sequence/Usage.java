package dna.sequence;
import java.util.Iterator;

// This class represents usage data at the sequence or sequence set level.
public class Usage {
	
	LinkedList<Pair<String, Integer>> kmerList;
	// Constructor.
	public Usage() {
		kmerList = new LinkedList<Pair<String, Integer>>();
	}
	
	// Add a kmer with the corresponding number of occurrences.
	public void add(Usage usage){
		Iterator<Pair<String, Integer>> newPairList = usage.getCounts().iterator();
		
		while (newPairList.hasNext()) {
			Pair<String, Integer> pair = newPairList.next();
			String kmer = pair.first;
			
			Iterator<Pair<String, Integer>> oldPairList = kmerList.iterator();
			boolean contains = false;
			while (oldPairList.hasNext()) {
				Pair<String, Integer> oldPair = oldPairList.next();
				if(oldPair.first.equals(kmer)) {
					oldPair.second += pair.second;
					contains = true;
					break;
				}
			}
			
			if(!contains) {
				kmerList.insert(pair);
			}
			
		}
		
		
	}
	// Add a kmer with the corresponding number of occurrences.
	public void add(String kmer, int count){
		kmerList.insert(new Pair<String, Integer>(kmer, count));
	}
	// Return the number of occurrences of kmer.
	public int getCount(String kmer){
		int counter = 0;
		Iterator<Pair<String, Integer>> itr = kmerList.iterator();
		while (itr.hasNext()) {
			Pair<String, Integer> pair = itr.next();
			if(pair.first.equals(kmer)) {
				counter = counter + pair.second;
			}
		}
		return counter;
		
	}
	// Return all kmers with their count.
	public LinkedList<Pair<String, Integer>> getCounts(){
		return kmerList;
	}
	
	@Override
	public String toString() {
		StringBuffer sbr = new StringBuffer();
		Iterator<Pair<String, Integer>> itr = kmerList.iterator();
		while (itr.hasNext()) {
			Pair<String, Integer> pair = itr.next();
			sbr.append(pair.first+"\t"+pair.second+"\n");
		}
		return sbr.toString()+"\n";
	}
	
	public void print() {
		StringBuffer sbr = new StringBuffer();
		Iterator<Pair<String, Integer>> itr = kmerList.iterator();
		while (itr.hasNext()) {
			Pair<String, Integer> pair = itr.next();
			sbr.append(pair.first+"\t"+pair.second+"\n");
		}
		System.out.println(sbr.toString()+"\n");
	}
}
