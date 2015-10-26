package LearnCluster;

public class ClusterCoefficient {
	
	public ClusterCoefficient() {
		
	}
	
	/**
	 * 
	 * @param N
	 * ���_��
	 * @param M
	 * �}��
	 * @param E
	 * �}�̏��B�אڍs��ł͂Ȃ��A�}���X�g�Ƃ���int�z��ɁB
	 * @return
	 */
	public double funcC(int N, int M, int[] E) {
		int j1, j2, vs, ve1, ve2, swap, found, first;
		int[] P = new int[N];
		int[] K = new int[N];
		int[] tri = new int[N];
		
		// ������
		for(int i = 0; i < N; i++) {
			K[i] = tri[i] = P[i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			// E[2*i] < E[2*i+1]�ɂ���
			if(E[2 * i] > E[2 * i + 1]) {
				swap = E[2 * i];
				E[2 * i] = E[2 * i + 1];
				E[2 * i + 1] = swap;
			}
		}
		sort(); // �}�̏��Ԃ��n�_�ɂ��ď����ɂ���
		
		for(int i = 0 ; i < 2 *M; i ++) {
			K[E[i]]++;
		}
		for(int i = 0; i < M; i++) {
			P[E[2 * i]]++;
		}
		for(int i = 0; i < N; i++) {
			P[i] += P[i-1];
		}
		
		for(vs = 0; vs < N; vs++) {
			if(vs == 0) first = 0;
			else first = P[vs - 1];
			
			for(int i = first; i < P[vs]; i++) {
				for(j1 = i + 1; j1 < P[vs]; j1++) {
					ve1 = E[2 * i + 1];
					ve2 = E[2 * j1 + 1];
					if(ve1 > ve2) {
						swap = ve1;
						ve1 = ve2;
						ve2 = swap;
					}
					j2 = P[ve1 - 1];
					found = 0;
					while (j2 < P[ve1] && found == 0) {
						if(E[2 * j2 + 1] == ve2) found = 1;
						else j2++;
					}
					if(found == 1) {
						tri[vs]++;
						tri[ve1]++;
						tri[ve2]++;
					}
				}
			}
		}
		
		double C = 0;
		double[] Cl = new double[N];
		int Neff = 0;
		for(int i = 0; i < N ;i++) {
			if(K[i] >= 2) {
				Neff++;
				Cl[i] = (double)(tri[i]/(K[i] * (K[i] - 1) / 2));
				C += Cl[i];
			}
			C = C / Neff;
		}
		return C;
	}
	
	
	/**
	 * �}�͖��������AE[2*i]���n�_�AE[2*i +1]���I�_�ƌĂԁB
	 */
	private void sort() {
		
	}
}
