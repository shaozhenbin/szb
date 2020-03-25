/*package com.szb.mongodb.test;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.szb.mongodb.define.Constants.brand;
import static com.szb.mongodb.define.Constants.categoryPerson;
import static com.szb.mongodb.define.Constants.color;
import static com.szb.mongodb.define.Constants.design;
import static com.szb.mongodb.define.Constants.exfactoryPrice;
import static com.szb.mongodb.define.Constants.fabricContent;
import static com.szb.mongodb.define.Constants.large;
import static com.szb.mongodb.define.Constants.largeCode;
import static com.szb.mongodb.define.Constants.listedBand;
import static com.szb.mongodb.define.Constants.middle;
import static com.szb.mongodb.define.Constants.middleCode;
import static com.szb.mongodb.define.Constants.name;
import static com.szb.mongodb.define.Constants.processingMethods;
import static com.szb.mongodb.define.Constants.season;
import static com.szb.mongodb.define.Constants.sku;
import static com.szb.mongodb.define.Constants.small;
import static com.szb.mongodb.define.Constants.smallCode;
import static com.szb.mongodb.define.Constants.spu;
import static com.szb.mongodb.define.Constants.style;
import static com.szb.mongodb.define.Constants.year;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Workbook;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteError;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import AppConfig;
import com.szb.mongodb.domain.Product;
import com.szb.mongodb.domain.dto.Map2SkuDto;
import com.szb.mongodb.mapper.DocumentMapper;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Slf4j
public class MongoTest3 {

	@Autowired
	private MongoTemplate template;
	@Autowired
	private MongoClient client;
	@Autowired
	private DocumentMapper mapper;

	@Test
	public void 批量查询_sku() {

		List<Product> products = new ArrayList<>();
		List<String> readIds = new ArrayList<>();
		readIds.add("713K2805059");
		readIds.add("713K2805059");
		readIds.add("713K2805059");
		readIds.add("713K2805058");
		readIds.add("713S8003009");
		*//**
 * 1.set去重，获取查询sku集合
 * <p>
 * 2.保存商品中心存在sku集合
 * <p>
 * 2.添加sku
 * <p>
 * 3.找出商品中心不存在的sku集合
 * <p>
 * 1.读取excel，获取查询条件 2.根据条件查询
 * <p>
 * 1.set去重，获取查询sku集合
 * <p>
 * 2.保存商品中心存在sku集合
 * <p>
 * 2.添加sku
 * <p>
 * 3.找出商品中心不存在的sku集合
 * <p>
 * 1.读取excel，获取查询条件 2.根据条件查询
 * <p>
 * 将map装换为javabean对象
 *
 * @param map
 * @param bean
 * @return <p>
 * Description:首字母大写转小写<br />
 * </p>
 * @author qipai
 * @version 0.1 2018年7月26日
 * @param s
 * @return String
 *//*
		List<String> queryIds = new ArrayList<>(new HashSet<String>(readIds));
		log.debug("queryIds--------->{}", queryIds);
		*//**
 * 2.保存商品中心存在sku集合
 *//*
		List<String> existsList = new ArrayList<>();
		FindIterable<Document> docs = client.getDatabase("goods").getCollection("sku").find(in("_id", queryIds))
				.projection(fields(excludeId(), include(sku, color, spu))).batchSize(1000);// 默认返回101
		MongoCursor<Document> mongoCursor = docs.iterator();
		Document tempDoc = new Document();
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			*//**
 * 2.添加sku
 *//*
			existsList.add(doc.getString(sku));
			if (!StringUtils.isEmpty(doc.getString(spu))) {
				Document doc1 = client.getDatabase("goods").getCollection("spu").find(eq("_id", doc.getString(spu)))
						.projection(fields(excludeId(),
								include(brand, categoryPerson, design, exfactoryPrice, fabricContent, large, middle,
										small, season, name, listedBand, processingMethods, style, year, largeCode,
										middleCode, smallCode)))
						.first();
				doc.putAll(doc1);
			} else {
				continue;
			}
			for (Entry<String, Object> entry : doc.entrySet()) {
				tempDoc.append(toLowerCaseFirstOne(entry.getKey()), entry.getValue());
				log.debug("key----->{}:value----->{}", entry.getKey(), entry.getValue());
			}
			log.info("doc---------->{}", tempDoc);
			Map2SkuDto skuDto = mapToBean(tempDoc, new Map2SkuDto());
			log.debug("skuDto---------------------->{}", skuDto);
			Product product = mapper.toProduct(skuDto);
			log.debug("product---------------------->{}", product);
			products.add(product);
		}

		// 批量插入
		if (products.size() > 0) {
			try {
				template.insertAll(products);
			} catch (Exception e) {
				log.debug("error----------->{}", e.getMessage());
			}
		}

		*//**
 * 3.找出商品中心不存在的sku集合
 *//*
		queryIds.removeAll(existsList);// 自定义数据类型得重写hashCode和equals方法。removeAll其实是循环调用remove
		log.debug("queryIds--------->{}", queryIds);
		client.close();
	}
	@Test
	public void 批量查询_sku_bulkWrite() throws FileNotFoundException, Exception {

		*//**
 * 1.读取excel，获取查询条件 2.根据条件查询
 *//*
		ImportParams params = new ImportParams();
		params.setHeadRows(1);
		List<Map<String, Object>> maplist = ExcelImportUtil
				.importExcel(new FileInputStream(new File("E://excel//sku.xlsx")), Map.class, params);

		List<String> readIds = new ArrayList<>();
		for (Map<String, Object> map : maplist) {
			if (!StringUtils.isEmpty(map.get(sku))) {
				readIds.add(map.get(sku).toString());
			}
		}
//		log.debug("skulist----------->{}", readIds);
		*//**
 * 1.set去重，获取查询sku集合
 *//*
		List<String> queryIds = new ArrayList<>(new HashSet<String>(readIds));
//		log.debug("queryIds--------->{}", queryIds);
		*//**
 * 2.保存商品中心存在sku集合
 *//*
		List<String> existsList = new ArrayList<>();
		FindIterable<Document> docs = client.getDatabase("goods").getCollection("sku").find(in("_id", queryIds))
				.projection(fields(include(sku, color, spu))).batchSize(1000);// 默认返回101
		MongoCursor<Document> mongoCursor = docs.iterator();
			
		List<InsertOneModel<Document>> insertList =  new ArrayList<InsertOneModel<Document>>();
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			*//**
 * 2.添加sku
 *//*
			existsList.add(doc.getString(sku));
			if (!StringUtils.isEmpty(doc.getString(spu))) {
				Document doc1 = client.getDatabase("goods").getCollection("spu").find(eq("_id", doc.getString(spu)))
						.projection(fields(excludeId(),
								include(brand, categoryPerson, design, exfactoryPrice, fabricContent, large, middle,
										small, season, name, listedBand, processingMethods, style, year, largeCode,
										middleCode, smallCode)))
						.first();
				if(!doc1.isEmpty()) {
					doc.putAll(doc1);
				}else {		
					continue;
				}
			} else {
				continue;
			}
			
			Document tempDoc = new Document();
			for (Entry<String, Object> entry : doc.entrySet()) {
				tempDoc.append(toLowerCaseFirstOne(entry.getKey()), entry.getValue());
//				log.debug("key----->{}:value----->{}", entry.getKey(), entry.getValue());
			}			
			insertList.add(new InsertOneModel<Document>(tempDoc));
		}
		//批量插入
		try {
			MongoCollection<Document> collection = client.getDatabase("test").getCollection("product").withWriteConcern(WriteConcern.ACKNOWLEDGED);
	        BulkWriteOptions options = new BulkWriteOptions();
	        options.ordered(false);
        	BulkWriteResult bwr = collection.bulkWrite(insertList, options);
        	log.debug("bwr------->{}",bwr.toString());
        }catch(MongoBulkWriteException e) {
			log.debug("message----------->{}", e.getWriteResult());
			List<BulkWriteError> list = e.getWriteErrors();
			if(list != null && list.size() > 0) {
				for(BulkWriteError error : list ) {
					log.debug("error----->{}", error.getMessage());
					
				}
			}
			
		}
		*//**
 * 3.找出商品中心不存在的sku集合
 *//*
		queryIds.removeAll(existsList);// 自定义数据类型得重写hashCode和equals方法。removeAll其实是循环调用remove
//		log.debug("queryIds--------->{}", queryIds);
		client.close();
	}

	@Test
	public void excel导入读取数据() throws FileNotFoundException, Exception {
		*//**
 * 1.读取excel，获取查询条件 2.根据条件查询
 *//*
		ImportParams params = new ImportParams();
		params.setHeadRows(1);
		List<Map<String, Object>> list = ExcelImportUtil
				.importExcel(new FileInputStream(new File("E://excel//sku.xlsx")), Map.class, params);

		List<String> skulist = new ArrayList<>();
		for (Map<String, Object> map : list) {
			if (!StringUtils.isEmpty(map.get(sku))) {
				skulist.add(map.get(sku).toString());
			}
		}
		log.debug("skulist----------->{}", skulist);
	}

	@Test
	public void excel导出_sku列表() throws IOException {
		
		List<Map<String, Object>> skuList = new ArrayList<>();
		
		FindIterable<Document> docs = client.getDatabase("goods").getCollection("sku").find().projection(fields(include(sku))).batchSize(1000).limit(1000);
		MongoCursor<Document> mongoCursor = docs.iterator();
		while(mongoCursor.hasNext()) {
			
			Document doc = mongoCursor.next();
			if(!StringUtils.isEmpty(doc.getString(sku))) {		
				skuList.add(doc);			
			}
			
		}
		log.debug("skulist size------------------->{}", skuList.size());
		Map<String,Object> map = new HashMap<>();
		map.put("maplist", skuList);
		
		TemplateExportParams paramsTemplate = new TemplateExportParams("E://excel//sku-模板.xlsx");
		Workbook workbook = ExcelExportUtil.exportExcel(paramsTemplate, map);
		
		FileOutputStream fos = new FileOutputStream("E:/excel/sku.xlsx");
		workbook.write(fos);
		fos.close();
		client.close();
	}
	*//**
 * 将map装换为javabean对象
 *
 * @param map
 * @param bean
 * @return
 *//*
	private <T> T mapToBean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	*//**
 *
 * <p>
 * Description:首字母大写转小写<br />
 * </p>
 *
 * @author qipai
 * @version 0.1 2018年7月26日
 * @param s
 * @return String
 *//*
	private String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return new StringBuilder().append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
*/