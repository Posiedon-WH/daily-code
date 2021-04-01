package posiedon.test2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weihai
 * @desc description
 * @date 2020/11/11
 */
@Slf4j
public class FileTest {

    private String filePath = "/Users/bjhl/workDir/my/tmp/test.txt";

    @Test
    public void test1() throws IOException {
        URL url = new URL("https://www.ecndata.cn/data/img/test/test.PNG");
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(5000);
        InputStream inputStream = connection.getInputStream();
        byte[] bs = new byte[] {};
        Path path = Paths.get("/tmp/test.png");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        StringBuilder stringBuilder = new StringBuilder();
        //        BufferedWriter bfw = Files.newBufferedWriter(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            //            inputStream.read(bs);
            //            bfw.write(Arrays.toString(bs));
            //            bfw.flush();

            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            log.info(stringBuilder.toString());
        } catch (Exception e) {
            //            bfw.close();
        }
    }

    @Test
    public void writeTest() throws IOException, InterruptedException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
            log.info("create file name :{}", filePath);
        }

        InputStream inputStream = Files.newInputStream(path);
        log.info("byte length {}", inputStream.available());

        //        log.info(readFromInputStream(inputStream));

        //IOUtils readFile
        //        log.info("IOUtils read inputStream to String: {}",IOUtils.toString(inputStream,"UTF-8"));

        TimeUnit.SECONDS.sleep(2);

    }

    @Test
    public void bufferReaderTest() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            log.info("BufferReader read content:{}", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void readSmallFileByJave() {
        Path path = Paths.get(filePath);
        try {
            List<String> strings = Files.readAllLines(path);
            log.info("Files read small by readAllLines");
            strings.forEach(log::info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readLargeFileByJava() {
        Path path = Paths.get(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            log.info("Java Files read large file:{}", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void filesLinesTest() {
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path);) {
            lines.forEach(log::info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFileByDataInputStream() {
        Path path = Paths.get(filePath);
        try (DataInputStream reader = new DataInputStream(Files.newInputStream(path))) {
            int n = reader.available();
            if (n > 0) {
                byte[] bytes = new byte[n];
                reader.read(bytes);
                log.info("read file by dataInputStream: {}", new String(bytes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If we are reading a large file,
     * FileChannel can be faster than standard IO
     */
    @Test
    public void readWithFileChannel() {
        try {
            RandomAccessFile reader = new RandomAccessFile(filePath, "r");
            FileChannel fileChannel = reader.getChannel();
            int bufferSize=1024;
            if(bufferSize>fileChannel.size()){
                bufferSize=(int) fileChannel.size();
            }
            //todo 学习byteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            fileChannel.read(buffer);
            buffer.flip();
            fileChannel.close();
            reader.close();
            log.info("fileChannel read file content is {}",new String(buffer.array()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * inputStream -> String
     */
    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    log.info("close inputstream success!");
                } catch (IOException e) {
                    log.info("close inputstream fail: {}", e);
                }

            }
        }
        return stringBuilder.toString();
    }


    private byte[] decompress(){
        return null;
    }
}
