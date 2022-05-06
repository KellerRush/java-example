package com.kellerrush.example.javacore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListNature  {

    private static final Logger logger = LoggerFactory.getLogger(ListNature.class);

    /**
     * 测试ArrayList和LinkedList集合随机插入数据的效率
     * @param listSize  集合原始容量
     * @param addCount  插入次数
     */
    public void testInsertRandom(int listSize, int addCount) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        long arrayAdd = this.testAddLast(arrayList, addCount);
        arrayList.clear();
        System.gc();

        long linkedAdd = this.testAddLast(linkedList, addCount);
        linkedList.clear();
        System.gc();

        logger.debug("于{}集合数据量下，{}次测试ArrayList与LinkedList测试随机插入，结果如下：===", listSize, addCount);
        logger.debug("ArrayList：生成数据耗时{}", arrayAdd);
        logger.debug("LinkedList：生成数据耗时{}", linkedAdd);

    }

    /**
     * 测试ArrayList和LinkedList集合随机活期去数据的效率
     * @param listSize  集合原始容量
     * @param getCount  获取次数
     */
    public void testGetRandom(int listSize, int getCount) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        long arrayAdd = this.testAddFirst(arrayList, getCount);
        long arrayGet = this.testGetRandom(arrayList, getCount);
        arrayList.clear();
        System.gc();

        long linkedAdd = this.testAddFirst(linkedList, getCount);
        long linkedGet = this.testGetRandom(linkedList, getCount);
        linkedList.clear();
        System.gc();

        logger.debug("于{}集合数据量下，{}次测试Array与LinkedList测试随机获取，结果如下：===", listSize, getCount);
        logger.debug("ArrayList：生成数据耗时{}，查询耗时{}", arrayAdd, arrayGet);
        logger.debug("LinkedList：生成数据耗时{}，查询耗时{}", linkedAdd, linkedGet);

    }


    /**
     * 测试ArrayList和LinkedList集合中首尾插入数据的效率
     * @param addCount 插入次数
     */
    public void testAddFirstAndLast(int addCount) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        long arrayListAddFirst = this.testAddFirst(arrayList, addCount);
        arrayList.clear();
        System.gc();
        long arrayListAddLast = this.testAddLast(arrayList, addCount);
        arrayList.clear();
        System.gc();

        long linkedListAddFirst = this.testAddFirst(linkedList, addCount);
        linkedList.clear();
        System.gc();
        long linkedListAddLast = this.testAddLast(linkedList, addCount);
        linkedList.clear();
        System.gc();

        logger.debug("{}次测试Array与LinkedList测试头尾添加，结果如下：===", addCount);
        logger.debug("ArrayList：头部添加耗时{},尾部添加耗时{}",arrayListAddFirst, arrayListAddLast);
        logger.debug("LinkedList：头部添加耗时{},尾部添加耗时{}",linkedListAddFirst, linkedListAddLast);

    }

    /**
     * 测试ArrayList和LinkedList集合中替换数据的效率
     * @param replaceCount 插入次数
     */
    public void testReplaceRandom(int replaceCount) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        long arrayListReplace = this.testReplaceRandom(arrayList, replaceCount);
        arrayList.clear();
        System.gc();

        long linkedListReplace = this.testReplaceRandom(linkedList, replaceCount);
        linkedList.clear();
        System.gc();

        logger.debug("{}次测试Array与LinkedList测试随机替换，结果如下：===", replaceCount);
        logger.debug("ArrayList：耗时{}",arrayListReplace);
        logger.debug("LinkedList：耗时{}",linkedListReplace);

    }

    /**
     * 测试ArrayList和LinkedList集合中随机删除数据的效率
     * @param removeCount 插入次数
     */
    public void testRemoveRandom(int listSize, int removeCount) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new ArrayList<String>();

        if (listSize < removeCount) {
            throw new IllegalArgumentException("removeCount需要小于listSize");
        }

        testAddLast(arrayList, listSize);
        long arrayListRemove = this.testRemoveRandom(arrayList, removeCount);
        arrayList.clear();
        System.gc();

        testAddLast(linkedList, listSize);
        long linkedListRemove = this.testRemoveRandom(linkedList, removeCount);
        linkedList.clear();
        System.gc();

        logger.debug("{}次测试Array与LinkedList测试随机删除，结果如下：===", removeCount);
        logger.debug("ArrayList：耗时{}",arrayListRemove);
        logger.debug("LinkedList：耗时{}",linkedListRemove);

    }

    private long testAddFirst(List list, int addCount) {
        int[] array = this.getRandomArray(addCount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < addCount; i++) {
            list.add(0, array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }

    private long testAddLast(List list, int addCount) {
        int[] array = this.getRandomArray(addCount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < addCount; i++) {
            list.add(array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }

    private long testReplaceRandom(List list, int replaceCount) {
        int[] array = this.getRandomArray(list.size());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < replaceCount; i++) {
            list.set(array[i], array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }

    private long testRemoveRandom(List list, int removeCount) {
        int[] array = this.getRandomArray(removeCount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < removeCount; i++) {
            try {
                list.remove(array[i]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis() - startTime;
    }

    private long testGetRandom(List list, int getCount) {
        int[] array = this.getRandomArray(getCount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < getCount; i++) {
            list.get(array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }


    private int[] getRandomArray(int addCount){
        int[] array = new int[addCount];
        for (int i = 0; i < addCount; i++) {
            Random randomNum = new Random();
            array[i] = randomNum.nextInt(addCount);
        }
        return array;
    }

//    private int[] getRandomArray(int listSize, int addCount){
//        int[] array = new int[listSize];
//        for (int i = 0; i < listSize; i++) {
//            Random randomNum = new Random();
//            array[i] = randomNum.nextInt(listSize);
//        }
//        return array;
//    }

}
