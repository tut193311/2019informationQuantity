package s4.B193311; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*package s4.specification;
  ここは、１回、２回と変更のない外部仕様である。
  public interface FrequencerInterface {     // This interface provides the design for frequency counter.
  void setTarget(byte  target[]); // set the data to search.
  void setSpace(byte  space[]);  // set the data to be searched target from.
  int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
  //Otherwise, it return 0, when SPACE is not set or SPACE's length is zero
  //Otherwise, get the frequency of TAGET in SPACE
  int subByteFrequency(int start, int end);
  // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
  // For the incorrect value of START or END, the behavior is undefined.
  }
*/



public class Frequencer implements FrequencerInterface{
	// Code to start with: This code is not working, but good start point to work.
	byte [] myTarget;
	byte [] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;

	int []  suffixArray; // Suffix Arrayの実装に使うデータの型をint []とせよ。


	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a integer, which is the starting position in mySpace.

	// The following is the code to print the contents of suffixArray.
	// This code could be used on debugging.

	private void printSuffixArray() {
		if(spaceReady) {
			for(int i=0; i< mySpace.length; i++) {
				int s = suffixArray[i];
				for(int j=s;j<mySpace.length;j++) {
					System.out.write(mySpace[j]);
				}
				System.out.write('\n');
			}
		}
	}

	private int suffixCompare(int i, int j) {
		// suffixCompareはソートのための比較メソッドである。
		// 次のように定義せよ。
		// comparing two suffixes by dictionary order.
		// suffix_i is a string starting with the position i in "byte [] mySpace".
		// Each i and j denote suffix_i, and suffix_j.
		// Example of dictionary order
		// "i"      <  "o"        : compare by code
		// "Hi"     <  "Ho"       ; if head is same, compare the next element
		// "Ho"     <  "Ho "      ; if the prefix is identical, longer string is big
		//
		//The return value of "int suffixCompare" is as follows.
		// if suffix_i > suffix_j, it returns 1
		// if suffix_i < suffix_j, it returns -1
		// if suffix_i = suffix_j, it returns 0;

		// ここにコードを記述せよ
		//
		int index_i = suffixArray[i];
		int index_j = suffixArray[j];
		while(mySpace[index_i] == mySpace[index_j]){
			index_i++;
			index_j++;
			if(mySpace.length <= index_i || mySpace.length <= index_j){
				if(suffixArray[i] < suffixArray[j]){
					return 1;
				}else if(suffixArray[i] > suffixArray[j]){
					return -1;
				}else{
					return 0;
				}
			}
		}
		if(mySpace[index_i] > mySpace[index_j]){
			return 1;
		}else{
			return -1;
		}
	}

	public void setSpace(byte []space) {
		// suffixArrayの前処理は、setSpaceで定義せよ。
		mySpace = space; if(mySpace.length>0) spaceReady = true;
		// First, create unsorted suffix array.
		suffixArray = new int[space.length];
		int tempArray[] = new int[space.length];
		// put all suffixes in suffixArray.
		for(int i = 0; i< space.length; i++) {
			suffixArray[i] = i;
			tempArray[i] = i;	// Please note that each suffix is expressed by one integer.
		}

		//
		// ここに、int suffixArrayをソートするコードを書け。
		// 　順番はsuffixCompareで定義されるものとする。

		//バブルソート
//		for(int i = 0; i < suffixArray.length; i++) {
//			for(int j = suffixArray.length-1; j > i; j--) {
//			    if(suffixCompare(i, j) == 1) {
//			    	int temp = suffixArray[i];
//			    	suffixArray[i] = suffixArray[j];
//			    	suffixArray[j] = temp;
//				}
//			}
//		}

		//マージソート
		suffixArray = mergeSort(tempArray);
	}
	//マージソートに必要な関数群
//	private int[] mergeSort(int array[]){
//		if(array.length > 1){
//			int left = array.length/2;
//			int right = array.length - left;
//			int a1[] = new int[left];
//			int a2[] = new int[right];
//			//for(int i = 0; i < left; i++){ a1[i] = array[i]; }
//			//for(int i = 0; i < right; i++){ a2[i] = array[left+i]; }
//			System.arraycopy(array, 0, a1, 0, a1.length);
//			System.arraycopy(array, left, a2, 0, a2.length);
//			mergeSort(a1);
//			mergeSort(a2);
//			merge(a1, a2, array);
//		}
//		return array;
//	}
//
//	private void merge(int a1[], int a2[], int array[]){
//		int i = 0;
//		int j = 0;
//		while(i < a1.length || j < a2.length){
//			if(i >= a1.length || (j < a2.length && suffixCompare(a1[i], a2[j]) == 1)){
//				array[i+j] = a2[j];
//				j++;
//			}else{
//				array[i+j] = a1[i];
//				i++;
//			}
//		}
//	}

