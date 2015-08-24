package th.go.excise.edbarcode.report.test;

import org.junit.Test;

import static org.junit.Assert.*;
import th.go.excise.edbarcode.report.common.util.ThaiNumberUtils;

public class TestReadBath {
		
	
		@Test
		public void testText1() {
			assertEquals("ศูนย์บาท",ThaiNumberUtils.toThaiBaht("0"));
			
		}
		
		@Test
		public void testText2() {
			
			assertEquals("หนึ่งแสนล้านล้านบาท",ThaiNumberUtils.toThaiBaht("100000000000000000"));
			
		}
		
		@Test
		public void testText3() {
			
			assertEquals("สิบสตางค์",ThaiNumberUtils.toThaiBaht("0.1"));
			
		}
		
		@Test
		public void testText4() {
			
			assertEquals("หนึ่งสตางค์",ThaiNumberUtils.toThaiBaht("0.01"));
			
		}
		
		@Test
		public void testText5() {
			
			assertEquals("สิบบาทสิบสตางค์",ThaiNumberUtils.toThaiBaht("10.1"));
			
		}
		
		@Test
		public void testText6() {
			
			assertEquals("สิบบาทหนึ่งสตางค์",ThaiNumberUtils.toThaiBaht("10.01"));
			
		}
		
		@Test
		public void testText7() {
			
			assertEquals("หนึ่งหมื่นสองพันสามร้อยสี่สิบห้าล้านหกแสนเจ็ดหมื่นแปดพันเก้าร้อยสิบบาท",ThaiNumberUtils.toThaiBaht("12345678910.00"));
			
		}
		
}
