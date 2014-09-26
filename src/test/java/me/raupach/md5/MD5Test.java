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

import java.text.MessageFormat;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class MD5Test {
    
    @Test 
    public void testT() {
	assertEquals(0xd76aa478, MD5.T[0]);
	assertEquals(0xeb86d391, MD5.T[63]);
    }
    
    @Test
    public void swap() {
	MD5 md5 = new MD5();
	assertEquals(0x0f000000, md5.swap(0x0000000f));
	assertEquals(0xabcdef00, md5.swap(0x00efcdab));
    }
    
    @Test 
    public void btoi() {
	MD5 md5 = new MD5();
	byte[] b = new byte[] {-128, 0x00, 0x00, 0x00};
	assertEquals(0x00000080, md5.btoi(b[0], b[1], b[2], b[3]));
    }
    
    @Test
    public void ltob() {
	MD5 md5 = new MD5();
	long l = 8;
	byte[] bytes = md5.ltob(l);
	int a = 0;
    }
    
    @Test 
    public void testHash() {
	MD5 md5 = new MD5();
	assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5.hash(""));
	//assertEquals("0cc175b9c0f1b6a831c399e269772661", md5.hash("a"));
	//assertEquals("900150983cd24fb0d6963f7d28e17f72", md5.hash("abc"));
	//assertEquals("f96b697d7cb7938d525a2f31aaf161d0", md5.hash("message digest"));
	//assertEquals("c3fcd3d76192e4007dfb496cca67e13b", md5.hash("abcdefghijklmnopqrstuvwxyz"));
	//assertEquals("d174ab98d277d9f5a5611c2c9f419d9f", md5.hash("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"));
	//assertEquals("57edf4a22be3c955ac49da2e2107b67a", md5.hash("12345678901234567890123456789012345678901234567890123456789012345678901234567890"));
    }
    
    // 64 bit number
    // 0x00000000 0x00000008
    // low order first
    // 0x00000008 0x00000000
    // endianess
    // 0x08000000 0x00000000

    @Test
    public void test() {
	int[] md = new int[] {
	    0x67452301, 
	    0xefcdab89, 
	    0x98badcfe,
	    0x10325476 
	};
	int[] x = new int[] {
	    0x80,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000,
	    0x00000000
	};
	// d41d8cd98f00b204e9800998ecf8427e
	MD5 md5 = new MD5();
	md5.update(md, x);
    }
    
    @Test
    public void test003() {
	byte bytes[] = new byte[121];
	int bytesToAdd = 64 - bytes.length % 64;
	if (bytesToAdd < 8) { // 2 ints for length
	    bytesToAdd = 64 + bytesToAdd;
	}
	System.out.println(MessageFormat.format("bytes_total={0}, padding_bytes={1}, modulo={2}",
		bytes.length, bytesToAdd, (bytes.length + bytesToAdd) % 64 ));
    }
    
    
    
    
}
