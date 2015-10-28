package Betweenness;

public class Betweenness {
	
	public double[] betweenness(int N, int M, int[] E) {
		
		
		double[] sig = new double[N];
		double[] delta = new double[N];
		
		int[] pair = new int[2 * M];
		int[] S = new int[N];
		int[] Q = new int[N];
		int Np, Ns;
		int qs, qe;
		int[] Eb = new int[4 * M];
		
		for(int i = M - 1; i >= 0; i--) {
			Eb[4 * i] = Eb[4 * i + 3] = E[2 * i];
			Eb[4 * i + 1] = Eb[4 * i + 2] = E[2 * i + 1];
		}
		
		sort(Eb);
		int[] P = new int[N];
		
		for(int i = 0; i < N; i++) P[i] = 0;
		for(int i = 0; i < M; i++) P[Eb[2 * i]]++;
		for(int i = 1; i < N; i++) P[i] += P[i - 1];
		
		double[] b = new double[N];
		for(int i = 0; i < N; i++) b[i] = 0;
		int[] d = new int[N];
		
		for(int vs = 0; vs < N; vs++) {
			for(int i = 0; i < N; i++) {
				d[i] = -1;
				sig[i] = delta[i] = 0; 
			}
			d[vs] = 0;
			sig[vs] = 1.0;
			Ns = 0;
			Q[0] = vs;
			qs = 0;
			qe = 1;
			Np = 0;
			
			while(qs != qe) {
				int u = Q[qs];
				int first;
				qs++;
				S[Ns] = u;
				Ns++;
				if(u == 0) first = 0;
				else first = P[u - 1];
				int ve;
				for(int i = first; i < P[u]; i++) {
					ve = Eb[2 * i + 1];
					if(d[ve] < 0) {
						d[ve] = d[u] + 1;
						Q[qe] = ve;
						qe++;
					}
					if(d[ve] == d[u] + 1) {
						sig[ve] += sig[u];
						pair[2 * Np] = ve;
						pair[2 * Np + 1] = u;
						Np++;
					}
				}
			}
			
			sort(pair);
			while(Ns > 0) {
				int w = S[Ns - 1];
				Ns--;
				if(w != vs) {
					int i = 0;
					if(w > 0) {
						while(pair[2 * i] != w) i++;
					}
					while((pair[2 * i] == w) && (i < Np) ) {
						delta[pair[2 * i + 1]] += (double)(sig[pair[2 * i + 1]] / sig[w] * (1 + delta[w]));
						i++;
					}
					b[w] += delta[w];
				}
			}
			
		}
		
		for(int i = 0; i < N; i++) b[i] = b[i] /2;
		
		return b;
		
	}
	
	private void sort(int[] E) {
		int temp;
		for(int i = 0; i < E.length; i = i + 2) {
			for(int j = E.length - 2; j > i; j = j - 2) {
				if(E[j - 2] > E[j]) {
					temp = E[j];
					E[j] = E[j + 2];
					E[j + 2] = temp;
				}
			}
		}
		
	}

}
