package s4.B193311; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class AdditionalTestCase {
    public static void main(String[] args) {
        int c;
        c = 0;
        try {
            FrequencerInterface myObject;
            int freq;
            c = 0;
            System.out.println("checking Frequencer");
            myObject = new Frequencer();
            freq = myObject.frequency();
            if (-1 != freq) {
                System.out.println("frequency() should return -1, when target is not set, but returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setTarget("".getBytes());
            freq = myObject.frequency();
            if (-1 != freq) {
                System.out.println("frequency() should return -1, when target is empty, but return " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            if (-1 != freq) {
                System.out.println("frequency() for AAA should return -1, when target is  not set. But it returns  " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            myObject.setTarget("".getBytes());
            freq = myObject.frequency();
            if (-1 != freq) {
                System.out.println("frequency() for AAA should return -1, when taget empty string. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setTarget("A".getBytes());
            freq = myObject.frequency();
            if (0 != freq) {
                System.out.println("frequency() for not set, should return 0, when taget is not empty. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("".getBytes());
            myObject.setTarget("A".getBytes());
            freq = myObject.frequency();
            if (0 != freq) {
                System.out.println("frequency() for empty space, should return 0, when taget is not empty. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            myObject.setTarget("A".getBytes());
            freq = myObject.frequency();
            if (3 != freq) {
                System.out.println("frequency() for AAA, should return 3, when taget is A. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            myObject.setTarget("AA".getBytes());
            freq = myObject.frequency();
            if (2 != freq) {
                System.out.println("frequency() for AAA, should return 2, when taget is AA. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            myObject.setTarget("AAA".getBytes());
            freq = myObject.frequency();
            if (1 != freq) {
                System.out.println("frequency() for AAA, should return 1, when taget is AAA. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAA".getBytes());
            myObject.setTarget("AAAA".getBytes());
            freq = myObject.frequency();
            if (0 != freq) {
                System.out.println("frequency() for AAA, should return 0, when taget is AAAA. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
            if (4 != freq) {
                System.out.println("frequency() for Hi_Ho_Hi_Ho, should return 4, when taget is H. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("Ho".getBytes());
            freq = myObject.frequency();
            if (2 != freq) {
                System.out.println("frequency() for Hi_Ho_Hi_Ho, should return 2, when taget is Ho. But it returns " + freq);
                c++;
            }
            /* please note subByteFreqency(0,0) is considered illeagal specification, and you should not include this case */
            myObject = new Frequencer();
            myObject.setSpace("AAAB".getBytes());
            myObject.setTarget("AAAAB".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (3 != freq) {
                System.out.println("SubBytefrequency() for AAAB, should return 3, when taget is AAAAB[0:1]. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAAB".getBytes());
            myObject.setTarget("AAAAB".getBytes());
            freq = myObject.subByteFrequency(1, 2);
            if (3 != freq) {
                System.out.println("SubBytefrequency() for AAAB, should return 3, when taget is AAAAB[1:2]. But it returns " + freq);
                c++;
            }
            if (2 == freq) {
                System.out.println("You might be confused by the intentional error in sample code.");
            }
            myObject = new Frequencer();
            myObject.setSpace("AAAB".getBytes());
            myObject.setTarget("AAAAB".getBytes());
            freq = myObject.subByteFrequency(1, 3);
            if (2 != freq) {
                System.out.println("SubBytefrequency() for AAAB, should return 2, when taget is AAAAB[1:3]. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("AAAB".getBytes());
            myObject.setTarget("AAAAB".getBytes());
            freq = myObject.subByteFrequency(4, 5);
            if (1 != freq) {
                System.out.println("SubBytefrequency() for AAAB, should return 1, when taget is AAAAB[4:5]. But it returns " + freq);
                c++;
            }
            myObject = new Frequencer();
            myObject.setSpace("BAAA".getBytes());
            myObject.setTarget("AAAAB".getBytes());
            freq = myObject.subByteFrequency(4, 5);
            if (1 != freq) {
                System.out.println("SubBytefrequency() for BAAA, should return 1, when taget is AAAAB[4:5]. But it returns " + freq);
                c++;
            }
//            myObject = new Frequencer();
//            myObject.setSpace("はろーわーるど".getBytes());
//            myObject.setTarget("ー".getBytes());
//            freq = myObject.subByteFrequency(0, 2);
//            if (2 != freq) {
//                System.out.println("SubBytefrequency() for はろーわーるど, should return 2, when taget is ー[0:2]. But it returns " + freq);
//                c++;
//            }

            //Additional Test
            myObject = new Frequencer();
            String longString = new String();
            for (int i = 0; i < Math.pow(10, 0); i++) {
                longString = longString + "A";
            }
            myObject.setSpace(longString.getBytes());
            myObject.setTarget("A".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (Math.pow(10, 0) != freq) {
                System.out.println("SubBytefrequency() for A*10^0, should return 10^0, when taget is A[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            longString = new String();
            for (int i = 0; i < Math.pow(10, 4); i++) {
                longString = longString + "A";
            }
            myObject.setSpace(longString.getBytes());
            myObject.setTarget("A".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (Math.pow(10, 4) != freq) {
                System.out.println("SubBytefrequency() for A*10^4, should return 10^4, when taget is A[0:1]. But it returns " + freq);
                c++;
            }

//            myObject = new Frequencer();
//            longString = new String();
//            for (int i = 0; i < Math.pow(10, 4); i++) {
//                longString = longString + "AAAAAAAAAA";
//            }
//            myObject.setSpace(longString.getBytes());
//            myObject.setTarget("A".getBytes());
//            freq = myObject.subByteFrequency(0, 1);
//            if (Math.pow(10, 5) != freq) {
//                System.out.println("SubBytefrequency() for A*10^4, should return 10^5, when taget is A[0:1]. But it returns " + freq);
//                c++;
//            }

            myObject = new Frequencer();
            myObject.setSpace("ABC".getBytes());
            myObject.setTarget("B".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (1 != freq) {
                System.out.println("SubBytefrequency() for ABC, should return 1, when taget is B[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            myObject.setSpace("ABCDE".getBytes());
            myObject.setTarget("B".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (1 != freq) {
                System.out.println("SubBytefrequency() for ABCDE, should return 1, when taget is B[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            myObject.setSpace("ABCDE".getBytes());
            myObject.setTarget("D".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (1 != freq) {
                System.out.println("SubBytefrequency() for ABCDE, should return 1, when taget is D[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            myObject.setSpace("ABBBE".getBytes());
            myObject.setTarget("B".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (3 != freq) {
                System.out.println("SubBytefrequency() for ABBBE, should return 3, when taget is B[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            myObject.setSpace("ABBEE".getBytes());
            myObject.setTarget("B".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (2 != freq) {
                System.out.println("SubBytefrequency() for ABBEE, should return 1, when taget is B[0:1]. But it returns " + freq);
                c++;
            }

            myObject = new Frequencer();
            myObject.setSpace("AABBE".getBytes());
            myObject.setTarget("B".getBytes());
            freq = myObject.subByteFrequency(0, 1);
            if (2 != freq) {
                System.out.println("SubBytefrequency() for AABBE, should return 1, when taget is B[0:1]. But it returns " + freq);
                c++;
            }

        } catch (Exception e) {
            System.out.println("Exception occurred in Frequencer Object: STOP");
            System.out.println(e);
            c++;
        }

        try {
            InformationEstimatorInterface myObject;
            double value;
            System.out.println("checking InformationEstimator");
            myObject = new InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            if ((value < 1.9999) || (2.0001 < value)) {
                System.out.println("IQ for 0 in 3210321001230123 should be 2.0. But it returns " + value);
                c++;
            }
            myObject.setTarget("01".getBytes());
            value = myObject.estimation();
            if ((value < 2.9999) || (3.0001 < value)) {
                System.out.println("IQ for 01 in 3210321001230123 should be 3.0. But it returns " + value);
                c++;
            }
            myObject.setTarget("0123".getBytes());
            value = myObject.estimation();
            if ((value < 2.9999) || (3.0001 < value)) {
                System.out.println("IQ for 0123 in 3210321001230123 should be 3.0. But it returns " + value);
                c++;
            }
            myObject.setTarget("00".getBytes());
            value = myObject.estimation();
            if ((value < 3.9999) || (4.0001 < value)) {
                System.out.println("IQ for 00 in 3210321001230123 should be 4.0. But it returns " + value);
                c++;
            }

            //Additional Test
            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setTarget("a".getBytes());
                value = additionalObject.estimation();
                if (value != Double.MAX_VALUE) {
                    System.out.println("IQ for 'a' in   should be Double.MAX_VALUE. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test1 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setSpace("".getBytes());
                additionalObject.setTarget("a".getBytes());
                value = additionalObject.estimation();
                if (value != Double.MAX_VALUE) {
                    System.out.println("IQ for 'a' in  '' should be Double.MAX_VALUE. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test2 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setSpace("a".getBytes());
                //additionalObject.setTarget("a".getBytes());
                value = additionalObject.estimation();
                if (value != 0.0) {
                    System.out.println("IQ for   in  'a' should be 0.0. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test3 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setSpace("a".getBytes());
                additionalObject.setTarget("".getBytes());
                value = additionalObject.estimation();
                if (value != 0.0) {
                    System.out.println("IQ for '' in 'a' should be 0.0. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test4 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setSpace("abcdefgh".getBytes());
                additionalObject.setTarget("i".getBytes());
                value = additionalObject.estimation();
                if (value != Double.MAX_VALUE) {
                    System.out.println("IQ for 'i' in 'abcdefgh' should be Double.MAX_VALUE. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test5 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setSpace("abcdefgh".getBytes());
                additionalObject.setTarget("140894830648908286".getBytes());
                value = additionalObject.estimation();
                if (value != Double.MAX_VALUE) {
                    System.out.println("IQ for '140894830648908286' in 'abcdefgh' should be Double.MAX_VALUE. But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test6 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                additionalObject.setTarget("".getBytes());
                value = additionalObject.estimation();
                if (value != 0) {
                    System.out.println("IQ for '' in   should be 0 But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test7 is OK!!");
                }
            }

            {
                InformationEstimatorInterface additionalObject;
                double newValue;
                additionalObject = new InformationEstimator();
                value = additionalObject.estimation();
                if (value != 0) {
                    System.out.println("IQ for   in   should be 0 But it returns " + value);
                    c++;
                }else{
                    System.out.println("Additional test8 is OK!!");
                }
            }



        } catch (Exception e) {
            System.out.println("Exception occurred: STOP");
            System.out.println(e);
            e.printStackTrace();
            c++;
        }
        if (c == 0) {
            System.out.println("TestCase OK");
        }
    }
}
//public class TestCase {
//    public static void main(String[] args) {
//	try {
//	    FrequencerInterface  myObject;
//	    int freq;
//	    System.out.println("checking s4.B193311.Frequencer");
//	    myObject = new s4.B193311.Frequencer();
//	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
//	    myObject.setTarget("H".getBytes());
//	    freq = myObject.frequency();
//	    System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
//	    if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
//
//        //Tests that Ogaki Masashi added. I tested on frequencer.
//        //Length of TARGET is zero.
//        FrequencerInterface myObject4 = new s4.B193311.Frequencer();
//        myObject4.setSpace("I my me mine".getBytes());
//        myObject4.setTarget("".getBytes());
//        freq = myObject4.frequency();
//        System.out.print("Length of TARGET is zero. ");
//        if(-1 == freq) { System.out.println("OK"); } else { System.out.println("WRONG"); }
//
//        //Length of SPACE is zero.
//        FrequencerInterface myObject5 = new s4.B193311.Frequencer();
//        myObject5.setTarget("I my me mine".getBytes());
//        myObject5.setSpace("".getBytes());
//        freq = myObject5.frequency();
//        System.out.print("Length of SPACE is zero. ");
//        if(-1 == freq) { System.out.println("OK"); } else { System.out.println("WRONG"); }
//
//        //Correct condition
//        myObject5.setSpace("Hop Step Jumpppppp!!".getBytes());
//	    myObject5.setTarget("p".getBytes());
//	    freq = myObject5.frequency();
//	    System.out.print("\"p\" in \"Hop Step Jumpppppp!!\" appears "+freq+" times. ");
//	    if(8 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
//
//        /*
//        myObject.setSpace("prpr".getBytes());
//	    myObject.setTarget("prprprpr".getBytes());
//	    freq = myObject.frequency();
//	    System.out.print("\"prprprpr\" in \"prpr\" appears "+freq+" times. ");
//	    if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
//        */
//
//        /*
//        freq = myObject.subByteFrequency(0, 1);
//        System.out.print("\"p\" in \"prpr\" appears"+freq+" times. ");
//        if(0 == freq) { System.out.println("OK");} else {System.out.println("WRONG"); }
//        */
//
//	}
//	catch(Exception e) {
//	    System.out.println("Exception occurred: STOP");
//	}
//
//    try {
//        //SPACE and TARGET is not setted.
//        FrequencerInterface myObject1 = new s4.B193311.Frequencer();
//        int freq = myObject1.frequency();
//        System.out.print("SPACE and TARGET is not setted. ");
//        if(-1 == freq) { System.out.println("OK"); } else { System.out.println("WRONG"); }
//
//    } catch(Exception e) {
//       System.out.println("Exception occurred: STOP");
//    }
//
//    try {
//        //TARGET is not setted.
//        FrequencerInterface myObject2 = new s4.B193311.Frequencer();
//        myObject2.setSpace("I my me mine".getBytes());
//        int freq = myObject2.frequency();
//        System.out.print("TARGET is not setted. ");
//        if(-1 == freq) { System.out.println("OK"); } else { System.out.println("WRONG"); }
//
//    } catch(Exception e) {
//       System.out.println("Exception occurred: STOP");
//    }
//
//    try {
//        //SPACE is not setted.
//        FrequencerInterface myObject3 = new s4.B193311.Frequencer();
//        myObject3.setTarget("m".getBytes());
//        int freq = myObject3.frequency();
//        System.out.print("SPACE is not setted. ");
//        if(-1 == freq) { System.out.println("OK"); } else { System.out.println("WRONG"); }
//
//    } catch(Exception e) {
//       System.out.println("Exception occurred: STOP");
//    }
//
//    //I added test programs for Frequency.subBytesFrequency()
//    try {
//        //Correct condition
//        FrequencerInterface frqObj = new s4.B193311.Frequencer();
//        frqObj.setSpace("ppap".getBytes());
//        frqObj.setTarget("pp".getBytes());
//        int frq = frqObj.subByteFrequency(0, 2);
//        System.out.print("\"pp\" in \"ppap\" appears "+frq+"times!. ");
//        if(1 == frq){ System.out.println("OK"); } else { System.out.println("WRONG"); }
//    }catch(Exception e){
//        System.out.println("Exception occured: STOP");
//    }
//
//
//	try {
//	    InformationEstimatorInterface myObject;
//	    double value;
//	    System.out.println("checking s4.B193311.InformationEstimator");
//	    myObject = new s4.B193311.InformationEstimator();
//	    myObject.setSpace("3210321001230123".getBytes());
//	    myObject.setTarget("0".getBytes());
//	    value = myObject.estimation();
//	    System.out.println(">0 "+value);
//	    myObject.setTarget("01".getBytes());
//	    value = myObject.estimation();
//	    System.out.println(">01 "+value);
//	    myObject.setTarget("0123".getBytes());
//	    value = myObject.estimation();
//	    System.out.println(">0123 "+value);
//	    myObject.setTarget("00".getBytes());
//	    value = myObject.estimation();
//	    System.out.println(">00 "+value);
//
//        //Tests that Ogaki Masashi added.
//        System.out.println("I check '000000000001'");
//        myObject.setSpace("000000000001".getBytes());
//        myObject.setTarget("0".getBytes());
//        value = myObject.estimation();
//        System.out.println(">0 "+value);
//        myObject.setSpace("000000000001".getBytes());
//        myObject.setTarget("1".getBytes());
//        value = myObject.estimation();
//        System.out.println(">1 "+value);
//
//
//	}
//	catch(Exception e) {
//	    System.out.println("Exception occurred: STOP");
//	}
//
//    }
//}
	    