	//再帰を使わないマージソート
	private int[] mergeSort(int array[]){
		 if(array.length < 2){
		 	return array;
		 }
		 int left = 0;
		 int right = 1;
		 int end = 2;
		 int step = 1;
		 int array_length = array.length;
		 int[] tmp = new int[array_length];
		 while(step < array_length){
		 	while(left <= array_length-1){
		 		if(end > array_length){
		 			end = array_length;
				}
		 		if(right > array_length-1){
		 			right = end;
				}
				for(int i = left, j = 0, k = 0; i < end; i++){
					if(left+j == right){
						tmp[i] = array[right+k];
						k++;
					}else if(right+k == end){
						tmp[i] = array[left+j];
						j++;
					}else if(suffixCompare(array[left+j], array[right+k]) == 1){
						tmp[i] = array[right+k];
						k++;
					}else{
						tmp[i] = array[left+j];
						j++;
					}
				}
		 		left = end;
		 		right = end+step;
		 		end = end+step+step;
			}
			step = step*2;
			left = 0;
			right = step;
			end = step+step;
			System.arraycopy(tmp, 0, array, 0, array_length);
		 }
		 return array;
	}

	// Suffix Arrayを用いて、文字列の頻度を求めるコード
	// ここから、指定する範囲のコードは変更してはならない。

	public void setTarget(byte [] target) {
		myTarget = target; if(myTarget.length>0) targetReady = true;
	}

	public int frequency() {
		if(targetReady == false) return -1;
		if(spaceReady == false) return 0;
		return subByteFrequency(0, myTarget.length);
	}



	public int subByteFrequency(int start, int end) {
        /* This method be work as follows, but much more efficient
           int spaceLength = mySpace.length;
           int count = 0;
           for(int offset = 0; offset< spaceLength - (end - start); offset++) {
            boolean abort = false;
            for(int i = 0; i< (end - start); i++) {
             if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
            }
            if(abort == false) { count++; }
           }
        */
		int first = subByteStartIndex(start, end);
		int last1 = subByteEndIndex(start, end);
		return last1 - first;
	}
	// 変更してはいけないコードはここまで。

	private int targetCompare(int i, int j, int k) {
		// suffixArrayを探索するときに使う比較関数。
		// 次のように定義せよ
		// suffix_i is a string in mySpace starting at i-th position.
		// target_i_k is a string in myTarget start at j-th postion ending k-th position.
		// comparing suffix_i and target_j_k.
		// if the beginning of suffix_i matches target_i_k, it return 0.
		// The behavior is different from suffixCompare on this case.
		// if suffix_i > target_i_k it return 1;
		// if suffix_i < target_i_k it return -1;
		// It should be used to search the appropriate index of some suffix.
		// Example of search
		// suffix          target
		// "o"       >     "i"
		// "o"       <     "z"
		// "o"       =     "o"
		// "o"       <     "oo"
		// "Ho"      >     "Hi"
		// "Ho"      <     "Hz"
		// "Ho"      =     "Ho"
		// "Ho"      <     "Ho "   : "Ho " is not in the head of suffix "Ho"
		// "Ho"      =     "H"     : "H" is in the head of suffix "Ho"
		//
		// ここに比較のコードを書け
		//

		int suffix_i = suffixArray[i];
		int target_j = j;
		int target_k = k;
		int target_length = k-j;

		while(mySpace[suffix_i] == myTarget[target_j]) {
			suffix_i++;
			target_j++;
			if(mySpace.length <= suffix_i || target_k <= target_j) {
				if(mySpace.length - suffixArray[i] < target_length) {
					return -1;
				} else if(mySpace.length - suffixArray[i] > target_length){
					return 0;
				}else{
					return 0;
				}
			}
		}
		if(mySpace[suffix_i] > myTarget[target_j]) {
			return 1;
		} else {
			return -1;
		}
	}


