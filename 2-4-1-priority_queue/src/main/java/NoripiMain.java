import java.util.*;

public class NoripiMain implements Interface {

	private class NoripiPriorityQueue {
		private List<Integer> data;

		public NoripiPriorityQueue() {
			this.data = new ArrayList<Integer>();
		}

		public void enqueue(int value) {
			this.data.add(value);
		}

		public int dequeue() {
			Collections.sort(this.data);
			int value = this.data.get(this.data.size()-1);

			if (this.data.size()==1) {
				this.data = new ArrayList<Integer>();
			} else {
				this.data = this.data.subList(0, this.data.size()-1);
			}

			return value;
		}

		public boolean empty() {
			return this.data.size()==0;
		}
	}

	public int execute(int N, int L, int P, int[] A, int[] B) {
		NoripiPriorityQueue queue = new NoripiPriorityQueue();

		int[] checkpoints = new int[A.length+1];
		int[] gasAmounts = new int[B.length+1];
		for (int i=0; i<A.length; i++) {
			checkpoints[i] = A[i];
		}
		checkpoints[A.length] = L;
		for (int i=0; i<B.length; i++) {
			gasAmounts[i] = B[i];
		}
		gasAmounts[B.length] = 0;

		int movement = 0;
		int restGas = P;
		int numGasStand = 0;

		int i = 0;
		while (movement < L) {
			int nextmove = checkpoints[i]-movement;
			
			// can move
			if (restGas>=nextmove) { 
				movement += nextmove;
				restGas -= nextmove;
				
				// move 
				queue.enqueue(gasAmounts[i]);
				i++;
			} else {
				if (queue.empty()) {
					return -1;
				}

				int gas = queue.dequeue();
				if (gas==0) {
					return numGasStand;
				}

				restGas += gas;
				numGasStand++;
			}
		}

		return numGasStand;
	}
}