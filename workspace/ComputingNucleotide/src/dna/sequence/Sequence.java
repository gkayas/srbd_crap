package dna.sequence;
import java.util.ArrayList;
import java.util.HashMap;

// This class represents a single sequence.
public class Sequence {
	private String header, sequence;
    	// Constructor.
	public Sequence(String header, String seq){
		this.header = header;
		sequence = seq;
		
		
	}
	// Return the header of the sequence.
	public String getHeader(){
		return header;
	}
	public String getSeq(){
		return sequence;
	}
	// Return the usage for word length k and window step size w.
	public Usage getUsage(int wordLen, int stepSize){
		System.out.println("Calculating Kmer for "+sequence+". Word length: "+ wordLen+" Window step: "+stepSize);
		Usage usage = new Usage();
		HashMapCustom<String, Integer> kmerCount = new HashMapCustom<String, Integer>();
		String subStr = "";
		int seqLen = sequence.length();
		ArrayList<String> kmerList = new ArrayList<String>();
		for(int firstIndex=0; firstIndex<seqLen; firstIndex = firstIndex+stepSize){
			int lastIndex = firstIndex + wordLen;
			if(lastIndex<=sequence.length() && (seqLen-firstIndex)>=wordLen){
				subStr = sequence.substring(firstIndex, lastIndex);
				if(kmerList.contains(subStr)) {
					kmerCount.put(subStr, kmerCount.get(subStr)+1);
					
				} else {
					kmerList.add(subStr);
					kmerCount.put(subStr, 1);
				}
				
				
			}else {
				break;
			}
		}
		
		for( int i = 0; i<kmerList.size(); i++) {
			String kmer = kmerList.get(i);
			
			usage.add(kmer, kmerCount.get(kmer));
		}
//		System.out.println(usage);
		return usage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Sequence)) {
			return false;
		}
		Sequence target = (Sequence) obj;
		
		return this.header.equals(target.header) && this.sequence.equals(target.sequence);
	}
	
	@Override
	public String toString() {
		return "Sequence: "+sequence+"\nHeader:"+header;
	}
}