	private int subByteStartIndex(int start, int end) {
		//suffix arrayのなかで、目的の文字列の出現が始まる位置を求めるメソッド
		// 以下のように定義せよ。
        /* Example of suffix created from "Hi Ho Hi Ho"
           0: Hi Ho
           1: Ho
           2: Ho Hi Ho
           3:Hi Ho
           4:Hi Ho Hi Ho
           5:Ho
           6:Ho Hi Ho
           7:i Ho
           8:i Ho Hi Ho
           9:o
           A:o Hi Ho
        */

		// It returns the index of the first suffix
		// which is equal or greater than target_start_end.
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho", it will return 5.
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho ", it will return 6.
		//
		// ここにコードを記述せよ。
		//

//		int count = -1;
//		for(int i = 0; i < suffixArray.length; i++){
//			//int suffix_i = suffixArray[i];
//			if(targetCompare(i, start, end) == 0){
//				count = i;
//				break;
//			}
//		}
//		return count;

		//二分探索
		if(targetCompare(0, start, end) == 0){
			return 0;
		}
		int left = 1;
		int right = suffixArray.length - 1;
		while(true){
			int mid = (left + right)/2;
			int result = targetCompare(mid, start, end);
			if(left >= right){
				if(result == 0){
					return mid;
				}else{
					return -1;
				}
			}else if(result == 0 && targetCompare(mid-1, start, end) != 0){
				return mid;
			}else if(result == -1){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
	}

	private int subByteEndIndex(int start, int end) {
		//suffix arrayのなかで、目的の文字列の出現しなくなる場所を求めるメソッド
		// 以下のように定義せよ。
        /* Example of suffix created from "Hi Ho Hi Ho"
           0: Hi Ho
           1: Ho
           2: Ho Hi Ho
           3:Hi Ho
           4:Hi Ho Hi Ho
           5:Ho
           6:Ho Hi Ho
           7:i Ho
           8:i Ho Hi Ho
           9:o
           A:o Hi Ho
        */
		// It returns the index of the first suffix
		// which is greater than target_start_end; (and not equal to target_start_end)
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is "Ho", it will return 7 for "Hi Ho Hi Ho".
		// Assuming the suffix array is created from "Hi Ho Hi Ho",
		// if target_start_end is"i", it will return 9 for "Hi Ho Hi Ho".
		//
		//　ここにコードを記述せよ
		//

//		int count = -1;
//		for(int i = subByteStartIndex(start, end); i < suffixArray.length; i++) {
//			//int suffix_i = suffixArray[i];
//			if(targetCompare(i, start, end) != 0) {
//				count = i;
//				break;
//			}
//		}

		//二分探索
		if(targetCompare(mySpace.length-1, start, end) == 0){
			return mySpace.length;
		}
		int left = 0;
		int right = suffixArray.length - 2;
		while(true){
			int mid = (left + right)/2;
			int result = targetCompare(mid, start, end);
			if(left >= right){
				if(result == 0){
					return mid + 1;
				}else{
					return -1;
				}
			}else if(result == 0 && targetCompare(mid+1, start, end) != 0){
				return mid + 1;
			}else if(result == 1){
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
	}


	// Suffix Arrayを使ったプログラムのホワイトテストは、
	// privateなメソッドとフィールドをアクセスすることが必要なので、
	// クラスに属するstatic mainに書く方法もある。
	// static mainがあっても、呼びださなければよい。
	// 以下は、自由に変更して実験すること。
	// 注意：標準出力、エラー出力にメッセージを出すことは、
	// static mainからの実行のときだけに許される。
	// 外部からFrequencerを使うときにメッセージを出力してはならない。
	// 教員のテスト実行のときにメッセージがでると、仕様にない動作をするとみなし、
	// 減点の対象である。
	public static void main(String[] args) {
		Frequencer frequencerObject;
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
			frequencerObject.printSuffixArray(); // you may use this line for DEBUG
            /* Example from "Hi Ho Hi Ho"
               0: Hi Ho
               1: Ho
               2: Ho Hi Ho
               3:Hi Ho
               4:Hi Ho Hi Ho
               5:Ho
               6:Ho Hi Ho
               7:i Ho
               8:i Ho Hi Ho
               9:o
               A:o Hi Ho
            */

			frequencerObject.setTarget("i".getBytes());
			System.out.println("'o' > 'i' : " + frequencerObject.targetCompare(9, 0, 1));
			frequencerObject.setTarget("H".getBytes());
			System.out.println("'Ho' = 'H' : " + frequencerObject.targetCompare(5, 0, 1));
			frequencerObject.setTarget("H".getBytes());
			System.out.println("'i Ho Hi Ho' > 'H': " + frequencerObject.targetCompare(8, 0, 1));
//			frequencerObject.setTarget("AAAAB".getBytes());
//			System.out.println("'B' = 'B': " + frequencerObject.targetCompare(3, 4, 5));


			frequencerObject.setTarget("H".getBytes());

			System.out.println(frequencerObject.subByteStartIndex(0, 1)); // 3 is correct.
			System.out.println(frequencerObject.subByteEndIndex(0, 1)); // 7 is correct.
			//
			// ****  Please write code to check subByteStartIndex, and subByteEndIndex
			//

			int result = frequencerObject.frequency();
			System.out.print("Freq = "+ result+" ");
			if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
		}
		catch(Exception e) {
			System.out.println("STOP");
			e.printStackTrace();
		}
	}
}
