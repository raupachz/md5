/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Björn Raupach
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.raupach.md5;

import java.io.Serializable;
import static java.lang.Math.abs;
import static java.lang.Math.sin;
import static java.lang.Integer.rotateLeft;

public class MD5 implements Serializable {
    
    static final int T[] = new int[64]; 
    static {
	for (int i = 0; i < 64; i++) {
	    Double e = abs(sin(i+1)) * 4294967296L;
	    T[i] = (int) (e.longValue());
	}
    }
    
    public String hash(byte[] bytes) {
	// Initialize state
	int[] md = new int[] {
	    swap(0x01234567),
	    swap(0x89abcdef),
	    swap(0xfedcba98),
	    swap(0x76543210)
	};
	
	
	
	// print pretty string
	StringBuilder sb = new StringBuilder(32);
	sb.append(Integer.toHexString(swap(md[0])));
	sb.append(Integer.toHexString(swap(md[1])));
	sb.append(Integer.toHexString(swap(md[2])));
	sb.append(Integer.toHexString(swap(md[3])));
	return sb.toString();
    }
    
    
    /* Operation for a single 16 word block */
    void update(int md[], int[] x) {
	int a = md[0];
	int b = md[1];
	int c = md[2];
	int d = md[3];
	
	a = ff(a, b, c, d, x[ 0],  7, T[ 0]);
	d = ff(d, a, b, c, x[ 1], 12, T[ 1]);
	c = ff(c, d, a, b, x[ 2], 17, T[ 2]);
	b = ff(b, c, d, a, x[ 3], 22, T[ 3]);
	a = ff(a, b, c, d, x[ 4],  7, T[ 4]);
	d = ff(d, a, b, c, x[ 5], 12, T[ 5]);
	c = ff(c, d, a, b, x[ 6], 17, T[ 6]);
	b = ff(b, c, d, a, x[ 7], 22, T[ 7]);
	a = ff(a, b, c, d, x[ 8],  7, T[ 8]);
	d = ff(d, a, b, c, x[ 9], 12, T[ 9]);
	c = ff(c, d, a, b, x[10], 17, T[10]);
	b = ff(b, c, d, a, x[11], 22, T[11]);
	a = ff(a, b, c, d, x[12],  7, T[12]);
	d = ff(d, a, b, c, x[13], 12, T[13]);
	c = ff(c, d, a, b, x[14], 17, T[14]);
	b = ff(b, c, d, a, x[15], 22, T[15]);
	a = gg(a, b, c, d, x[ 1],  5, T[16]);
	d = gg(d, a, b, c, x[ 6],  9, T[17]);
	c = gg(c, d, a, b, x[11], 14, T[18]);
	b = gg(b, c, d, a, x[ 0], 20, T[19]);
	a = gg(a, b, c, d, x[ 5],  5, T[20]);
	d = gg(d, a, b, c, x[10],  9, T[21]);
	c = gg(c, d, a, b, x[15], 14, T[22]);
	b = gg(b, c, d, a, x[ 4], 20, T[23]);
	a = gg(a, b, c, d, x[ 9],  5, T[24]);
	d = gg(d, a, b, c, x[14],  9, T[25]);
	c = gg(c, d, a, b, x[ 3], 14, T[26]);
	b = gg(b, c, d, a, x[ 8], 20, T[27]);
	a = gg(a, b, c, d, x[13],  5, T[28]);
	d = gg(d, a, b, c, x[ 2],  9, T[29]);
	c = gg(c, d, a, b, x[ 7], 14, T[30]);
	b = gg(b, c, d, a, x[12], 20, T[31]);
	a = hh(a, b, c, d, x[ 5],  4, T[32]);
	d = hh(d, a, b, c, x[ 8], 11, T[33]);
	c = hh(c, d, a, b, x[11], 16, T[34]);
	b = hh(b, c, d, a, x[14], 23, T[35]);
	a = hh(a, b, c, d, x[ 1],  4, T[36]);
	d = hh(d, a, b, c, x[ 4], 11, T[37]);
	c = hh(c, d, a, b, x[ 7], 16, T[38]);
	b = hh(b, c, d, a, x[10], 23, T[39]);
	a = hh(a, b, c, d, x[13],  4, T[40]);
	d = hh(d, a, b, c, x[ 0], 11, T[41]);
	c = hh(c, d, a, b, x[ 3], 16, T[42]);
	b = hh(b, c, d, a, x[ 6], 23, T[43]);
	a = hh(a, b, c, d, x[ 9],  4, T[44]);
	d = hh(d, a, b, c, x[12], 11, T[45]);
	c = hh(c, d, a, b, x[15], 16, T[46]);
	b = hh(b, c, d, a, x[ 2], 23, T[47]);
	a = ii(a, b, c, d, x[ 0],  6, T[48]);
	d = ii(d, a, b, c, x[ 7], 10, T[49]);
	c = ii(c, d, a, b, x[14], 15, T[50]);
	b = ii(b, c, d, a, x[ 5], 21, T[51]);
	a = ii(a, b, c, d, x[12],  6, T[52]);
	d = ii(d, a, b, c, x[ 3], 10, T[53]);
	c = ii(c, d, a, b, x[10], 15, T[54]);
	b = ii(b, c, d, a, x[ 1], 21, T[55]);
	a = ii(a, b, c, d, x[ 8],  6, T[56]);
	d = ii(d, a, b, c, x[15], 10, T[57]);
	c = ii(c, d, a, b, x[ 6], 15, T[58]);
	b = ii(b, c, d, a, x[13], 21, T[59]);
	a = ii(a, b, c, d, x[ 4],  6, T[60]);
	d = ii(d, a, b, c, x[11], 10, T[61]);
	c = ii(c, d, a, b, x[ 2], 15, T[62]);
	b = ii(b, c, d, a, x[ 9], 21, T[63]);
	
	md[0] += a;
	md[1] += b;
	md[2] += c;
	md[3] += d;
    }
    
    int ff(int a, int b, int c, int d, int x, int s, int t) {
	return b + rotateLeft(a + f(b,c,d) + x + t, s);
    }
    
    int f(int x, int y, int z) {
	return x&y | ~x&z;
    }
    
    int gg(int a, int b, int c, int d, int x, int s, int t) {
	return b + rotateLeft(a + g(b,c,d) + x + t, s);
    }
    
    int g(int x, int y, int z) {
	return x&z | y&~z;
    }
    
    int hh(int a, int b, int c, int d, int x, int s, int t) {
	return b + rotateLeft(a + h(b,c,d) + x + t, s);
    }
    
    int h(int x, int y, int z) {
	return x^y^z;
    }
    
    int ii(int a, int b, int c, int d, int x, int s, int t) {
	return b + rotateLeft(a + i(b,c,d) + x + t, s);
    }
    
    int i(int x, int y, int z) {
	return y^(x|~z);
    }
    
    /* swaps the endianess */
    int swap (int n) {
	return (n & 0xff) << 24 
		| ((n >>  8) & 0xff) << 16 
	        | ((n >> 16) & 0xff) << 8 
		| ((n >> 24) & 0xff);
    }
    
}
