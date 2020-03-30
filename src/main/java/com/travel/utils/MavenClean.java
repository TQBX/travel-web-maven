package com.travel.utils;

import java.io.File;

/**
 * @auther Summerday
 */
public class MavenClean {

    private static int emptyDirectoryNum = 0;
    private static int lastUpdateNum = 0;

    private int emptyNum = 0;

    /**
     * 要清理的内容包括
     * 1. 所有的.lastUpdated 结尾的文件
     * 2. 清理之后的所有空文件夹
     */

    public static void main(String[] args) {
        final String mavenRepositoryPath = "E:\\Java\\maven_repository";
        File f = new File(mavenRepositoryPath);
        MavenClean clean = new MavenClean();
        //while(clean.emptyNum!=0&&clean.emptyNum!=-1) {
        //这里一次执行清理不完 多次执行直到打印出0
        clean.cleanLastUpdate(f);
        clean.cleanEmptyDirectory(f);
        System.out.println("删除文件 " + lastUpdateNum + "个, 删除空文件夹 " + emptyDirectoryNum + "个");
        System.out.println(clean.emptyNum);
        //}
    }

    /**
     * 删除所有.lastUpdated结尾的文件
     *
     * @param f
     */
    public void cleanLastUpdate(File f) {
        if (f != null) {
            if (f.getName().endsWith("lastUpdated")) {
                lastUpdateNum++;
                System.out.println("要删除的文件是：" + f.getAbsolutePath());
                f.delete();
            }
            if (f.isDirectory()) {
                File[] fs = f.listFiles();
                for (File fItem : fs) {
                    cleanLastUpdate(fItem);
                }
            }
        }

    }

    /**
     * 删除所有的空文件夹
     *
     * @param f
     */
    public int cleanEmptyDirectory(File f) {
        if (f != null && f.isDirectory()) {
            File[] fs = f.listFiles();
            if (fs.length == 0) {
                emptyDirectoryNum++;
                emptyNum++;
                System.out.println(f.getAbsolutePath() + " 是空文件夹被删除");
                f.delete();
            } else {
                for (File fc : fs) {
                    cleanEmptyDirectory(fc);
                }
            }
        }
        return emptyNum;

    }
}
