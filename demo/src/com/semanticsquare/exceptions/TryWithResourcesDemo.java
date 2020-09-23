// Master Java Exceptions with Best Practices
package com.semanticsquare.exceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourcesDemo {
	static String inFileStr = "walden.jpg";
	static String outFileStr = "walden-out.jpg";
		// Arm - automatic resource management
	public static void fileCopyWithArm() throws IOException {
		System.out.println("\nInside fileCopyWithArm ...");				
		
		try (Test t = new Test(); Test2 t2 = new Test2(); BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}		
			
			throw new IOException("Important Exception!!");			
		}		
	}	
	
	public static void fileCopyWithoutArm() throws IOException {
		System.out.println("\nInside fileCopyWithoutArm ...");
		
		Test t = null;
		Test2 t2 = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			t = new Test();
			t2 = new Test2();
			in = new BufferedInputStream(new FileInputStream(inFileStr));
			out = new BufferedOutputStream(new FileOutputStream(outFileStr));
			
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}
			
			throw new IOException("Important Exception!!");
			
		} finally {
			
			if (t2 != null) 
				t2.close();
			
			if (t != null) 
				t.close();			
			
			if (in != null)
				in.close();
			
			if (out != null)
				out.close();
		}		
				
	}
	
	/* Handles Exception Masking via Suppression
	 * This method shows when using try catch block how to have other exceptions as supressed exceptions under the main exception
	 * which is `important exception`. This method shows how ARM(fileCopyWithArm) would work behind the scenes. 
	 * Both yield same output.
	 */
	public static void fileCopyWithoutArm2() throws IOException {
		System.out.println("\nInside fileCopyWithoutArm2 ...");
		
		Test t = null;
		Test2 t2 = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		// We make use of this varibale to supress other exceptions.
		IOException ioException = null;		
				
		try {
			t = new Test();
			t2 = new Test2();
			in = new BufferedInputStream(new FileInputStream(inFileStr));
			out = new BufferedOutputStream(new FileOutputStream(outFileStr));
			
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}
			
			throw new IOException("Important Exception!!");
			
		} catch (IOException e) {
			ioException = e;
		} finally {			
			if (ioException != null) {
				try {
					if (t2 != null) {
						t2.close();
					}
				} catch (IOException e) {
					ioException.addSuppressed(e);					
				}				
			} else {
				try {
					if (t2 != null) {
						t2.close();
					}
				} catch (IOException e) {
					ioException = e;					
				}
			}
			
			if (ioException != null) {
				try {
					if (t != null) {
						t.close();
					}
				} catch (IOException e) {
					ioException.addSuppressed(e);					
				}				
			} else {
				try {
					if (t != null) {
						t.close();
					}
				} catch (IOException e) {
					ioException = e;					
				}
			}
			
			if (ioException != null) {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					ioException.addSuppressed(e);					
				}				
			} else {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					ioException = e;					
				}
			}
			
			if (ioException != null) {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					ioException.addSuppressed(e);					
				}				
				
				throw ioException;
			} else {
				if (out != null) {
					out.close();
				}				
			}
		}		
				
	}
	
	public static void main(String[] args) {
		try {
			fileCopyWithoutArm2();
		} catch (IOException e) {
			e.printStackTrace();
			// To get access to suppressed messages we need to code like below:
			/*
			Throwable[] throwables = e.getSuppressed();
			System.out.println(throwables[0].getMessage());
			System.out.println(throwables[1].getMessage());
			*/
		}
		
	}
}

class Test implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception");			
	}		
}

class Test2 implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception 2");			
	}		
}