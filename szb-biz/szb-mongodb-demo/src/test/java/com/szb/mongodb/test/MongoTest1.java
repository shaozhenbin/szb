//package com.szb.mongodb.test;
//
//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Projections.excludeId;
//import static com.mongodb.client.model.Projections.fields;
//import static com.mongodb.client.model.Projections.include;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.mongodb.MongoBulkWriteException;
//import com.mongodb.MongoClient;
//import com.mongodb.WriteConcern;
//import com.mongodb.bulk.BulkWriteError;
//import com.mongodb.bulk.BulkWriteResult;
////import com.mongodb.QueryBuilder;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.model.BulkWriteOptions;
//import com.mongodb.client.model.InsertOneModel;
//import com.mongodb.client.model.UpdateOneModel;
//import com.mongodb.client.model.UpdateOptions;
//import com.mongodb.client.model.WriteModel;
//import AppTestConfig;
//import Person;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppTestConfig.class })
//@Slf4j
//public class MongoTest1 {
//
//	@Autowired
//	private MongoClient mongoClient;
//	@Autowired
//	private MongoTemplate template;
//	@Autowired
//	private MongoOperations operations;
//
//	@Test
//	public void 插入文档_Document() {
//
//		Document document = new Document("name", "Café Con Leche")
//	               .append("contact", new Document("phone", "228-555-0149")
//	                                       .append("email", "cafeconleche@example.com")
//	                                       .append("location",Arrays.asList(-73.92502, 40.8279556)))
//	               .append("stars", 3)
//	               .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));
//
//		mongoClient.getDatabase("test").getCollection("person").insertOne(document);
//
//	}
//	@Test
//	public void test() {
//
//		Person s = new Person();
//		s.setId("s001");
//		s.setName("mongodb");
//		s.setSchool("酱菜");
//		template.save(s);
//
//	}
//
//
//	@Test
//	public void test_01() {
//
//		Bson filter = eq("name", "邵振斌");
//		FindIterable<Document> docs = mongoClient.getDatabase("test").getCollection("person").find(filter).projection(fields(include("name"),excludeId()));
//		MongoCursor<Document> mongoCursor = docs.iterator();
//		while(mongoCursor.hasNext()){
//			log.debug("doc---------->{}", mongoCursor.next());
//		}
//	}
//
//	@Test
//	public void test_02() {
//		/**
//		 * 1.默认批量操作是有序，执行发现错误便回滚
//		 * 2.设置为无序，可执行所有操作并报告任何错误
//		 */
//		try {
//			BulkWriteResult bwr = mongoClient.getDatabase("test").getCollection("person").bulkWrite(
//				Arrays.asList(
//						new InsertOneModel<>(new Document("name","shao").append("_id", "1")),
//						new InsertOneModel<>(new Document("name","shao").append("_id", "3")),
//						new InsertOneModel<>(new Document("name","shao").append("_id", "8"))),
//					 new BulkWriteOptions().ordered(false)
//					 );
//		log.debug("bwr------->{}",bwr.toString());
//		} catch(MongoBulkWriteException e) {
//			log.debug("message----------->{}", e.getWriteResult());
//			List<BulkWriteError> list = e.getWriteErrors();
//			if(list != null && list.size() > 0) {
//				for(BulkWriteError error : list ) {
//					log.debug("error----->{}", error.getMessage());
//
//				}
//			}
//
//		}
//
//
//	}
//	@Test
//	public void test_04() {
//		MongoCollection<Document> collection = mongoClient.getDatabase("test").getCollection("person").withWriteConcern(WriteConcern.ACKNOWLEDGED);
//        BulkWriteOptions options = new BulkWriteOptions();
//        options.ordered(false);
//
//        List<InsertOneModel<Document>> insertList =  new ArrayList<InsertOneModel<Document>>();
//        for(int i =0; i< 10; i++){
//            Document document= new Document().append("_id",i).append("name", "shaozhenbin");
//            insertList.add(new InsertOneModel<Document>(document));
//        }
//        try {
//        	BulkWriteResult bwr = collection.bulkWrite(insertList, options);
//        	log.debug("bwr------->{}",bwr.toString());
//        }catch(MongoBulkWriteException e) {
//			log.debug("message----------->{}", e.getWriteResult());
//			List<BulkWriteError> list = e.getWriteErrors();
//			if(list != null && list.size() > 0) {
//				for(BulkWriteError error : list ) {
//					log.debug("error----->{}", error.getMessage());
//
//				}
//			}
//
//		}
//	}
//
//	@Test
//	public void test_05() {
//		MongoCollection<Document> collection = mongoClient.getDatabase("test").getCollection("test").withWriteConcern(WriteConcern.ACKNOWLEDGED);
//
//        BulkWriteOptions options = new BulkWriteOptions();
//        options.ordered(false);
//        List<WriteModel<Document>> requests = new ArrayList<WriteModel<Document>>();
//        List<Document> documents = new ArrayList<>();
//
//        for(int i =0; i< 500; i++){
//            Document document= new Document().append("_id",i).append("name", "shaozhenbin")
//            		.append("school", "juxfe").append("gender", "male").append("bb", "aa")
//            		.append("school1", "juxfe1").append("gender1", "male").append("bb1", "aa")
//            		.append("school2", "juxfe2").append("gender2", "male").append("bb2", "aa")
//            		.append("school3", "juxfe3").append("gender3", "male").append("bb3", "aa")
//            		.append("school4", "juxfe4").append("gender4", "male").append("bb4", "aa")
//            		.append("school5", "juxfe").append("gender5", "male").append("bb5", "aa")
//            		.append("school6", "juxfe").append("gender6", "male").append("bb6", "aa");
//
//            documents.add(document);
//        }
//
//		for (Document document : documents) {
//			//更新条件
//			Document queryDocument = new Document("_id",document.get("_id"));
//			//更新内容，改下书的价格
//			Document updateDocument = new Document("$set", document);
//			//构造更新单个文档的操作模型
//			UpdateOneModel<Document> uom = new UpdateOneModel<Document>(queryDocument,updateDocument,new UpdateOptions().upsert(true));
//			//UpdateOptions代表批量更新操作未匹配到查询条件时的动作，默认false，什么都不干，true时表示将一个新的Document插入数据库，他是查询部分和更新部分的结合
//			requests.add(uom);
//		}
//
//       try {
//        	BulkWriteResult bwr = collection.bulkWrite(requests, options);
//        	log.debug("bwr------->{}",bwr.toString());
//        }catch(MongoBulkWriteException e) {
//			log.debug("message----------->{}", e.getWriteResult());
//			List<BulkWriteError> list = e.getWriteErrors();
//			if(list != null && list.size() > 0) {
//				for(BulkWriteError error : list ) {
//					log.debug("error----->{}", error.getMessage());
//
//				}
//			}
//
//		}
//	}
//
//	@Test
//	public void test_06() {
//
//		Person s = new Person();
//		s.setId("s001");
//		s.setName("mongodb");
//		s.setSchool("酱菜");
//
//		Person s1 = new Person();
//		s1.setId("s002");
//		s1.setName("mongodb");
//		s1.setSchool("酱菜");
//
//		Person s2 = new Person();
//		s2.setId("s003");
//		s2.setName("mongodb");
//		s2.setSchool("酱菜");
//
//		Person s3 = new Person();
//		s3.setId("s004");
//		s3.setName("mongodb");
//		s3.setSchool("酱菜");
//
//		List<Person> list = new ArrayList<>();
//		list.add(s2);
//		list.add(s1);
//		list.add(s);
//		list.add(s3);
//
//		template.insertAll(list);
//
//	}
//
//}
