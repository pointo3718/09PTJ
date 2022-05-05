package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  ProductServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;

//	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("����ƾ");
		product.setProdDetail("�ܸ�");
		product.setManuDate("20220401");
		product.setPrice(5000);
		product.setFileName("����ƾ");
		
//		System.out.println(product);

		productService.addProduct(product);
		
//		product = productService.getProduct(10021);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("����ƾ", product.getProdName());
		Assert.assertEquals("�ܸ�", product.getProdDetail());
		Assert.assertEquals("20220401", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("����ƾ", product.getFileName());
	}
	
//	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
//		product.setProdNo(10020);
//		product.setProdName("����ƾ");
//		product.setProdDetail("�ܸ�");
//		product.setManuDate("20220401");
//		product.setPrice(5000);
//		product.setFileName("����ƾ");
		
		product = productService.getProduct(10021);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals(10021, product.getProdNo());
		Assert.assertEquals("����ƾ", product.getProdName());
		Assert.assertEquals("�ܸ�", product.getProdDetail());
		Assert.assertEquals("20220401", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("����ƾ", product.getFileName());

	}
	
//	@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10020);
		Assert.assertNotNull(product);
		System.out.println("1"+product);
		
		Assert.assertEquals("����ƾ", product.getProdName());
		Assert.assertEquals("�ܸ�", product.getProdDetail());
		Assert.assertEquals("20220401", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("����ƾ", product.getFileName());

		product.setProdName("���θӽ�Ĺ");
		product.setProdDetail("�ܸ���");
		product.setManuDate("20220402");
		product.setPrice(8000);
		product.setFileName("���θӽ�Ĺ");
		
		System.out.println("2"+product);
		productService.updateProduct(product);
		
		product = productService.getProduct(10021);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		System.out.println(product);

		//==> API Ȯ��
		Assert.assertEquals("���θӽ�Ĺ", product.getProdName());
		Assert.assertEquals("�ܸ���", product.getProdDetail());
		Assert.assertEquals("20220402", product.getManuDate());
		Assert.assertEquals(8000, product.getPrice());
		Assert.assertEquals("���θӽ�Ĺ", product.getFileName());
	 }
	 
//	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	//System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
//	 @Test
	 public void testGetProductListByProductNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10020");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 @Test
	 public void testGetProductListByProductName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("����ƾ");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}