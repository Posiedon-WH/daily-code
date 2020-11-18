package posiedon.test2;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import posiedon.bean.Author;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/9
 */
public class MongoClientTest {

    private MongoDatabase db;

    private MongoClient client;

    private MongoCollection<Document> doc;

    private List<Author> authorList;

    private List<Document> documentList;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Before
    public void before() {
        //编解码器的 list
       /* List<CodecRegistry> codecResgistes = new ArrayList<>();
        //list 加入默认的编解码器集合 codecResgistes.add(MongoClient.getDefaultCodecRegistry()); //生成一个 pojo 的编解码器
        CodecRegistry pojoCodecRegistry = CodecRegistries.
                fromProviders(PojoCodecProvider.builder().automatic(true).build());
        //list 加入 pojo 的编解码器
        codecResgistes.add(pojoCodecRegistry);
        //通过编解码器的 list 生成编解码器注册中心
        CodecRegistry registry = CodecRegistries.fromRegistries(codecResgistes);

        //把编解码器注册中心放入 MongoClientOptions //MongoClientOptions 相当于连接池的配置信息
        MongoClientOptions options = MongoClientOptions.builder().
                codecRegistry(registry).build();*/

        MongoCredential credential = MongoCredential.createCredential("root", "admin", "mongmm6436".toCharArray());
        //        client = new MongoClient(new ServerAddress("www.ecndata.cn", 27017), Lists.newArrayList(credential)
        //        ,options);
        client = new MongoClient(new ServerAddress("www.xxxx.cn", 27017), Lists.newArrayList(credential));
        db = client.getDatabase("test");
        doc = db.getCollection("user");

        System.out.println("mongo connected");
        Author author1 = new Author();
        author1.setIncome(100000);
        author1.setName("sdsds");
//        doc.drop();


        AtomicInteger atomicInteger = new AtomicInteger();
        authorList = Stream.generate(new Random()::nextInt)
                .limit(20000)
                .map(i -> {
                   final Author author = new Author();
                    Author.Hobby hobby = new Author.Hobby();
                    hobby.setName("interest" + i);
                    hobby.setId(atomicInteger.intValue());
                    author.setName("posiedon" + atomicInteger.getAndIncrement());
                    author.setIncome(i);
                    author.setHobbies(Lists.newArrayList(hobby));
                    return author;
                })
                .collect(Collectors.toList());
        documentList = authorList.stream()
                .map(s -> {
                    try {
                        return Document.parse(new ObjectMapper().writeValueAsString(s));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Test
    public void testInsert() {
        System.out.println("insert data into mongo");
        AtomicInteger count = new AtomicInteger();
        Lists.partition(documentList, 500)
                .forEach(authors -> {
                    try {
                        doc.insertMany(authors);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("count " + count.getAndIncrement());
                });

    }

    @Test
    public void testFind() throws JsonProcessingException {
        FindIterable<Document> documents = doc.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            String json = iterator.next().toJson();
            System.out.println(json);
//            Author author = objectMapper.readValue(iterator.next().toString(), Author.class);
//            System.out.println(author);
        }
        System.out.println(documents);

    }

}
