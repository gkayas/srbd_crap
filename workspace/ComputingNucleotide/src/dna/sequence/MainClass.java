package dna.sequence;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainClass {
	public static void main(String[] args) {
		SequenceSet ss = null ;
		try {
			ss = SequenceSet.load("ecoli.fasta");
			System.out.println(ss.sequenceSet.size());
			System.out.println(ss.getUsage(3, 10));
		} catch (FileNotFoundException e) {
			System.out.println("Inout File not found.");
			e.printStackTrace();
		}
		LinkedList<Sequence> sequences = ss.getSequences();
		Iterator<Sequence> seqItr = sequences.iterator();
//		while(seqItr.hasNext()) {
//			Sequence seq = seqItr.next();
//			System.out.println(seq);
//			System.out.println(seq.getUsage(20, 10));
//		}
	}
}//end of main class
