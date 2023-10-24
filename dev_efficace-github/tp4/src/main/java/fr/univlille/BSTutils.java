package fr.univlille;

/*
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * SDD Seance TP 6 : Représentation d'un ABR en ASCII
 *
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>
 * IUT-A, Universite de Lille, Sciences et Technologies
 */

public final class BSTutils {
	static final char SPC = ' ';
	
	static public String nSpaces(int k){
		String res = new String();
		for(int i=0; i<k; ++i)
			res += SPC;
		return res;
	}
	static public String nUnderscores(int k){
		String res = new String();
		for(int i=0; i<k; ++i)
			res += "_";
		return res;
	}

	static String[] stringArrayConcatH(String[] l, String[] r, int n){
		int max = Math.max(l.length, r.length);
		int min = Math.min(l.length, r.length);
		String[] res = new String[max];
		int i = 0;
		for (; i<min; ++i)
			res[i] = l[i] + nSpaces(n) + r[i];
		for (; i<l.length; ++i)
			res[i] = l[i] + nSpaces(n) + nSpaces(r.length==0?0:r[0].length());
		for (; i<r.length; ++i)
			res[i] = nSpaces(l.length==0?0:l[0].length()) + nSpaces(n) +r[i];
		return res;
	}

	static String[] stringArrayConcatH(String[] l, int n){
		String[] res = new String[l.length];
		for (int i=0; i<l.length; ++i)
			res[i] = l[i] + nSpaces(n);
		return res;
	}
	
	static String[] stringArrayConcatH(int n, String[] r){
		String[] res = new String[r.length];
		for (int i=0; i<r.length; ++i)
			res[i] = nSpaces(n) + r[i];
		return res;
	}
	static String[] stringArrayConcatV(String[] l, String[] r){
		String[] res = new String[l.length + r.length];
		for(int i = 0; i<l.length; ++i)
			res[i] = l[i];
		for(int i = 0; i<r.length; ++i)
			res[l.length+i] = r[i];
		return res;
	}
	
	static String leftLeg(boolean b){
		return b?" ":"/";
		//return ""+SPC;
	}
	static String rightLeg(boolean b){
		return b?" ":"\\";
		//return ""+SPC;
	}

	public static int indexOfSlash(String[] lBot) {
		if (lBot.length==0)
			return 0;
		int i;
		for(i=lBot[0].length()-1;i>=0 && lBot[0].charAt(i)==SPC;--i);
		return i;
	}

	public static int indexOfBackslash(String[] rBot) {
		if (rBot.length==0)
			return 0;
		int i;
		for(i=0;i<rBot[0].length() && rBot[0].charAt(i)==SPC;++i);
		return i;
	}

	public static boolean sameLength(String[] test) {
		if(test.length==0)
			return true;
		int l = test[0].length();
		int i = 1;
		for (; i<test.length && test[i].length()==l; ++i);
		return i==test.length;
	}
}
