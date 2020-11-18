package posiedon.think.filetest;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/27 23:39
 */
public class FileTest {
    TreeInfo treeInfo = new TreeInfo();
    @Test
    public void test1() {
        File filepath = new File("F:\\generator\\");
        String[] list = filepath.list();
//        String[] list1 = filepath.list(new DirFilter());
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("===================");
        TreeInfo treeInfo = recurseDir(filepath, "");
        treeInfo.files.forEach(System.out::println);
        System.out.println("============");
        treeInfo.dirs.forEach(System.out::println);
    }

    class DirFilter implements FilenameFilter {
        private Pattern pattern;

        public DirFilter() {
            pattern = Pattern.compile("F:+^[A-Za-z]");
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

    public TreeInfo recurseDir(File startDir,String regex){

        for (File file : startDir.listFiles()) {
            if(file.isDirectory()){
                treeInfo.dirs.add(file);
                recurseDir(file,regex);
//                treeInfo.dirs.addAll(recurseDir(file,regex).dirs);
            }else {
                treeInfo.files.add(file);
            }
        }

        return treeInfo;
    }

    static class TreeInfo {
        List<File> files = new ArrayList<File>();
        List<File> dirs = new ArrayList<File>();
//        Set<File> files = new HashSet<File>();
//        Set<File> dirs = new HashSet<File>();
    }
}
